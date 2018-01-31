package cn.bdqn.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.bdqn.entity.EmpStar;
import cn.bdqn.service.EmpStarService;
import cn.bdqn.service.EmpStarServiceImpl;
import cn.bdqn.utils.Constants;
import cn.bdqn.utils.PageSupport;

@Controller
@RequestMapping(value="/empStar")
public class EmpStarController {

	private Logger logger = Logger.getLogger(EmpStarController.class);
	@Resource
	private EmpStarService empStarService;
	
	@RequestMapping(value="/list")
	public String getCampusInformationByUuid(Model model,
			@RequestParam(value="pageIndex",required=false)Integer pageIndex
			){
		List<EmpStar> empList = null;
		int pageSize =5;
		Integer currentPageNo = 1;
		
		if(pageIndex != null){
			currentPageNo = Integer.valueOf(pageIndex);
		}
		int count =0;
		try {
			count= empStarService.getCount();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(count);
		int totalPageCount = pages.getTotalPageCount();
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		try {
			empList = empStarService.getEmpStarList((currentPageNo-1)*pageSize, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("empList",empList);
		model.addAttribute("currentPageNo",currentPageNo);
		model.addAttribute("totalPageCount",totalPageCount);
		return "empStarList";
	}
		//分页显示咨询列表
		@RequestMapping(value = "/empList.json", method = RequestMethod.GET)
		@ResponseBody
		public List<EmpStar> getCampusInformationList(@RequestParam String pageIndex) {
			int pageSize = 5;
			int currentPageNo = 1;
			int totalCount = 0;
			try {
				totalCount = empStarService.getCount();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	    	
	    	List<EmpStar> empList = null;
			try {
				empList = empStarService.getEmpStarList((currentPageNo-1)*pageSize, pageSize);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return empList;
		}
		
		
		
		//根据id查询
		@RequestMapping(value="/viewEmpStar",method=RequestMethod.GET)
		public String view(@RequestParam Integer uuid,Model model){
			EmpStar empStar=null;
			System.out.println("進入");
			try {
				empStar = empStarService.getEmpStarByUuid(uuid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("empStar",empStar);
			return "empStarList";
		}
		
		//根据id查询
		@RequestMapping(value = "/viewEmpStar.json", method = RequestMethod.GET)
		@ResponseBody
		public EmpStar getview(@RequestParam Integer uuid) {
			EmpStar empStar = null;
			try {
				empStar = empStarService.getEmpStarByUuid(uuid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return empStar;
		}
		//增加
		@RequestMapping(value="/empadd",method=RequestMethod.GET)
		public String addEmpStar(@ModelAttribute("empStar")EmpStar empStar){
			return "empStarAdd";
		}
		@RequestMapping(value = "/addEmpStar")
		public String addSave(EmpStar empStar, HttpSession session, HttpServletRequest request,
				@RequestParam(value = "a_picPath", required = false) MultipartFile attach) {
			String idPicPath = null;
			if(!attach.isEmpty()){
				String path = "\\statics"+File.separator+"uploadfiles"; //文件存放位置
				String oldFileName = attach.getOriginalFilename();//原文件名
				String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀     
				int filesize = 500000;
		        if(attach.getSize() >  filesize){	//上传大小不得超过 500k
	            	request.setAttribute("uploadFileError",Constants.FILEUPLOAD_ERROR_4);
		        	return "empStarAdd";
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
	                    return "empStarAdd";
	                }  
	                idPicPath = path+File.separator+fileName;
	            }else{
	            	request.setAttribute("uploadFileError",Constants.FILEUPLOAD_ERROR_3);
	            	return "empStarAdd";
	            }
			}
			empStar.setCreateTime(new Date().getTime());
			empStar.setPicPath(idPicPath);
		
			try {
				if(empStarService.addEmpStar(empStar)){
					return "redirect:/empStar/list";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return "empStarAdd";
		}
		//删除
		  @RequestMapping(value="/delemp",method=RequestMethod.GET)
		 public Object delEmp(@RequestParam Integer uuid){
		    	System.out.println("删除*************************"+uuid);
				try {
					empStarService.deleteEmpStarByUuid(uuid);
				} catch (Exception e) {
			e.printStackTrace();
				}
		        return "redirect:/empStar/list";
		    }
		  //刷新
		  @RequestMapping(value="/modifyEmp",method=RequestMethod.POST)
		    public String modifyUserSave(EmpStar empStar,HttpSession session,Model model) throws Exception{
		    	System.out.println("保存~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+empStar.getUuid());
		        if(empStarService.modifyEmpStar(empStar)){
		            return "redirect:/empStar/list";
		        }
		        return "modifyEmp";
		    }
}
