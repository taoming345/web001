package cn.bdqn.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

import cn.bdqn.entity.CampusInformation;
import cn.bdqn.service.CampusInformationService;
import cn.bdqn.utils.Constants;
import cn.bdqn.utils.PageSupport;




@Controller
@RequestMapping(value = "/CampusInformation")
public class CampusInformationController {
	
	private Logger logger = Logger.getLogger(CampusInformationController.class);
	
	@Resource
	private CampusInformationService campusInformationService;
	//分页显示咨询列表
	@RequestMapping(value = "/list")
	public String getCampusInformationList(Model model,@RequestParam(value="pageIndex",required=false) String pageIndex) {
		int pageSize = Constants.pageSize;
		int currentPageNo = 1;
		int totalCount	= campusInformationService.getCount();
		PageSupport pages=new PageSupport();
    	pages.setCurrentPageNo(currentPageNo);
    	pages.setPageSize(pageSize);
    	pages.setTotalCount(totalCount);
    	int totalPageCount = pages.getTotalPageCount();
    	
    	if(currentPageNo < 1){
    		currentPageNo = 1;
    	}else if(currentPageNo > totalPageCount){
    		currentPageNo = totalPageCount;
    	}
    	
    	List<CampusInformation> campusInformationList=campusInformationService.getCampusInformationList((currentPageNo-1)*pageSize, pageSize);		
    	model.addAttribute("campusInformationList", campusInformationList); 	
    	model.addAttribute("pages", pages);

    	return "appinfolist";
	}
	//分页显示咨询列表
	@RequestMapping(value = "/campusInformationList.json", method = RequestMethod.GET)
	@ResponseBody
	public List<CampusInformation> getCampusInformationList(@RequestParam String pageIndex) {
		int pageSize = 5;
		int currentPageNo = 1;
		int totalCount	= campusInformationService.getCount();
		PageSupport pages=new PageSupport();
    	pages.setCurrentPageNo(currentPageNo);
    	pages.setPageSize(pageSize);
    	pages.setTotalCount(totalCount);
    	int totalPageCount = pages.getTotalPageCount();
    	
    	if(currentPageNo < 1){
    		currentPageNo = 1;
    	}else if(currentPageNo > totalPageCount){
    		currentPageNo = totalPageCount;
    	}
    	
    	List<CampusInformation> campusInformationList=campusInformationService.getCampusInformationList((currentPageNo-1)*pageSize, pageSize);

		return campusInformationList;
	}
	
	
	
	//根据id查询
	@RequestMapping(value="/view/{id}",method=RequestMethod.GET)
	public String view(@PathVariable Integer uuid,Model model){
		CampusInformation campusInformation = campusInformationService.getCampusInformationById(uuid);
		model.addAttribute(campusInformation);
		return "campusInformationview";
	}
	
	//根据id查询
	@RequestMapping(value = "/viewcampusInformation", method = RequestMethod.GET)
	@ResponseBody
	public CampusInformation getview(@RequestParam Integer uuid) {
		CampusInformation campusInformation = campusInformationService.getCampusInformationById(uuid);
		return campusInformation;
	}
	
	//删除
	
/*	@RequestMapping(value = "/viewcampusInformation", method = RequestMethod.GET)
	@ResponseBody
	public Object deleteById(@RequestParam Integer uuid) {
		int count= campusInformationService.deleteCampusInformationById(uuid);
		HashMap<String, String> map = new HashMap<String,String>();
			if(count>0)
				map.put("uuid","exist");
			else
				map.put("uuid","noexist");
		
		return JSON.toJSON(map);
	}*/
	
	
}
