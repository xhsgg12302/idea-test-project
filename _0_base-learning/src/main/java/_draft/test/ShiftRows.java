package _draft.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/12/2
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class ShiftRows {

    public static void main(String[]args) throws IOException {
        Workbook wb = new XSSFWorkbook();   
        //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet1");

        Row head = sheet.createRow(0);

        CellStyle errorCellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setColor(Font.COLOR_RED);
        errorCellStyle.setFont(font);
        Cell cell = head.createCell(0);
        cell.setCellValue("This is a test of merging");
        cell.setCellStyle(errorCellStyle);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,10));
        
        
        Row row1 = sheet.createRow(1);
        setRowValue(row1,"中");
        
        Row row2 = sheet.createRow(4);
        setRowValue(row2,"华");

        Row row3 = sheet.createRow(5);
        setRowValue(row3,"人");

        Row row4 = sheet.createRow(6);
        setRowValue(row4 ,"民");

        Row row5 = sheet.createRow(9);
        setRowValue(row5  ,"共和国");

        // Shift rows 6 - 11 on the spreadsheet to the top (rows 0 - 5)
        sheet.shiftRows(4, 5, 2);

        FileOutputStream fileOut = new FileOutputStream("shiftRowsOrigin45-2.xlsx");
        wb.write(fileOut);
        fileOut.close();
        
    }
    
    public static void setRowValue(Row row ,String value){

        for (int i = 0; i < 5; i++) {
            row.createCell(i).setCellValue(value);
        }
    }
}
