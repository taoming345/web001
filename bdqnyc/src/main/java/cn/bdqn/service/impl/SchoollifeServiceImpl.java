package cn.bdqn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.dao.SchoollifeMapper;
import cn.bdqn.entity.Schoollife;
import cn.bdqn.service.service.SchoollifeService;
@Service("schoollifeService")
public class SchoollifeServiceImpl implements SchoollifeService{

	@Resource
	private SchoollifeMapper schoollifeMapper;

	public SchoollifeMapper getSchoollifeMapper() {
		return schoollifeMapper;
	}

	public void setSchoollifeMapper(SchoollifeMapper schoollifeMapper) {
		this.schoollifeMapper = schoollifeMapper;
	}

	@Override
	public List<Schoollife> getSchoollifeListByPage(Integer currentPageNo, Integer pageSize) {
		
		return schoollifeMapper.getSchoollifeListByPage((currentPageNo-1)*pageSize, pageSize);
	}

	@Override
	public List<Schoollife> getSchoollifeList(Schoollife tSchoollife) {
		
		return schoollifeMapper.getSchoollifeList(tSchoollife);
	}

	@Override
	public int getTotalCount() {
		
		return schoollifeMapper.getTotalCount();
	}

	@Override
	public int delete(Integer uuid) {
		
		return schoollifeMapper.delete(uuid);
	}

	@Override
	public boolean add(Schoollife schoollife) {
		if (schoollifeMapper.add(schoollife)>0) {
			return true;
		}else {
			return false;
		}
		
	}

}
