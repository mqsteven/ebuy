package com.igeek;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.container.page.Page;
import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.ebuy.manager.mapper.TbItemMapper;
import com.igeek.ebuy.pojo.TbItem;
import com.igeek.ebuy.pojo.TbItemExample;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月21日 上午11:31:59
 */
public class PageHelperTest {

	//@Test
	public void testPageHelper() {
		//手动创建spring容器 
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemMapper itemMapper = context.getBean(TbItemMapper.class);
		//使用插件 
		//设置了开始位置和每页大小，则插件会将下一个要执行的sql查询进行分页。
		PageHelper.startPage(1, 10);
		TbItemExample e = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(e );
		//将List包装成一个pageInfo
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		System.out.println(pageInfo.getTotal());
		for (TbItem item : list) {
			System.out.println(item.getId()+":"+item.getTitle());
		}
		System.out.println(list.size());
	}
}
