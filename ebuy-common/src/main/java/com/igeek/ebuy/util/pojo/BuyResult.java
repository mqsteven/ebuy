package com.igeek.ebuy.util.pojo;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月25日 上午11:46:01
 */
public class BuyResult implements java.io.Serializable{

	private int status;//状态码
	private String msg;//消息
	private Object data;//数据对象
	
	//{"status":200,"msg":"消息","data":{xxxxx}}
	
	/**
	 * @param i
	 */
	public BuyResult(int status) {
		this.status = status;
	}

	public BuyResult(int status,Object data) {
		this.status = status;
		this.data = data;
	}
	
	public BuyResult(int status,String message) {
		this.status = status;
		this.msg = message;
	}


	public static BuyResult ok() {
		return new BuyResult(200);
	}
	
	
	
	
	
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @param i
	 * @param string
	 * @return
	 */
	public static BuyResult build(int status, String message) {
		return new BuyResult(status, message);
	}
	
	
}
