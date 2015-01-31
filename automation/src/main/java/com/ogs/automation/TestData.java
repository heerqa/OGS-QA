package com.ogs.automation;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;


public class TestData {
    private String productname;
/*
    public String username(int rownum) throws IOException {
        XSSFRow row = getCells(rownum);
        XSSFCell usernamecell = row.getCell(0);
        return username = usernamecell.toString();
    }*/
    
    public String productName(int rownum) throws IOException {
        XSSFRow row = getCells(rownum);
        XSSFCell usernamecell = row.getCell(0);
        return productname = usernamecell.toString();
    }

   /* public String password(int rownum) throws IOException {
        XSSFRow row = getCells(rownum);
        XSSFCell usernamecell = row.getCell(1);
        return password = usernamecell.toString();
    }

    public String userid(int rownum) throws IOException {
        XSSFRow row = getCells(rownum);
        XSSFCell usernamecell = row.getCell(2);
        return userid = usernamecell.toString();
    }
*/
    private XSSFRow getCells(int rownum) throws IOException {
        InputStream datasheet = FileUtils.getTestContentInputStream(this.getClass(), "TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(datasheet);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        return sheet.getRow(rownum);
    }

/*    public String toString(int rownum) throws IOException {
        return "User{" +
                "username='" + username(rownum) + '\'' +
                ", password='" + password(rownum) + '\'' +
                ", userid='" + userid(rownum) + '\'' +
                '}';
    }
*/
}

