package com.igeek.ebuy.util.pojo;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月28日 上午11:22:58
 */
public class SearchItem implements java.io.Serializable{
	private String id;
	private String title;
	private String sell_point;
	private long price;
	private String category_name;
	private String item_image;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the sell_point
	 */
	public String getSell_point() {
		return sell_point;
	}
	/**
	 * @param sell_point the sell_point to set
	 */
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	/**
	 * @return the price
	 */
	public long getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(long price) {
		this.price = price;
	}
	/**
	 * @return the category_name
	 */
	public String getCategory_name() {
		return category_name;
	}
	/**
	 * @param category_name the category_name to set
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	/**
	 * @return the item_image
	 */
	public String getItem_image() {
		return item_image;
	}
	/**
	 * @param item_image the item_image to set
	 */
	public void setItem_image(String item_image) {
		this.item_image = item_image;
	}

	
}
