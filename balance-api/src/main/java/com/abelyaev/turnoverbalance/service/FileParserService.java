package com.abelyaev.turnoverbalance.service;

import com.abelyaev.turnoverbalance.model.dto.BalanceInfo;
import com.abelyaev.turnoverbalance.model.dto.TableInfoDto;
import com.abelyaev.turnoverbalance.model.entity.FilesEntity;
import com.abelyaev.turnoverbalance.producer.BalanceProducer;
import com.abelyaev.turnoverbalance.repository.FilesRepository;
import com.abelyaev.turnoverbalance.utils.ParseFileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.abelyaev.turnoverbalance.constants.Constant.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileParserService {

    private final ParseFileUtils parseFileUtils;
    private final FilesRepository filesRepository;
    private final BalanceProducer balanceProducer;

    public Flux<String> parseFile(Flux<Part> file) {
        return file
                .flatMapSequential(part -> part.content()
                        .<String>handle((dataBuffer, sink) -> {
                            try {
                                XSSFWorkbook workbook = new XSSFWorkbook(dataBuffer.asInputStream());
                                XSSFSheet sheet = workbook.getSheetAt(0);
                                String result = findTurnoverBalanceDuplicates(sheet);
                                String fileName = part.headers().getContentDisposition().getFilename();
                                balanceProducer.sendMessage(new BalanceInfo(UUID.randomUUID().toString(), fileName, result, new Date()));
                                filesRepository.save(FilesEntity.builder()
                                                .filename(fileName)
                                                .duplicates(result)
                                                .build())
                                        .subscribe();
                                sink.next(result);
                            } catch (IOException e) {
                                sink.error(new RuntimeException(e));
                            }
                        }))
                .onErrorReturn("PARSE ERROR");
    }

    /**
     * 1 - ищем в какой ячейке находится сальдо на конец периода
     * 2 - находим ячейки начала первой таблицы и заполняем компании с не нулевой суммой сальдо
     * 3 - находим ячейки начала второй таблицы и находим дубликаты с не нулевой суммой сальдо
     *
     * @param sheet
     */
    private String findTurnoverBalanceDuplicates(XSSFSheet sheet) {
        TableInfoDto tableInfoDto = new TableInfoDto();
        tableInfoDto.setSaldoСolumnIndex(findSaldoIndex(sheet));
        return String.join(", ", getCompanyDuplicates(sheet, tableInfoDto));
    }

    private int findSaldoIndex(XSSFSheet sheet) {
        int saldoIndex = IntStream.range(0, 10)
                .filter(i -> BALANCE_END_PERIOD.equals(parseFileUtils.setCellString(parseFileUtils
                        .getXSSFCell(sheet, 5, i))))
                .findFirst()
                .orElse(0);
        if (saldoIndex == 0) {
            throw new IllegalArgumentException("Не найдено сальдо на конец периода");
        }
        return saldoIndex;
    }

    private TreeSet<String> getCompanyDuplicates(XSSFSheet sheet, TableInfoDto tableInfoDto) {
        Boolean isFirstTable = null;
        HashMap<String, BigDecimal> firstTableMap = new HashMap<>();
        TreeSet<String> companyDuplicates = new TreeSet<>();
        for (int i = 6; i <= sheet.getLastRowNum(); i++) {
            String stringCell = parseFileUtils.setCellString(parseFileUtils.getXSSFCell(sheet, i, 0)).trim();
            if (Boolean.TRUE.equals(isFirstTable)) {
                BigDecimal companySaldoSum = parseFileUtils.getCompanySaldoSum(sheet, i, tableInfoDto.getSaldoСolumnIndex());
                if (BigDecimal.ZERO.compareTo(companySaldoSum) != 0) {
                    firstTableMap.put(stringCell, companySaldoSum);
                }
            }
            if (Boolean.FALSE.equals(isFirstTable) && firstTableMap.containsKey(stringCell)) {
                BigDecimal sum = parseFileUtils.getCompanySaldoSum(sheet, i, tableInfoDto.getSaldoСolumnIndex());
                if (BigDecimal.ZERO.compareTo(sum) != 0) {
                    companyDuplicates.add(stringCell);
                }
            }
            if (FIRST_TABLE_BEGIN_SIX_ZERO.equals(stringCell) || FIRST_TABLE_BEGIN_SIX_TWO.equals(stringCell)) {
                isFirstTable = Boolean.TRUE;
            }
            if (SECOND_TABLE_BEGIN_SIX_ZERO.equals(stringCell) || SECOND_TABLE_BEGIN_SIX_TWO.equals(stringCell)) {
                isFirstTable = Boolean.FALSE;
            }
        }
        if (firstTableMap.isEmpty()) {
            throw new IllegalArgumentException("Неверная форма документа");
        }
        return companyDuplicates;
    }

}
