package cn.bdqn.dao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.entity.Schoollife;


public interface SchoollifeMapper {

	public List<Schoollife> getSchoollifeListByPage(@Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize);
	public List<Schoollife> getSchoollifeList(Schoollife tSchoollife);
	public int getTotalCount();
	public int delete(@Param("uuid") Integer uuid);
	public int add(Schoollife schoollife);
}
