package cn.bdqn.service.service;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.entity.Emp;

public interface EmpService {
	//员工登录
	public Emp doLogin(@Param(value="userName")String userName,@Param(value="pwd")String pwd)throws Exception;
}
