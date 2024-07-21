package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
String path;
FileInputStream fi;
FileOutputStream fo;
XSSFWorkbook workbook;
XSSFSheet sheet;
XSSFRow row;
XSSFCell cell;

	public XLUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row= sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row= sheet.getRow(rownum);
		cell= row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);
			//Returns the formatted value of cell as a string
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rownum,int colnum, String data) throws IOException
	{
	File xlfile=new File(path);

	if(!xlfile.exists()) // If file not exists then create new file
	{
	workbook = new XSSFWorkbook();
	fo=new FileOutputStream(path);
	workbook.write(fo);
	}

	fi=new FileInputStream(path);

	workbook= new XSSFWorkbook(fi);
	//If sheet doesn't exist create new sheet
	if(workbook.getSheetIndex(sheetName)==-1)
	{
		workbook.createSheet(sheetName);
	}
	sheet = workbook.getSheet(sheetName);
	
	if(sheet.getRow(rownum)==null)
	{
		sheet.createRow(rownum);
	}
	row= sheet.getRow(rownum);
	
	cell= row.createCell(colnum);
	cell.setCellValue(data);
	fo= new FileOutputStream(path);
	workbook.write(fo);
	workbook.close();
	fi.close();
	fo.close();

	}
	  
	
	
	

}
