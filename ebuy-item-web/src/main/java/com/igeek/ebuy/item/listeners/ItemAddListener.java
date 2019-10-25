package com.igeek.ebuy.item.listeners;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.igeek.ebuy.pojo.TbItem;
import com.igeek.ebuy.pojo.TbItemDesc;
import com.igeek.ebuy.service.ItemService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月3日 下午1:39:05
 */
public class ItemAddListener implements MessageListener {
	@Autowired
	FreeMarkerConfigurer creeMarkerConfigurer;
	
	@Autowired
	private ItemService itemService;
	
	@Value("${OUT_PATH}")
	private String OUT_PATH;
	
	public void onMessage(Message message) {
		try {
			Configuration configuration = creeMarkerConfigurer.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			TextMessage msg = (TextMessage)message;
			TbItem item = itemService.queryById(Long.parseLong(msg.getText().trim()));
			TbItemDesc itemDesc = itemService.queryDescById(item.getId());
			String [] images = item.getImage().split(",");
			item.setImage(images[0]);
			Map data = new HashMap();
			data.put("item", item);
			data.put("images", images);
			data.put("itemDesc", itemDesc);
			Writer out = new FileWriter(OUT_PATH+"/"+item.getId()+".html");
			template.process(data, out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
