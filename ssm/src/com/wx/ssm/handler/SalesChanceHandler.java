package com.wx.ssm.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wx.ssm.entity.SalesChance;
import com.wx.ssm.entity.User;
import com.wx.ssm.service.SalesChanceService;
import com.wx.ssm.tool.MyPage;

@RequestMapping(value="/saleschance")
@Controller
@Scope(value="prototype")
public class SalesChanceHandler {

	@Autowired
	private SalesChanceService salesChanceService;
	
	@RequestMapping(value="/list")
	public String list(@RequestParam(value="pageNow",required=false,defaultValue="1") int pageNow,Map<String, Object> map){
		MyPage<SalesChance> myPage = new MyPage<SalesChance>();
		myPage.setPageNow(pageNow);
		MyPage<SalesChance> page = salesChanceService.getPage(myPage);
		map.put("page", page);
		return "saleschance/list";
	}
	
	@RequestMapping(value="/addsaleschanceUI")
	public String addSalesChanceUI(Map<String, Object> map){
		map.put("chance", new SalesChance());
		return "saleschance/addsaleschanceUI";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(SalesChance salesChance,HttpSession session){
		User user = (User) session.getAttribute("user");
		salesChance.setCreateBy(user);
		salesChance.setStatus(1);
		salesChanceService.save(salesChance);
		
		return "redirect:/saleschance/list";
	}
	
	@RequestMapping(value="/delete{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable(value = "id") int id){
		
		salesChanceService.delete(id);
		return "redirect:/saleschance/list";
	}
	
	@RequestMapping(value="/updateUI{id}",method=RequestMethod.GET)
	public String updateUI(@PathVariable(value="id") int id,Map<String, Object> map){
		SalesChance salesChance = salesChanceService.getById(id);
		map.put("chance", salesChance);
		return "/saleschance/updatesaleschanceUI";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public String update(SalesChance salesChance){
		salesChanceService.update(salesChance);
		return "redirect:/saleschance/list";
	}
	
	@RequestMapping(value="/dispatcher{id}")
	public String dispatcher(@PathVariable(value="id") int id,Map<String, Object> map){
		SalesChance salesChance = salesChanceService.getById(id);
		map.put("chance", salesChance);
		return "/saleschance/dispatchChance";
	}
}
