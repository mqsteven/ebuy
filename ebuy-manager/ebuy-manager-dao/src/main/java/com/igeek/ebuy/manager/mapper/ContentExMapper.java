package com.igeek.ebuy.manager.mapper;

import java.util.List;
import java.util.Map;

import com.igeek.ebuy.pojo.TbContent;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月26日 下午3:26:02
 */
public interface ContentExMapper {

	public List<TbContent> queryByPage(Map map);
	
	public int queryCount(Map map);
}
