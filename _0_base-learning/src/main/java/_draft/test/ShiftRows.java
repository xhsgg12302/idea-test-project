package _draft.test;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
        head.createCell(0).setCellValue("head");
        
        
        Row row1 = sheet.createRow(1);
        setRowValue(row1,"1");

        Row row2 = sheet.createRow(4);
        setRowValue(row2,"2");

        Row row3 = sheet.createRow(5);
        setRowValue(row3,"3");

        Row row4 = sheet.createRow(6);
        setRowValue(row4 ,"4");

        Row row5 = sheet.createRow(9);
        setRowValue(row5  ,"5");

        // Shift rows 6 - 11 on the spreadsheet to the top (rows 0 - 5)
        sheet.shiftRows(5, 10, -4);

        FileOutputStream fileOut = new FileOutputStream("shiftRows4.xlsx");
        wb.write(fileOut);
        fileOut.close();
        
    }
    
    public static void setRowValue(Row row ,String value){

        for (int i = 0; i < 5; i++) {
            row.createCell(i).setCellValue(value);
        }
    }
}
