package _draft.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;

public class TestPOI {

    public static void main(String [] args){


        try  {
            FileOutputStream fos = new FileOutputStream("/Users/stevenobelia/Desktop/tongrentang.xls");

            Workbook wb = new HSSFWorkbook(); //工作薄对象

            Sheet sheet = wb.createSheet();   //创建一个sheet
            /*
             * 设定合并单元格区域范围
             * 	firstRow  0-based
             * 	lastRow   0-based
             * 	firstCol  0-based
             * 	lastCol   0-based
             */
            CellRangeAddress cra = new CellRangeAddress(0, 3, 3, 9);

            //在sheet里增加合并单元格
            sheet.addMergedRegion(cra);

            /*Row row1 = sheet.createRow(0);
            row1.setHeightInPoints(10 * 15);
            Row row2 = sheet.createRow(1);
            row2.setHeightInPoints(5 * 15);
            Row row3 = sheet.createRow(2);
            row3.setHeightInPoints(10 * 15);


            row1.createCell((short)0).setCellValue("C");
            row2.createCell((short)0).setCellValue("A");
            row3.createCell((short)0).setCellValue("B");*/

            //这两个东西可以控制行列，返回一个单元格，在里面写数据.
            Row row = sheet.createRow(1);   //行,从0开始
            Cell cell_1 = row.createCell(2); // 列，从0开始

            cell_1.setCellValue("When you're right , no one remembers, when you're wrong ,no one forgets .");

            //cell 位置3-9被合并成一个单元格，不管你怎样创建第4个cell还是第5个cell…然后在写数据。都是无法写入的。
            Cell cell_2 = row.createCell(10);

            cell_2.setCellValue("what's up ! ");

            wb.write(fos);

            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
