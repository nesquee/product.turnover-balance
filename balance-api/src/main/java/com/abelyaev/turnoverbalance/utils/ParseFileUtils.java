package com.abelyaev.turnoverbalance.utils;

import com.abelyaev.turnoverbalance.model.dto.TableInfoDto;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ParseFileUtils {

    public String setCellString(XSSFCell cell) {
        if (cell == null) {
            return "";
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    public XSSFCell getXSSFCell(XSSFSheet sheet, int row, int index){
        return sheet.getRow(row).getCell(index);
    }

    public double setCellDouble(XSSFCell cell) {
        cell.setCellType(CellType.NUMERIC);
        return cell.getNumericCellValue();
    }

    public BigDecimal getCompanySaldoSum(XSSFSheet sheet, int row, int saldoIndex) {
        BigDecimal sum = new BigDecimal(setCellDouble(getXSSFCell(sheet, row, saldoIndex))
                + setCellDouble(getXSSFCell(sheet, row, saldoIndex + 1)));
        return  sum;
    }
}
