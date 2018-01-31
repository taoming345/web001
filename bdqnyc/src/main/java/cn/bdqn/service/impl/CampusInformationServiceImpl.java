package cn.bdqn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.dao.CampusInformationMapper;
import cn.bdqn.entity.CampusInformation;
import cn.bdqn.service.CampusInformationService;



@Service
public class CampusInformationServiceImpl implements CampusInformationService {
	
	
	@Resource
	private CampusInformationMapper mapper;
	
	
	public CampusInformationMapper getMapper() {
		return mapper;
	}

	public void setMapper(CampusInformationMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public CampusInformation getCampusInformationById(Integer uuid) {
		CampusInformation campusInformation=mapper.getCampusInformationById(uuid);
		return campusInformation;
	}

	@Override
	public List<CampusInformation> getCampusInformationList(int currentPageNo, int pageSize) {
		List<CampusInformation> campusInformationList=mapper.getCampusInformationList(currentPageNo, pageSize);
		return campusInformationList;
	}

	@Override
	public int getCount() {
		int count=0;
		count=mapper.getCount();
		return count;
	}

	@Override
	public int deleteCampusInformationById(Integer uuid) {
		// TODO Auto-generated method stub
		return mapper.deleteCampusInformationById(uuid);
	}

}
