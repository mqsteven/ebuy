package com.igeek.ebuy.util.pojo;

import java.util.List;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月21日 上午11:22:27
 */
public class EasyUIDatagridResult implements java.io.Serializable{
	private long total;
	private List<?> rows;
	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	/**
	 * @return the rows
	 */
	public List<?> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
