/*
package _draft.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.FileOutputStream;
import java.io.Serializable;

public class TestPOI3 {
    public static void main(String[] args) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
        HSSFSheet sheet = wb.createSheet("银行存余额表(1)");// 创建一个Excel的Sheet
        // 定义样式
        HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);//表头样工
        HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);//单元格样式
        HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
        cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//右对齐
        cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);//左对齐
        // 设置列宽
        sheet.setColumnWidth(0, 7200);
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 5000);
        sheet.setColumnWidth(4, 5000);
        sheet.setColumnWidth(5, 5000);
        try {
            HSSFRow row = null;
            HSSFCell cell = null;
            // ---------------------------1.初始化带边框的表头------------------------------
            for (int i = 0; i < 5; i++) {
                row = sheet.createRow(i);
                for (int j = 0; j <= 5; j++) {
                    cell = row.createCell(j);
                    cell.setCellStyle(cellStyleCenter);
                }
            }
            */
/*//*
/ ---------------------------2.指定单元格填充数据------------------------------
            cell = sheet.getRow(0).getCell(0);
            cell.setCellValue(new HSSFRichTextString("银行存余额表"));
            cell = sheet.getRow(1).getCell(0);
            cell.setCellValue(new HSSFRichTextString("2015-08-05"));
            cell = sheet.getRow(2).getCell(0);
            cell.setCellValue(new HSSFRichTextString("开户行"));
            cell = sheet.getRow(2).getCell(1);
            cell.setCellValue(new HSSFRichTextString("活期"));
            cell = sheet.getRow(2).getCell(3);
            cell.setCellValue(new HSSFRichTextString("定期"));
            cell = sheet.getRow(2).getCell(5);
            cell.setCellValue(new HSSFRichTextString("存款合计"));
            cell = sheet.getRow(3).getCell(1);
            cell.setCellValue(new HSSFRichTextString(" "));
            cell = sheet.getRow(3).getCell(4);
            cell.setCellValue(new HSSFRichTextString("折合本位币合计"));
            cell = sheet.getRow(4).getCell(1);
            cell.setCellValue(new HSSFRichTextString("人民币"));
            cell = sheet.getRow(4).getCell(2);
            cell.setCellValue(new HSSFRichTextString("折合本位币合计"));*//*


            // ---------------------------3.合并单元格------------------------------
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));// 开始行，结束行，开始列，结束列
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));
            sheet.addMergedRegion(new CellRangeAddress(2, 4, 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(2, 3, 1, 2));
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 4));
            sheet.addMergedRegion(new CellRangeAddress(3, 4, 4, 4));
            sheet.addMergedRegion(new CellRangeAddress(2, 4, 5, 5));
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\elizabeth\\Desktop\\TestPOI8.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
class ExportFileNameUtils implements Serializable {
    */
/**
     *
     * <br>
     * <b>功能：</b>列头样式<br>
     * <b>作者：</b>yixq<br>
     * <b>@param wb
     * <b>@return</b>
     *//*

    public static HSSFCellStyle initColumnHeadStyle(HSSFWorkbook wb) {
        HSSFCellStyle columnHeadStyle = wb.createCellStyle();
        HSSFFont columnHeadFont = wb.createFont();
        columnHeadFont.setFontName("宋体");
        columnHeadFont.setFontHeightInPoints((short) 10);
        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        columnHeadStyle.setFont(columnHeadFont);
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);
        columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
        columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
        columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
        columnHeadStyle.setBorderRight((short) 1);// 边框的大小
        columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
        columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        return columnHeadStyle;
    }

    */
/**
     *
     * <br>
     * <b>功能：</b>单元格的默认样式<br>
     * <b>作者：</b>yixq<br>
     * <b>@param wb
     * <b>@return</b>
     *//*

    public static HSSFCellStyle initColumnCenterstyle(HSSFWorkbook wb) {
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        HSSFCellStyle centerstyle = wb.createCellStyle();
        centerstyle.setFont(font);
        centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        centerstyle.setWrapText(true);
        centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderLeft((short) 1);
        centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderRight((short) 1);
        centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．
        return centerstyle;
    }
}
*/
