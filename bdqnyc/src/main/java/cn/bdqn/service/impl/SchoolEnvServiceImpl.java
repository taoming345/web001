package cn.bdqn.service.school.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.dao.SchoolEnvMapper;
import cn.bdqn.entity.SchoolEnv;
import cn.bdqn.service.SchoolEnvService;

@Service
public class SchoolEnvServiceImpl implements SchoolEnvService{
	
	@Resource
	private SchoolEnvMapper schoolMapper;

	public List<SchoolEnv> getAllEnv(Integer currentPageNo, Integer pageSize) {
		
		return schoolMapper.getAllEnv((currentPageNo-1)*pageSize, pageSize);
	}

	public Integer count() throws Exception {

		
		return schoolMapper.count();
	}

	public Integer deleteByuuid(Integer uuid) throws Exception {
		int count = schoolMapper.deleteByuuid(uuid);
		
		return count;
	}

	public boolean add(SchoolEnv schoolEnv) throws Exception {
		
		return schoolMapper.add(schoolEnv);
	}

	

	

}
