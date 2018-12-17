/**
 * 
 */
package cn.minsin.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.minsin.core.tools.StringUtil;

/**
 * 解析Excel
 * 
 * <p style="color:red">
 * 导出:builder->sheet->row->cell->export 每一步都不能少
 * </p>
 * <p style="color:red">
 * 导入：builder->cellValueList 会返回一个复杂类型' Map<String,List<Map<Integer,Object>>>'
 * </p>
 * <p>
 * 第一层Map 是sheet key为sheet的名字
 * </p>
 * <p>
 * 第二层List 是这个sheet里面的行
 * </p>
 * <p>
 * 第三层Map的key为指定下标的值
 * </p>
 * 
 * @author mintonzhang
 * @2018年10月11日
 */
public class ExcelFunctions {

	private ExcelVersion excelVersion;

	private Workbook workbook;

	private Row row;

	private Sheet sheet;

	private ExcelFunctions(ExcelVersion excelVersion) {
		int year = excelVersion.getYear();
		if (year == 2003) {
			workbook = new HSSFWorkbook();
		} else if (year == 2007) {
			workbook = new XSSFWorkbook();
		}
		this.excelVersion = excelVersion;
	}

	private ExcelFunctions(ExcelVersion excelVersion, Workbook workbook) {
		this.excelVersion = excelVersion;
		this.workbook = workbook;
	}

	/**
	 * 创建空工作簿
	 * 
	 * @param excelVersion
	 * @return 2018年10月11日
	 * @author mintonzhang@163.com
	 */
	public static ExcelFunctions builder(ExcelVersion excelVersion) {
		return new ExcelFunctions(excelVersion);
	}

	/**
	 * 加载已经存在的excel文件
	 * 
	 * @param in
	 * @param excelVersion
	 * @return 2018年10月11日
	 * @author mintonzhang@163.com
	 * @throws Exception
	 */
	public static ExcelFunctions builder(InputStream in) throws Exception {
		try {
			Workbook workbook = WorkbookFactory.create(in);
			return new ExcelFunctions(workbook instanceof HSSFWorkbook?ExcelVersion.VERSION_2003:ExcelVersion.VERSION_2007, workbook);
		} catch (Exception e) {
			throw new Exception("Excel读取失败！");
		}
	}
	/**
	 * 加载已经存在的excel文件 并制定excel的版本
	 * 
	 * @param in
	 * @param excelVersion
	 * @return 2018年10月11日
	 * @author mintonzhang@163.com
	 * @throws Exception
	 */
	public static ExcelFunctions builder(InputStream in,ExcelVersion excelVersion) throws Exception {
		try {
			Workbook workbook = WorkbookFactory.create(in);
			return new ExcelFunctions(excelVersion, workbook);
		} catch (Exception e) {
			throw new Exception("Excel读取失败！");
		}
	}
	/**
	 * 自定义解析Excel 在某些情况,通用的Excel解析是不会满足条件的，那么这个方法会提供了将Inputsteam转换成workbook
	 * 
	 * @param in
	 * @param excelVersion
	 * @return 2018年10月11日
	 * @author mintonzhang@163.com
	 * @throws Exception
	 */
	public static Workbook builderCustomize(InputStream in) throws Exception {
		try {
			return WorkbookFactory.create(in);
		} catch (Exception e) {
			throw new Exception("Excel读取失败！");
		}
	}
	
	/**
	 * 设置excel的版本
	 * @param excelVersion
	 * @return
	 * 2018年10月14日
	 * @author  mintonzhang@163.com
	 */
	public ExcelFunctions version(ExcelVersion excelVersion) {
		this.excelVersion =excelVersion;
		return this;
	}
	
	/**
	 * 获取当前excel的Version
	 * @return
	 * 2018年10月14日
	 * @author  mintonzhang@163.com
	 */
	public ExcelVersion getVersion() {
		return excelVersion;
	} 

