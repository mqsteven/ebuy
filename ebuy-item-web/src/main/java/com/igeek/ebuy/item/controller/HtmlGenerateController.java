package com.igeek.ebuy.item.controller;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.igeek.ebuy.item.pojo.User;
import com.igeek.ebuy.pojo.TbItem;
import com.igeek.ebuy.pojo.TbItemDesc;
import com.igeek.ebuy.service.ItemService;
import com.igeek.ebuy.util.pojo.EasyUIDatagridResult;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月3日 上午10:46:36
 */
@Controller
public class HtmlGenerateController {
	
	@Autowired
	FreeMarkerConfigurer creeMarkerConfigurer;
	
	@Autowired
	private ItemService itemService;
	
	
	@RequestMapping("/genAll")
	@ResponseBody
	public String genAll()throws Exception {
		Configuration configuration = creeMarkerConfigurer.getConfiguration();
		Template template = configuration.getTemplate("item.ftl");
		//读取所有的数据
		EasyUIDatagridResult result = itemService.queryByPage(1, 1000);
		List<TbItem> list = (List<TbItem>) result.getRows();
		for (TbItem tbItem : list) {
			Map data = new HashMap();
			data.put("item", tbItem);
			data.put("images", tbItem.getImage().split(","));
			TbItemDesc itemDesc = itemService.queryDescById(tbItem.getId());
			data.put("itemDesc", itemDesc);
			Writer out = new FileWriter("E:\\58营\\项目\\第十天\\static\\items\\"+tbItem.getId()+".html");
			template.process(data, out);
			out.close();
			out = null;
		}
		return "ok";
	}

	@RequestMapping("/generateHtml")
	@ResponseBody
	public String generateHtml() throws Exception{
		Configuration configuration = creeMarkerConfigurer.getConfiguration();
		//获取模板
		Template template = configuration.getTemplate("user.ftl");
		Map data = new HashMap();
		User user = new User();
		user.setId(9527);
		user.setName("小丸子");
		user.setTel("12512512525");
		data.put("score", 85);
		data.put("u", user);
		
		data.put("addr", "国家软件园巨蟹座");
		data.put("name","宇智波-鼬");
		
		//创建集合
		List<User> users = new ArrayList<User>();
		users.add(new User(9528, "佐助", "13813813838"));
		users.add(new User(9529, "鸣人", "13813813839"));
		users.add(new User(9530, "卡卡西", "13813813840"));
		users.add(new User(9531, "卡卡东", "13813813841"));
		users.add(new User(9532, "卡卡北", "13813813842"));
		users.add(new User(9533, "卡卡南", "13813813843"));
		users.add(new User(9534, "卡卡中", "13813813844"));
		data.put("users", users);
		
		//日期处理
		data.put("now", new Date());
		
//		第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
		Writer writer = new FileWriter("E:\\58营\\项目\\第十天\\temp\\user.html");
//		第七步：调用模板对象的process方法输出文件。
		template.process(data, writer);
//		第八步：关闭流。
		writer.close();
		
		return "ok";
	}
}
