package com.igeek;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年4月3日 上午9:29:29
 */
public class FreemarkerTest {
	
	
	//@Test
	public void testFreemarker2() throws IOException, TemplateException {
//		前提：创建一个模板文件
//		第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
//		第二步：设置模板文件所在的路径。
		configuration.setDirectoryForTemplateLoading(new File("E:\\58营\\项目\\workspace\\ebuy-item-web\\src\\main\\webapp\\WEB-INF\\ftl"));
//		第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
//		第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate("user.ftl");
//		第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
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

	}
	
	

	//@Test
	public void testFreemarker1() throws IOException, TemplateException {
//		前提：创建一个模板文件
//		第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
//		第二步：设置模板文件所在的路径。
		configuration.setDirectoryForTemplateLoading(new File("E:\\58营\\项目\\workspace\\ebuy-item-web\\src\\main\\webapp\\WEB-INF\\ftl"));
//		第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
//		第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate("hello.ftl");
//		第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map data = new HashMap();
		data.put("name", "漩涡鸣人");
		data.put("addr", "木叶村！");
//		第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
		Writer writer = new FileWriter("E:\\58营\\项目\\第十天\\temp\\hello.html");
//		第七步：调用模板对象的process方法输出文件。
		template.process(data, writer);
//		第八步：关闭流。
		writer.close();

	}
}
