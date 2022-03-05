package resources;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {
    public FileInputStream  fileInput;
    public FileOutputStream fileOutput;
    public XSSFWorkbook workbook;
    public XSSFSheet    sheet;
    public XSSFRow  row;
    public XSSFCell cell;
    String  path;

    public ExcelUtil(String  path){
        this.path = path;

    }

    public int getRowCount(String   sheetName) throws IOException{
        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fileInput.close();
        return rowcount;

    }

    public int getCellCount(String sheetName, int rowNum) throws IOException{
        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInput.close();
        return cellCount;

    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException{
        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try{
            data = formatter.formatCellValue(cell);

        } catch (Exception exception){
            data = "";
        }
        workbook.close();
        return data;

    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException{
        File xlsxFile = new File(path);
        if (!xlsxFile.exists()){
            workbook = new XSSFWorkbook();
            fileOutput = new FileOutputStream(path);
            workbook.write(fileOutput);

        }

        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);

        if (workbook.getSheetIndex(sheetName)==-1){
            workbook.createSheet(sheetName);
            sheet =  workbook.getSheet(sheetName);

        }

        if (sheet.getRow(rowNum)==null){
            sheet.createRow(rowNum);
            row = sheet.getRow(rowNum);
            cell = row.createCell(colNum);
            cell.setCellValue(data);
            fileOutput =  new FileOutputStream(path);
            workbook.write(fileOutput);
            workbook.close();
            fileInput.close();
            fileOutput.close();
        }
    }
}
