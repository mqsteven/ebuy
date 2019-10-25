package com.igeek.solr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月28日 上午10:23:32
 */
public class SolrjTest {
	
	
	//@Test
	public void testQueryDocHeightLighting() throws SolrServerException {
		SolrServer solrServer = new HttpSolrServer("http://192.168.128.153:8080/solr/core1");
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("item_title:人类");
		//开启高亮显示
		query.setHighlight(true);
		//设置默认搜索域
		query.set("df", "item_title");
		//设置高亮显示的前缀和后缀
		query.setHighlightSimplePre("<em style='color:red'>");
		query.setHighlightSimplePost("</em>");
		
		//查询数据
		QueryResponse response = solrServer.query(query );
		//从response中取出查询结果结合  ArrayList<SolrDocument>
		SolrDocumentList results = response.getResults();
		//从response中取出高亮显示的查询结果
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		System.out.println("总条数:"+results.getNumFound());
		for (SolrDocument doc : results) {
			System.out.println("id:"+doc.get("id")+",item_titel:"+doc.get("item_title"));
		}
		System.out.println("-----高亮显示的结果-------");
		for (SolrDocument doc : results) {
			String id = doc.get("id").toString();
			String itemTitle = doc.get("item_title").toString();
			//根据ID从高亮显示的结果集中取出该条文档所对应的高亮显示的结果
			Map<String, List<String>> map = highlighting.get(id);
			//根据默认的搜索域取出高亮显示的结果列表
			List<String> list = map.get("item_title");
			if(list!=null && list.size()>0) {
				itemTitle = list.get(0);
			}
			System.out.println("id:"+id+",item_titel:"+itemTitle);
		}
	}
	
	
	//@Test
	public void testQueryDoc() throws SolrServerException {
		SolrServer solrServer = new HttpSolrServer("http://192.168.128.153:8080/solr/core1");
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("item_title:人类");
		//查询数据
		QueryResponse response = solrServer.query(query );
		//从response中取出查询结果结合  ArrayList<SolrDocument>
		SolrDocumentList results = response.getResults();
		System.out.println("总条数:"+results.getNumFound());
		for (SolrDocument doc : results) {
			System.out.println("id:"+doc.get("id")+",item_titel:"+doc.get("item_title"));
		}
	}
	
	
	//@Test
	public void testDeleteByQuery() throws SolrServerException, IOException {
//		第一步：把solrJ的jar包添加到工程中。
//		在search-service中添加solrjjar
//		第二步：创建一个SolrServer，使用HttpSolrServer创建对象。
		SolrServer solrServer = new HttpSolrServer("http://192.168.128.153:8080/solr/core1");
		solrServer.deleteByQuery("item_title:吊炸天");
		solrServer.commit();
	}

	
	//@Test
	public void testDeleteByIdDoc() throws SolrServerException, IOException {
//		第一步：把solrJ的jar包添加到工程中。
//		在search-service中添加solrjjar
//		第二步：创建一个SolrServer，使用HttpSolrServer创建对象。
		SolrServer solrServer = new HttpSolrServer("http://192.168.128.153:8080/solr/core1");
		solrServer.deleteById("45680");
		solrServer.commit();
	}
	

	//@Test
	public void testAddDoc() throws SolrServerException, IOException {
//		第一步：把solrJ的jar包添加到工程中。
//		在search-service中添加solrjjar
//		第二步：创建一个SolrServer，使用HttpSolrServer创建对象。
		SolrServer solrServer = new HttpSolrServer("http://192.168.128.153:8080/solr/core1");
//		第三步：创建一个文档对象SolrInputDocument对象。
		SolrInputDocument doc = new SolrInputDocument();
//		第四步：向文档中添加域。必须有id域，域的名称必须在schema.xml中定义。
		doc.addField("id", 45681);
		doc.addField("item_title", "人类文明交流互鉴新篇章  人类前途命运的中国方案!");
//		第五步：把文档添加到索引库中。
		solrServer.add(doc);
//		第六步：提交。
		solrServer.commit();
	}
}
