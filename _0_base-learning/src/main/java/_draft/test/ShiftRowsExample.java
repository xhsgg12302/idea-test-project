package _draft.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/1/28
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class ShiftRowsExample {

    public static void main(String[] args) {
        try {
            OutputStream os = new FileOutputStream("D:\\idea-project\\hhm-rsp-center\\hello14.xlsx");
            Workbook wb = WorkbookFactory.create(new File("C:\\Users\\haohuo\\Desktop\\网格员采集信息表(最新）羊坊滩村V2.0.xlsx"));
            
            //Workbook wb = new XSSFWorkbook();
            /*Sheet sheet = wb.createSheet("Sheet");
            Row row0 = sheet.createRow(0);
            Row row     = sheet.createRow(1);
            Row row2    = sheet.createRow(2);
            Cell cell   = row.createCell(1);
            Cell cell2  = row2.createCell(1);
            cell2.setCellValue("Hello, Javatpoint");*/


            Sheet sheetAt = wb.getSheetAt(0);


            sheetAt.shiftRows(7, sheetAt.getLastRowNum() , -1);

            sheetAt.shiftRows(5, sheetAt.getLastRowNum(), -1);
            


            wb.write(os);
            os.flush();
            os.close();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
