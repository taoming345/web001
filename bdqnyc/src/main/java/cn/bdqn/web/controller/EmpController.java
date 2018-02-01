package cn.bdqn.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bdqn.entity.Emp;
import cn.bdqn.service.service.EmpService;
import cn.bdqn.utils.Constants;

@Controller
public class EmpController {
	@Resource
	private EmpService empService;
	
	@RequestMapping(value="/empLogin.html")
	public String doLogin(@RequestParam(value="userName")String userName,@RequestParam(value="pwd")String pwd,HttpSession session){
		Emp emp = null;
		try {
			emp = empService.doLogin(userName, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null != emp){
			session.setAttribute(Constants.EMP_SESSION, emp);
			return "redirect:/sys/main.html";
		}else{
			return "devlogin";
		}
	}
	@RequestMapping(value="/login.html")
	public String toMain(){
		return "devlogin";
	}
	@RequestMapping(value="/sys/main.html")
	public String loginSuccess(){
		return "login/main";
	}
	//注销
	@RequestMapping(value="/empLogout.html")
	public String logout(HttpSession session){
		//清除session
		session.removeAttribute(Constants.EMP_SESSION);
		return "devlogin";
	}
}
