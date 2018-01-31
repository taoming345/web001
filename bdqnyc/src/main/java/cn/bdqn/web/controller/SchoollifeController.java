package cn.bdqn.web.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.bdqn.entity.Schoollife;
import cn.bdqn.service.service.SchoollifeService;
import cn.bdqn.utils.Constants;
import cn.bdqn.utils.PageSupport;

@Controller
@RequestMapping(value="/schoollife")
public class SchoollifeController {

	private Logger logger = Logger.getLogger(SchoollifeController.class);
	@Resource
	private SchoollifeService schoollifeService;
	
	@RequestMapping(value="/schoollife.html")
	public String getSLifeList(Model model,@RequestParam(value="pageIndex",required=false) String pageIndex){
		
    	int pageSize = Constants.pageSize;
    	
    	int currentPageNo = 1;
		
    	if(pageIndex != null){
    		try{
    			currentPageNo = Integer.valueOf(pageIndex);
    		}catch(NumberFormatException e){
    			return "redirect:/user/syserror.html";
    		}
    	}	
    		
    	int totalCount	= schoollifeService.getTotalCount();
    	
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
		
		List<Schoollife> sList = schoollifeService.getSchoollifeListByPage(currentPageNo, pageSize);
		model.addAttribute("sList", sList);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "schoolLife";
	}
	
	@RequestMapping(value="/schoollife.json",method=RequestMethod.GET)
	@ResponseBody
	public String getSLifeListJson(Model model,@RequestParam(value="pageIndex",required=false) String pageIndex){
		
    	int pageSize = Constants.pageSize;
    	
    	int currentPageNo = 1;
		
    	if(pageIndex != null){
    		try{
    			currentPageNo = Integer.valueOf(pageIndex);
    		}catch(NumberFormatException e){
    			return "redirect:/user/syserror.html";
    		}
    	}	
    		
    	int totalCount	= schoollifeService.getTotalCount();
    	
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
		
		List<Schoollife> sList = schoollifeService.getSchoollifeListByPage(currentPageNo, pageSize);
		model.addAttribute("sList", sList);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return JSONArray.toJSONString(model);
	}
	
	@RequestMapping(value="/checkSchoolLife.json",method=RequestMethod.GET)
	@ResponseBody
	public String getScloolLifeByIdJson(@RequestParam("uuid")String uuid,Model model){
		logger.info("getScloolLifeById>>>>>>>>>>>>>>>>>>>>>>>>");
		Schoollife schoollife = new Schoollife();
		schoollife.setUuid(Integer.parseInt(uuid));
		List<Schoollife> schoollifeList = schoollifeService.getSchoollifeList(schoollife);
		model.addAttribute("schoollifeList",schoollifeList);
		return JSONArray.toJSONString(model);
	}
	
	@RequestMapping(value="/checkSchoolLife.html",method=RequestMethod.GET)
	public String getScloolLifeById(@RequestParam("uuid")String uuid,Model model){
		logger.info("getScloolLifeById>>>>>>>>>>>>>>>>>>>>>>>>");
		Schoollife schoollife = new Schoollife();
		schoollife.setUuid(Integer.parseInt(uuid));
		List<Schoollife> schoollifeList = schoollifeService.getSchoollifeList(schoollife);
		model.addAttribute("schoollifeList",schoollifeList);
		return "schoolLife";
	}
	@RequestMapping(value="delschoollife.html")
	public String delete(@RequestParam("uuid")String uuid,Model model){
		int result = schoollifeService.delete(Integer.parseInt(uuid));
		if (result>0) {
			return "redirect:/schoollife/schoollife.html";
		}else{
			return "schoolLife";
		}
	}
	
	@RequestMapping(value="add.html")
	public String add(@ModelAttribute("schoollife") Schoollife schoollife){
		return "schoollifeadd";
	}
	
	@RequestMapping(value="addsave.html",method=RequestMethod.POST)
	public String addsave(Schoollife schoollife,HttpSession session,HttpServletRequest request,
			@RequestParam(value="a_picPath",required= false) MultipartFile attach){
		logger.debug("addschoollifeSave<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		String idPicPath = null;
		if(!attach.isEmpty()){
			String path = "\\statics"+File.separator+"uploadfiles"; 
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀     
			int filesize = 500000;
	        if(attach.getSize() >  filesize){	//上传大小不得超过 500k
            	request.setAttribute("uploadFileError",Constants.FILEUPLOAD_ERROR_4);
	        	return "schoollifeadd";
	        }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
            	String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+".jpg";  
                File targetFile = new File(path, fileName);  
                if(!targetFile.exists()){  
                    targetFile.mkdirs();  
                }  
                //保存  
                try {  
                	attach.transferTo(targetFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                    request.setAttribute("uploadFileError",Constants.FILEUPLOAD_ERROR_2);
                    return "schoollifeadd";
                }  
                idPicPath = path+File.separator+fileName;
            }else{
            	request.setAttribute("uploadFileError",Constants.FILEUPLOAD_ERROR_3);
            	return "schoollifeadd";
            }
		}
		schoollife.setCreateTime(new Date().getTime());
		schoollife.setPicPath(idPicPath);
		if(schoollifeService.add(schoollife)){
			return "redirect:/schoollife/schoollife.html";
		}
		 return "schoollifeadd";
	}

}
