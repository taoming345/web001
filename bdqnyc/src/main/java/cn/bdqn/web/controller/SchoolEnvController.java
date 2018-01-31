package cn.bdqn.web.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bdqn.entity.SchoolEnv;
import cn.bdqn.service.SchoolEnvService;
import cn.bdqn.utils.Constants;
import cn.bdqn.utils.PageSupport;

@Controller
@RequestMapping("/school")
public class SchoolEnvController {
	
	@Resource
	private SchoolEnvService schoolService;
	
	
	
	private Logger logger = Logger.getLogger(SchoolEnvController.class);
	
	@RequestMapping(value="/list")
	/*@ResponseBody*/
	public String select(Model model,HttpSession session,
						   @RequestParam(value="pageIndex",required=false) String pageIndex) {
		
		
		logger.info("页面下标:"+pageIndex);
		
		List<SchoolEnv> schoolEnvs = null;
		
		//页面容量
		int pageSize = Constants.pageSize;
		//当前页码
		Integer currentPageNo = 1;
		
		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		//总数量
		int totalcount = 0;
		try {
			totalcount = schoolService.count();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//总页面
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalcount);
		int totalPageCount = pages.getTotalPageCount();
		
		//控制首页和尾页
		if(currentPageNo < 1) {
			currentPageNo = 1;
			
		}else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		
		schoolEnvs = schoolService.getAllEnv(currentPageNo, pageSize);
		
		model.addAttribute("schoolEnvs", schoolEnvs);
		model.addAttribute("pages", pages);
		
		
		return "test";
	}
	
	@RequestMapping(value="/delete")
	public String deleteByuuid(@RequestParam(value="uuid") Integer uuid) {
		
		try {
			schoolService.deleteByuuid(uuid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "redirect:/school/list";
	}
	
	@RequestMapping(value="/add")
	public String add(@ModelAttribute("schoolEnv")SchoolEnv schoolEnv) {
		
		
		return "useradd";
	}
	
	
	
	@RequestMapping(value="/addsave",method=RequestMethod.POST)
	public String addTeacherSave(SchoolEnv schoolEnv,HttpServletRequest request,
			@RequestParam(value="a_picPath",required= false) MultipartFile attach){
		logger.debug("addSchoolEnv<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		String idPicPath = null;
		if(!attach.isEmpty()){
			String path = "\\statics"+File.separator+"uploadfiles"; 
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀     
			int filesize = 500000;
	        if(attach.getSize() >  filesize){	//上传大小不得超过 500k
            	request.setAttribute("uploadFileError",Constants.FILEUPLOAD_ERROR_4);
	        	return "useradd";
	        }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
            	String fileName = RandomUtils.nextInt(1000000)+".jpg";  
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
                    return "useradd";
                }  
                idPicPath = path+File.separator+fileName;
            }else{
            	request.setAttribute("uploadFileError",Constants.FILEUPLOAD_ERROR_3);
            	return "useradd";
            }
		}
		schoolEnv.setCreateTime(new Date().getTime());
		schoolEnv.setPicPath(idPicPath);
		try {
			if(schoolService.add(schoolEnv)){
				return "redirect:/school/list";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "useradd";
	}

	
	
	
	
	
	
	
	
}
       