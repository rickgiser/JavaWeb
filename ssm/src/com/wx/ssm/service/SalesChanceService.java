package com.wx.ssm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.ssm.dao.SalesChanceMapper;
import com.wx.ssm.entity.SalesChance;
import com.wx.ssm.tool.MyPage;

@Service
public class SalesChanceService {

	@Autowired
	private SalesChanceMapper salesChanceMapper;
	
	@Transactional(readOnly=true)
	public MyPage<SalesChance> getPage(MyPage<SalesChance> myPage){
		int startIndex = (myPage.getPageNow() - 1) * myPage.getPageSize() + 1;
		int endIndex = startIndex + myPage.getPageSize() - 1;
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("startIndex", startIndex);
		map.put("endIndex", endIndex);
		List<SalesChance> content = salesChanceMapper.getContent(map);
		int totalNum = salesChanceMapper.getTotalNum();
		myPage.setContent(content);
		myPage.setTotalNum(totalNum);
		return myPage;
	}
	
	@Transactional
	public void save(SalesChance salesChance){
		salesChanceMapper.save(salesChance);
	}
	
	@Transactional
	public void delete(int id){
		salesChanceMapper.delete(id);
	}
	
	@Transactional
	public SalesChance getById(int id){
		SalesChance salesChance = salesChanceMapper.getById(id);
		return salesChance;
	}
	
	@Transactional
	public void update(SalesChance salesChance){
		salesChanceMapper.update(salesChance);
	}
}
