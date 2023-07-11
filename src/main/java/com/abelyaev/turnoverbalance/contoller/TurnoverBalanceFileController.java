package com.abelyaev.turnoverbalance.contoller;

import com.abelyaev.turnoverbalance.model.exception.ParseFileException;
import com.abelyaev.turnoverbalance.service.FileParserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Tag(name = "Файлы.", description = "Контроллер для работы с файлами.")
public class TurnoverBalanceFileController {
    private final FileParserService fileParserService;

    @Operation(
            summary = "Загрузка файла",
            description = "Метод для загрузки файла")
    @PostMapping(value = "/v1/upload")
    public String uploadFile(@RequestParam("file") MultipartFile reapExcelDataFile) {
        try {
            return fileParserService.parseFile(reapExcelDataFile);
        } catch (IllegalArgumentException | ParseFileException e) {
            return e.getMessage();
        }
    }
}
