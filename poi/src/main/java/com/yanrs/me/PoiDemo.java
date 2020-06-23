package com.yanrs.me;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PoiDemo {
    @Test
    public void testWrite() throws Exception {
        // 1. 创建 workbook
        // Workbook workbook = new HSSFWorkbook();  // 03 版本 excel 操作
        Workbook workbook = new XSSFWorkbook();  // 07 版本 excel 操作

        // 2. 根据 workbook 创建 sheet
        Sheet sheet = workbook.createSheet("会员列表");

        // 3. 根据 sheet 操作 row（行）
        Row row = sheet.createRow(0); // 创建第一行（序号从0开始）

        // 4. 根据行 row 操作 cell (列)
        Cell cell = row.createCell(0); // 创建第一列（序号从0开始）

        // 5. 向 cell 中设置值
        cell.setCellValue("131398612389698163");

        // 6. 写入到文件并保存
        // FileOutputStream fileOutputStream = new FileOutputStream("/tmp/xxx.xls");  // 03 版本 excel 操作
        FileOutputStream fileOutputStream = new FileOutputStream("/tmp/xxx.xlsx");  // 07 版本 excel 操作
        workbook.write(fileOutputStream);

        // 关闭流信息
        fileOutputStream.close();
    }

    @Test
    public void  testRead() throws Exception {
        // 1. 获取文件输入流
        FileInputStream fileInputStream = new FileInputStream("/tmp/xxx.xlsx");  // 07 版本 excel 操作
        // FileInputStream fileInputStream = new FileInputStream("/tmp/xxx.xlsx");  // 03 版本 excel 操作

        // 2. 创建 workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream); // 07 版本 excel 操作
        // HSSFWorkbook workbook = new HSSFWorkbook("/tmp/xxx.xlsx");  // 03 版本 excel 操作

        // 3. 根据 workbook 获取 sheet
        XSSFSheet sheet = workbook.getSheet("会员列表");

        // 4. 根据 sheet 获取行
        XSSFRow row = sheet.getRow(0);

        // 5. 根据行获取列
        XSSFCell cell = row.getCell(0);

        // 6. 获取值
        System.out.println(cell.getStringCellValue());

        // 7. 关闭输入流
        fileInputStream.close();
    }
}