	/**
	 * D://upload/aaa
	 * 
	 * @param filename
	 *            无后缀的文件名 2018年10月11日
	 * @author mintonzhang@163.com
	 * @throws Exception
	 */
	public void export(String filename) throws Exception {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(filename + excelVersion.getSuffix());
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		} catch (Exception e) {
			throw new Exception("Excel读取失败！");
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * aaa
	 * 
	 * @param filename
	 *            无后缀的文件名 2018年10月11日
	 * @author mintonzhang@163.com
	 * @throws Exception
	 */
	public void export(HttpServletResponse resp, String fileName) throws Exception {
		try {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1") + excelVersion.getSuffix();
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/x-msdownload; charset=utf-8");
			resp.setHeader("content-disposition", "attachment;filename=" + fileName);
			workbook.write(resp.getOutputStream());
		} catch (Exception e) {
			throw new Exception("Excel读取失败！");
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ExcelFunctions sheet(int sheetnum) {
		try {
			sheet = workbook.getSheetAt(sheetnum);
		} catch (Exception e) {
			sheet = workbook.createSheet();
			workbook.setActiveSheet(sheetnum);
		}
		return this;
	}

	public ExcelFunctions sheet(int sheetnum, String name) {
		try {
			sheet = workbook.getSheetAt(sheetnum);
		} catch (Exception e) {
			sheet = workbook.createSheet(name);
			workbook.setActiveSheet(sheetnum);
		}
		return this;
	}

	public ExcelFunctions row(int rownum) {
		row = null;// 初始化row
		row = sheet.getRow(rownum);
		if (row == null) {
			row = sheet.createRow(rownum);
		}
		return this;
	}

	public ExcelFunctions cell(int index, Object value) {
		Cell cell = null;
		try {
			cell = row.getCell(index);
			cell.setCellType(CellType.STRING);
		} catch (Exception e) {
			cell = row.createCell(index, CellType.STRING);
		}
		if (value == null) {
			cell.setCellValue("");
			return this;
		}
		else if (value instanceof String) {
			cell.setCellValue((String) value);
		}
		else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}
		else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}
		else if (value instanceof Calendar) {
			cell.setCellValue((Calendar) value);
		}
		else if (value instanceof Date) {
			cell.setCellValue((Date) value);
		}else {
			cell.setCellValue(value.toString());
		}
		
		return this;
	}

	///////////////////////////////////// 以上为导出///////////////////////////////////////


	/**
	 * 获取指定的Excel中的值
	 * 
	 * @param sheetNames
	 *            需要获取的sheet的名字
	 * @param startRowIndex
	 *            开始读取的行下标
	 * @param cellIndex
	 *            需要获取的cell下标数组
	 * @return 2018年10月11日
	 * @author mintonzhang@163.com
	 */
	public Map<String, List<Map<Integer, Object>>> cellValueList(String[] sheetNames, int startRowIndex,
			int[] cellIndex) {
		Map<String, List<Map<Integer, Object>>> returnMap = new HashMap<>();
		for (String name : sheetNames) {
			int index = 0;
			Sheet aimSheet = workbook.getSheet(name);
			Iterator<Row> rowIterator = aimSheet.rowIterator();
			List<Map<Integer, Object>> list = new ArrayList<>();
			while (rowIterator.hasNext()) {

				Map<Integer, Object> map = new HashMap<>();
				Row next = rowIterator.next();
				if (index < startRowIndex) {
					index++;
					continue;
				}
				boolean isAllNull = true;
				for (int i : cellIndex) {
					String value = getCellRealValue(next.getCell(i));
					if (value != null) {
						isAllNull = false;
					}
					map.put(i, value);
				}
				if (isAllNull)
					continue;
				list.add(map);
			}
			returnMap.put(name, list);
		}
		return returnMap;
	}

	/**
	 * 获取CellValue
	 * 
	 * @param cell
	 * @return 2018年9月21日
	 * @author mintonzhang@163.com
	 */
	public static String getCellRealValue(Cell cell) {
		if (cell == null)
			return null;
		String cellValue = "";
		// 以下是判断数据的类型
		switch (cell.getCellType()) {
		case NUMERIC: // 数字
			if (DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				cellValue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			} else {// 纯数字
				DataFormatter dataFormatter = new DataFormatter();
				cellValue = dataFormatter.formatCellValue(cell);
			}
			break;
		case STRING: // 字符串
			cellValue = cell.getStringCellValue();
			break;
		case BOOLEAN: // Boolean
			cellValue = cell.getBooleanCellValue() + "";
			break;
		case FORMULA: // 公式
			cellValue = cell.getCellFormula() + "";
			break;
		case BLANK: // 空值
			cellValue = "";
			break;
		case ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return StringUtil.filterSpace(cellValue);
	}

	public static void error(HttpServletResponse resp,String message,Exception error) {
		try {
			String errorMessage = error==null?"":error.getMessage();
			ExcelFunctions.builder(getExcelTempalte("excel/error.xlsx"))
			.sheet(0).row(6).cell(0,message+"\n\n"+errorMessage).export(resp,"错误概要");
		} catch (Exception e) {
			throw new RuntimeException("resources下缺少error.xlsx模板文件");
		}
	}
	
	/**
	 * 获取Excel文件
	 * @param excelName
	 * @return
	 * 2018年10月31日
	 * @author  mintonzhang@163.com
	 */
	public static InputStream getExcelTempalte(String excelName) {
        InputStream inStream = ExcelFunctions.class.getClassLoader().getResourceAsStream(excelName);
        if(inStream==null) {
        	throw new RuntimeException("resources/下缺少 "+excelName+" 模板文件");
        }
        return inStream;
	}
	
	/**
	 * Excel版本
	 * 
	 * @author mintonzhang 2018年10月11日
	 */
	public enum ExcelVersion {
		VERSION_2003(".xls", 2003), VERSION_2007(".xlsx", 2007);
		private String suffix;

		private int year;

		public String getSuffix() {
			return suffix;
		}

		private ExcelVersion(String suffix, int year) {
			this.suffix = suffix;
			this.year = year;
		}

		public int getYear() {
			return year;
		}
	}
}
