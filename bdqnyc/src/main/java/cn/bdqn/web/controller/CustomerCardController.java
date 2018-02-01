package cn.bdqn.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bdqn.entity.CustomerCard;
import cn.bdqn.service.service.CustomerCardService;
import cn.bdqn.utils.PageSupport;


@Controller
public class CustomerCardController {
	@Resource
	private CustomerCardService customerCardService;
	//分页查询
	@RequestMapping(value="/sys/customer_card.html")
	public String getAllCustomerCard(CustomerCard customerCard,Model model,@RequestParam(value="pageIndex",required=false) String pageIndex){
		List<CustomerCard> customerCardList = null;
		//页面容量
		int pageSize = 5;
		//当前页码
		Integer currentPageNo = 1;
		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		//总数量（表）
		int totalCount = 0;
		try {
			totalCount = customerCardService.getCustomerCardCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		//控制首页和尾页
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		try {
			customerCardList = customerCardService.getAllCustomerCard(currentPageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("customerCardList", customerCardList);
		model.addAttribute("pages", pages);
		
		return "customercard/customercardlist";
	}
	@RequestMapping(value="/sys/add.html")
	public String add(){
		return "customercard/customercardadd";
	}
	//添加用户体验卡
	@RequestMapping(value="/sys/addCustomerCard.html")
	public String addCustomerCard(CustomerCard customerCard){
		customerCard.setCreateTime(new Date().getTime());
		try {
			customerCardService.addCustomerCard(customerCard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sys/customer_card.html";
	}
	//删除用户体验卡
	@RequestMapping(value="/sys/delCustomerCard.html")
	public String delCustomerCard(@RequestParam(value="id")String id){
		try {
			customerCardService.deleteCustomerCardByUuid(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sys/customer_card.html";
	}
}
