package _draft.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;

public class TestPOI2 {

    public static void main(String [] args){
        try  {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\elizabeth\\Desktop\\TestPOI8.xls");

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("My sheet");// 创建一个表
            HSSFCellStyle style = createColumnStyle(workbook);//列名样式
            // 设置列宽  （第几列，宽度）
            sheet.setColumnWidth(0, 6500);
            sheet.setColumnWidth(1, 5500);
            sheet.setColumnWidth(2, 5500);
            sheet.setColumnWidth(3, 5500);
            sheet.setColumnWidth(4, 6500);
            sheet.setColumnWidth(5, 6500);
            sheet.setColumnWidth(6, 6500);
            sheet.setColumnWidth(7, 6500);
            sheet.setDefaultRowHeight((short) 660);//设置行高


            String[] head0 = new String[]{"分类", "支付方式", "总金额", "支付来源", "支付终端", "金额", "操作员"};//在excel中的第3行每列的参数
            //    String[] head1 = new String[]{"交易笔数", "支付金额", "交易笔数", "支付金额"};//在excel中的第4行每列（合并列）的参数
            String[] headnum0 = new String[]{"1,1,0,0", "1,1,1,1", "1,1,2,2",
                    "1,1,3,3", "1,1,4,4", "1,1,5,5", "1,1,6,6"};//对应excel中的行和列，下表从0开始{"开始行,结束行,开始列,结束列"}
            // String[] headnum1 = new String[]{"1,1,6,6", "1,1,7,7", "1,1,8,8",
            //          "1,1,9,9"};

            HSSFCell cell = createTitleStyle(workbook,sheet,"Title",head0,style);
            //动态合并单元格
            sheet = addMergedRegionFirst(sheet, headnum0);//动态合并单元格

            sheet.addMergedRegion(new CellRangeAddress(2,4,0,1));

            sheet.addMergedRegion(new CellRangeAddress(2,3,2,2));



            sheet.createRow(2).createCell(5).setCellValue("你好[2,5]");

            sheet.createRow(3).createCell(5).setCellValue("这是[3,5]");

            HSSFRow row4 = sheet.createRow(4);
            row4.setHeight((short) 0x349);

            HSSFCell cell40 = row4.createCell(2);
                    cell40.setCellStyle(style);
                    cell40.setCellValue("这是[4,2]");



            sheet.createRow(6).createCell(1).setCellValue("这是[6,1]");
            sheet.createRow(6).createCell(2).setCellValue("这是[6,2]");
            sheet.createRow(6).createCell(3).setCellValue("这是[6,3]");
            sheet.createRow(6).createCell(4).setCellValue("这是[6,4]");
            sheet.createRow(6).createCell(5).setCellValue("这是[6,5]");

            HSSFRow row = sheet.createRow(7);
            row.setHeight((short) 0x349);

            HSSFCell cell2 = row.createCell(0);
                     cell2.setCellStyle(style);
                     cell2.setCellValue("这是[7,0]");
            HSSFCell cell3 = row.createCell(1);
                     cell3.setCellValue("这是[7,1]");
                     cell3.setCellStyle(style);
            HSSFCell cell4 = row.createCell(2);
                     cell4.setCellValue("这是[7,2]");
                     cell4.setCellStyle(style);
            HSSFCell cell5 = row.createCell(3);
                     cell5.setCellValue("这是[7,3]");
                     cell5.setCellStyle(style);
            HSSFCell cell6 = row.createCell(4);
                     cell6.setCellValue("这是[7,4]");
                     cell6.setCellStyle(style);
            HSSFCell cell7 = row.createCell(5);
                     cell7.setCellValue("这是[7,5]");
                     cell7.setCellStyle(style);






            workbook.write(fos);
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static HSSFCell createTitleStyle(HSSFWorkbook workbook, HSSFSheet sheet, String title, String[] head0, HSSFCellStyle style) {
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("宋体");
        headfont.setFontHeightInPoints((short) 22);// 字体大小
        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headstyle.setLocked(true);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, head0.length - 1));
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 0x349);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headstyle);
        cell.setCellValue(title);
        HSSFRow row1 = sheet.createRow(1);
        for (int i = 0; i < head0.length; i++) {
            cell = row1.createCell(i);
            cell.setCellValue(head0[i]);
            cell.setCellStyle(style);
        }
        return cell;
    }
    public static HSSFCellStyle createColumnStyle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);// 字体大小
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        style.setLocked(true);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        return style;
    }
    public static HSSFSheet addMergedRegionFirst(HSSFSheet sheet, String[] headnum0) {

        //动态合并单元格
        for (int i = 0; i < headnum0.length; i++) {
            if(headnum0[0]!=null){
                String[] temp = headnum0[i].split(",");
                Integer startrow = Integer.parseInt(temp[0]);
                Integer overrow = Integer.parseInt(temp[1]);
                Integer startcol = Integer.parseInt(temp[2]);
                Integer overcol = Integer.parseInt(temp[3]);
                sheet.addMergedRegion(new CellRangeAddress(startrow, overrow,
                        startcol, overcol));
            }

        }
        return sheet;
    }
}
