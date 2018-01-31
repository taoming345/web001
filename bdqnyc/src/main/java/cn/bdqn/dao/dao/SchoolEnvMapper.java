package cn.bdqn.dao.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.entity.SchoolEnv;

public interface SchoolEnvMapper {
	
	
	//查找所有信息
	public List<SchoolEnv> getAllEnv(@Param(value="from")Integer currentPageNo,
										@Param(value="pageSize")Integer pageSize
										
			);
	
	//查找信息条数
	public Integer count()throws Exception;
	

	//根据uuid删除信息
	public Integer deleteByuuid(@Param(value="uuid")Integer uuid) throws Exception;
	
	
	//添加信息
	/*public List<SchoolEnv> add(@Param(value="uuid")Integer uuid,@Param(value="picPath")String picPath,@Param(value="createTime")Integer createTime)throws Exception;*/
	public boolean add(SchoolEnv schoolEnv)throws Exception;
	
}
