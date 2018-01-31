package cn.bdqn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.entity.EmpStar;

public interface EmpStarService {

	public int getCount()throws Exception;
	//查询所有 根据id 
	public EmpStar getEmpStarByUuid(@Param("uuid")Integer uuid) throws Exception;
	//分页
	public List<EmpStar> getEmpStarList(@Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize)throws Exception;
	//增加
	public boolean addEmpStar(EmpStar empStar)throws Exception;
	
	public boolean modifyEmpStar(EmpStar empStar)throws Exception;
	//删除
	public boolean deleteEmpStarByUuid(@Param(value="uuid")Integer uuid)throws Exception;
}
