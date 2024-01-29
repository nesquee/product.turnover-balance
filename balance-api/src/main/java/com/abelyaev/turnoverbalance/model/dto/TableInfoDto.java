package com.abelyaev.turnoverbalance.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeSet;

@Getter
@Setter
public class TableInfoDto {
    @Schema(description = "Индех колонки сальдо на конец периода", example = "1")
    private int saldoСolumnIndex;

    @Schema(description = "Данные из первой таблицы: название компании, сумма сальдо")
    private HashMap<String, BigDecimal> firstTableMap;

    @Schema(description = "Дубликаты компаний с суммой сальдо, которые содержатся в первой и второй таблицах")
    private TreeSet<String> duplicates;

}
