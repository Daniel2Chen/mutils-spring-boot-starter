/**
 * 
 */
package cn.minsin.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
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

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.ExcelConfig;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.rule.AbstractFunctionRule;
import cn.minsin.core.tools.IOUtil;
import cn.minsin.core.tools.StringUtil;
import cn.minsin.excel.model.ExcelRowModel;

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
public class ExcelFunctions extends AbstractFunctionRule {

	private final static ExcelConfig config = AbstractConfig.loadConfig(ExcelConfig.class);

	private ExcelVersion excelVersion;

	private Workbook workbook;

	private Row row;

	private Sheet sheet;

	private ExcelFunctions(ExcelVersion excelVersion) {
		if (excelVersion == null || excelVersion == ExcelVersion.VERSION_2007) {
			workbook = new XSSFWorkbook();
			excelVersion = ExcelVersion.VERSION_2007;
		} else {
			workbook = new HSSFWorkbook();
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
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public static ExcelFunctions builder(InputStream in) throws EncryptedDocumentException, IOException{
			Workbook workbook = WorkbookFactory.create(in);
			return new ExcelFunctions(
					workbook instanceof HSSFWorkbook ? ExcelVersion.VERSION_2003 : ExcelVersion.VERSION_2007, workbook);
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
	public static ExcelFunctions builder(InputStream in, ExcelVersion excelVersion) throws Exception {
		try {
			Workbook workbook = WorkbookFactory.create(in);
			return new ExcelFunctions(excelVersion, workbook);
		} catch (Exception e) {
			throw new MutilsException(e, "Excel读取失败！");
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
			throw new MutilsException(e, "Excel读取失败！");
		}
	}

	/**
	 * 设置excel的版本
	 * 
	 * @param excelVersion
	 * @return 2018年10月14日
	 */
	public ExcelFunctions version(ExcelVersion excelVersion) {
		this.excelVersion = excelVersion;
		return this;
	}

	public Workbook getWorkBook() {
		return workbook;
	}

	/**
	 * 获取当前excel的Version
	 * 
	 * @return 2018年10月14日
	 */
	public ExcelVersion getVersion() {
		return excelVersion;
	}

	/**
	 * D://upload/aaa
	 * 
	 * @param filename 无后缀的文件名 2018年10月11日
	 * @throws IOException 
	 */
	public void export(String filename) throws  IOException {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filename + excelVersion.getSuffix());
			workbook.write(fileOutputStream);
		}finally {
			IOUtil.close(workbook, fileOutputStream);
		}
	}

	/**
	 * 	导出文件到浏览器
	 * @param resp
	 * @param fileName 无后缀的文件名 2018年10月11日
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 * @throws Exception
	 */
	public void export(HttpServletResponse resp, String fileName) throws EncryptedDocumentException, IOException {
		try {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1") + excelVersion.getSuffix();
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/x-msdownload; charset=utf-8");
			resp.setHeader("content-disposition", "attachment;filename=" + fileName);
			workbook.write(resp.getOutputStream());
		} catch (Exception e) {
			error(resp, "excel导出失败，已切换为错误模板", e);
		} finally {
			IOUtil.close(workbook);
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
		} else if (value instanceof String) {
			cell.setCellValue((String) value);
		} else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Calendar) {
			cell.setCellValue((Calendar) value);
		} else if (value instanceof Date) {
			cell.setCellValue((Date) value);
		} else {
			cell.setCellValue(value.toString());
		}

		return this;
	}

	///////////////////////////////////// 以上为导出///////////////////////////////////////

	/**
	 * 获取指定的Excel中的值
	 * 
	 * @param sheetNames    需要获取的sheet的名字
	 * @param startRowIndex 开始读取的行下标
	 * @param cellIndex     需要获取的cell下标数组
	 * @return 2018年10月11日
	 */
	public Map<String, ExcelRowModel> getCellValueList(String[] sheetNames, int startRowIndex, int[] cellIndex)
			throws MutilsErrorException {
		Map<String, ExcelRowModel> returnMap = new HashMap<>();
		for (String name : sheetNames) {
			int index = 0;
			Sheet aimSheet = workbook.getSheet(name);
			Iterator<Row> rowIterator = aimSheet.rowIterator();
			ExcelRowModel list = new ExcelRowModel();
			while (rowIterator.hasNext()) {

				Map<Integer, Object> cells = new HashMap<>();
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
					cells.put(i, value);
				}
				if (isAllNull)
					continue;
				list.setCells(cells);
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

	public static void error(HttpServletResponse resp, String message, Exception error) throws EncryptedDocumentException, IOException {
			String errorTemplateUrl = config.getErrorTemplatePath();
			String errorMessage = error == null ? "" : error.getMessage();
			ExcelFunctions.builder(new FileInputStream(errorTemplateUrl)).sheet(config.getErrorTemplateSheetIndex())
					.row(config.getErrorTemplateRowIndex())
					.cell(config.getErrorTemplateCellIndex(), message + "\n\n" + errorMessage)
					.export(resp, config.getErrorTemplateExportName());
	}

	/**
	 * Excel版本
	 * 
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

	/**
	 * 获取Excel文件
	 * 
	 * @param excelName
	 * @return eg:static/xxx.xlsx 2018年10月31日
	 */
	public static InputStream getExcelTempalte(String excelName) {
		InputStream inStream = ExcelFunctions.class.getClassLoader().getResourceAsStream(excelName);
		if (inStream == null) {
			throw new MutilsException(excelName + " 模板文件不存在.");
		}
		return inStream;
	}

}
