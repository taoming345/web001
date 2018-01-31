package cn.bdqn.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.dao.EmpMapper;
import cn.bdqn.entity.Emp;
import cn.bdqn.service.service.EmpService;
@Service
public class EmpServiceImpl implements EmpService {
	@Resource
	private EmpMapper empMapper;
	@Override
	public Emp doLogin(String userName, String pwd) throws Exception {
		Emp emp = empMapper.doLogin(userName, pwd);
		return emp;
	}

}
