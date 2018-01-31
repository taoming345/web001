package cn.bdqn.web.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.bdqn.entity.Teacher;
import cn.bdqn.service.TeacherService;
import cn.bdqn.utils.Constants;
import cn.bdqn.utils.PageSupport;

@Controller
public class TeacherController {
	
	private Logger logger = Logger.getLogger(TeacherController.class);
	
	@Resource
	private TeacherService teacherService;
	
	//列表分页
	@RequestMapping(value="/teacherList.html")
	public String teacherList(Model model,@RequestParam(value="pageIndex",required=false) String pageIndex){
		logger.debug("teacherList>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		int pageSize = Constants.pageSize;
		int currentPageNo = 1;
		if(pageIndex != null){
			currentPageNo = Integer.parseInt(pageIndex);
		}
		int totalCount = teacherService.getTeacherCount();
		
		PageSupport pageSupport = new PageSupport();
		pageSupport.setCurrentPageNo(currentPageNo);
		pageSupport.setPageSize(pageSize);
		pageSupport.setTotalCount(totalCount);
		int totalPageCount = pageSupport.getTotalPageCount();
		
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		List<Teacher> teacherList = teacherService.getTeacherList((currentPageNo-1)*pageSize, pageSize);
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("currentPageNo", currentPageNo);
		model.addAttribute("totalCount", totalCount);
		return "teacherList";
	}
	/*@RequestMapping(value="/teacher.json")
	public String teacherList(Model model,@RequestParam(value="pageIndex",required=false) String pageIndex){
		logger.debug("teacher>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		int pageSize = Constants.pageSize;
		int currentPageNo = 1;
		if(pageIndex != null){
			currentPageNo = Integer.parseInt(pageIndex);
		}
		int totalCount = teacherService.getTeacherCount();
		
		PageSupport pageSupport = new PageSupport();
		pageSupport.setCurrentPageNo(currentPageNo);
		pageSupport.setPageSize(pageSize);
		pageSupport.setTotalCount(totalCount);
		int totalPageCount = pageSupport.getTotalPageCount();
		
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		List<Teacher> teacherList = teacherService.getTeacherList((currentPageNo-1)*pageSize, pageSize);
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("currentPageNo", currentPageNo);
		model.addAttribute("totalCount", totalCount);
		return JSONArray.toJSONString(model);
	}*/
	//通过uuid查询对应的信息
	@RequestMapping(value="/teacherById.html",method=RequestMethod.POST)
	public String teacherById(Model model,@RequestParam Integer uuid){
		logger.debug("teacherById<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		Teacher teacherById = teacherService.getTeacherById(uuid);
		model.addAttribute("teacherById",teacherById);
		return "teacherList";
	}
	/*@RequestMapping(value="/teacherById.json",method=RequestMethod.POST)
	public String teacherById(Model model,@RequestParam Integer uuid){
		logger.debug("teacherById<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		Teacher teacherById = teacherService.getTeacherById(uuid);
		model.addAttribute("teacherById",teacherById);
		return JSONArray.toJSONString(model);
	}*/
	
	//通过uuid删除对应的信息
	@RequestMapping(value="/deleteTeacher.html")
	public String deleteTeacher(Model model,@RequestParam Integer uuid){
		logger.debug("deleteTeacher<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		int deleteTeacher = teacherService.deleteTeacher(uuid);
		model.addAttribute("deleteTeacher",deleteTeacher);
		return "redirect:/teacherList.html";
	}
	
	// 跳转至添加页面
	@RequestMapping(value="/addTeacherInfo.html")
	public String addTeacher(){
		logger.debug("addTeacherInfo<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		return "teacherAdd";
	}
	
	// 添加后保存信息   上传文件
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/addTeacherSave.html",method=RequestMethod.POST)
	public String addTeacherSave(Teacher teacher,HttpServletRequest request,
									@RequestParam(value="picPath",required=false) MultipartFile attach){
		logger.debug("addTeacherSave<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		String idPicPath = null;
		boolean flag = ServletFileUpload.isMultipartContent(request);
		if(flag){
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			List<FileItem> list;
			try {
				list = fileUpload.parseRequest(request);
				for (FileItem fileItem : list) {
					if(fileItem.isFormField()){
						String name = fileItem.getFieldName();
						String value = fileItem.getString("UTF-8");
						if("title".equals(name)){
							teacher.setTitle(value);
						}
						if("profile".equals(name)){
							teacher.setProfile(value);
						}
						if("content".equals(name)){
							teacher.setContent(value);
						}
						if("createTime".equals(name)){
							teacher.setCreateTime(new Date().getTime());
						}
					}else{
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return  "";
		/*String idPicPath = null;
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles"); 
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀     
			int filesize = 500000;
	        if(attach.getSize() >  filesize){	//上传大小不得超过 500k
            	request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
	        	return "teacherAdd";
	        }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
            	String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";  
                File targetFile = new File(path, fileName);  
                if(!targetFile.exists()){  
                    targetFile.mkdirs();  
                }  
                //保存  
                try {  
                	attach.transferTo(targetFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                    request.setAttribute("uploadFileError", " * 上传失败！");
                    return "teacherAdd";
                }  
                idPicPath = path+File.separator+fileName;
            }else{
            	request.setAttribute("uploadFileError", " * 上传图片格式不正确");
            	return "teacherAdd";
            }
		}
		teacher.setCreateTime(new Date().getTime());
		teacher.setPicPath(idPicPath);
		if(teacherService.addTeacher(teacher) > 0){
			return "redirect:/teacherList.html";
		}
		 return "teacherAdd";*/
	}
}
