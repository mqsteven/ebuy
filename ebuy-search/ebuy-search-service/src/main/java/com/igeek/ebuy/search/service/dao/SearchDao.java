package com.igeek.ebuy.search.service.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.igeek.ebuy.util.pojo.SearchItem;
import com.igeek.ebuy.util.pojo.SearchResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月28日 下午3:46:48
 */
@Repository
public class SearchDao {
	@Autowired
	//@Qualifier("cloudSolrServer")
	private SolrServer solrServer;
	
	/**
	 * 商品存入索引库
	 * @param item
	 */
	public void saveItem(SearchItem item) {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", item.getId());
		doc.addField("item_title", item.getTitle());
		doc.addField("item_sell_point", item.getSell_point());
		doc.addField("item_price", item.getPrice());
		doc.addField("item_category_name", item.getCategory_name());
		//处理图片地址，只设置第一个图片的地址
		String image = item.getItem_image();
		String[] images = image.split(",");
		if(images!=null && images.length>0) 
			image = images[0];
		doc.addField("item_image", image);
		try {
			solrServer.add(doc);
			solrServer.commit();
		} catch (Exception e) {
			//使用log4j输出异常
			throw new RuntimeException(e);
		}
	}
	
	public SearchResult queryItem(SolrQuery query) {
		SearchResult result = new SearchResult();
		try {
			QueryResponse response = solrServer.query(query);
			SolrDocumentList results = response.getResults();
			//设置result的属性
			result.setRecourdCount((int)results.getNumFound());
			List<SearchItem> items = new ArrayList<SearchItem>();
			//取出高亮显示的结果
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			//遍历常规的结果
			for (SolrDocument item : results) {
				SearchItem searchItem = new SearchItem();
				searchItem.setId(item.get("id").toString());
				searchItem.setCategory_name(item.get("item_category_name").toString());
				searchItem.setItem_image(item.get("item_image").toString());
				searchItem.setPrice(Long.parseLong(item.get("item_price").toString()));
				searchItem.setSell_point(item.get("item_sell_point").toString());
				searchItem.setTitle(item.get("item_title").toString());
				//判断是否有高亮显示的结果
				if(highlighting!=null) {
					Map<String, List<String>> map = highlighting.get(searchItem.getId());
					List<String> list = map.get("item_title");
					if(list!=null && list.size()>0)
						searchItem.setTitle(list.get(0));
				}
				items.add(searchItem);
			}
			result.setItemList(items);
		} catch (SolrServerException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
