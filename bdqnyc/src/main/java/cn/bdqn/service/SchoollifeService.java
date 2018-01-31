package cn.bdqn.service.service;


import java.util.List;

import cn.bdqn.entity.Schoollife;

public interface SchoollifeService {

	public List<Schoollife> getSchoollifeListByPage(Integer currentPageNo,Integer pageSize);
	
	public List<Schoollife> getSchoollifeList(Schoollife tSchoollife);
	
	public int getTotalCount();
	
	public int delete(Integer uuid);

	public boolean add(Schoollife schoollife);
}
