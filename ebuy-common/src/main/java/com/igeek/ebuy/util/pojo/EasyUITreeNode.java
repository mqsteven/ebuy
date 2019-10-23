package com.igeek.ebuy.util.pojo;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月22日 上午10:34:45
 */
public class EasyUITreeNode implements java.io.Serializable{
	private long id;
	private String text;
	private String state;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

}
