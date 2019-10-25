package com.igeek.ebuy.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.igeek.ebuy.search.mapper.SearchItemMapper;
import com.igeek.ebuy.search.service.SearchService;
import com.igeek.ebuy.search.service.dao.SearchDao;
import com.igeek.ebuy.util.pojo.BuyResult;
import com.igeek.ebuy.util.pojo.SearchItem;
import com.igeek.ebuy.util.pojo.SearchResult;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月28日 上午11:17:02
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchItemMapper searchItemMapper;
	
	@Autowired
	//@Qualifier("cloudSolrServer")
	private SolrServer solrServer;
	
	@Autowired
	private SearchDao searchDAO;
	
	public BuyResult importIndex() {
		try {
			List<SearchItem> list = searchItemMapper.getItemList();
			List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
			//遍历list将数据导入索引库
			for (SearchItem item : list) {
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
				
				docs.add(doc);
			}
			solrServer.add(docs);
			solrServer.commit();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return BuyResult.ok();
	}

	@Override
	public SearchResult searchItem(int page, int rows, String keyword) {
		SolrQuery query = new SolrQuery();
		//开启高亮显示
		query.setHighlight(true);
		query.set("df", "item_title");
		query.setHighlightSimplePre("<span style='color:red'>");
		query.setHighlightSimplePost("</span>");
		//设置查询条件
		query.setQuery("item_title:"+keyword);
		SearchResult result = searchDAO.queryItem(query );
		return result;
	}
}
