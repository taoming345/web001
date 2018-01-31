package cn.bdqn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.dao.CustomerCardMapper;
import cn.bdqn.entity.CustomerCard;
import cn.bdqn.service.service.CustomerCardService;
@Service
public class CustomerCardServiceImpl implements CustomerCardService {
	@Resource
	private CustomerCardMapper customerCardMapper;
	
	public List<CustomerCard> getAllCustomerCard(Integer currentPageNo, Integer pageSize) throws Exception {
		List<CustomerCard> list = customerCardMapper.getAllCustomerCard((currentPageNo-1)*pageSize, pageSize);
		return list;
	}

	public int getCustomerCardCount() throws Exception {
		int count = customerCardMapper.getCustomerCardCount();
		return count;
	}

	public int addCustomerCard(String name, String tele, String qq, String email) throws Exception {
		int count = customerCardMapper.addCustomerCard(name, tele, qq, email);
		return count;
	}

	public int deleteCustomerCardByUuid(Integer id) throws Exception {
		int count = customerCardMapper.delCustomerCardByUuid(id);
		return count;
	}

}
