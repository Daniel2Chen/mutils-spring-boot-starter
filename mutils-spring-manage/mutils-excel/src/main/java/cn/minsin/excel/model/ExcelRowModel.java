package cn.minsin.excel.model;

import java.util.Map;

import cn.minsin.core.rule.AbstractModelRule;

public class ExcelRowModel extends AbstractModelRule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7096662531755822709L;

	//行下标
	private int rowIndex;
	//key为列下标 value为改该列的值
	private Map<Integer, Object> cells;

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public Map<Integer, Object> getCells() {
		return cells;
	}

	public void setCells(Map<Integer, Object> cells) {
		this.cells = cells;
	}
	
	

}
