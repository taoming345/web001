package cn.bdqn.dao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.entity.CampusInformation;

public interface CampusInformationMapper {
	//查询记录总数
	public int getCount();
	//根据id查询咨询
	public CampusInformation getCampusInformationById(@Param("uuid")Integer uuid);
	//分页显示咨询
	public List<CampusInformation> getCampusInformationList(@Param("currentPageNo")Integer currentPageNo,@Param("pageSize")Integer pageSize);

	public int deleteCampusInformationById(@Param("uuid")Integer uuid);
}
