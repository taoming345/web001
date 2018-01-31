package cn.bdqn.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.bdqn.entity.CampusInformation;

public interface CampusInformationService {
		//查询记录总数
		public int getCount();
		//根据id查询咨询
		public CampusInformation getCampusInformationById(Integer uuid);
		//分页显示咨询
		public List<CampusInformation> getCampusInformationList(int currentPageNo,int pageSize);
		
		public int deleteCampusInformationById(Integer uuid);
}
