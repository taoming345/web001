package cn.bdqn.dao.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.entity.CustomerCard;

public interface CustomerCardMapper {
	//分页查询用户体验卡
	public List<CustomerCard> getAllCustomerCard(@Param(value="from")Integer currentPageNo,
												@Param(value="pageSize")Integer pageSize)throws Exception;
	//获取列表总数
	public int getCustomerCardCount()throws Exception;
	//添加用户体验卡
	public int addCustomerCard(CustomerCard custormerCard)throws Exception;
	//通过uuid删除用户体验卡
	public int delCustomerCardByUuid(@Param(value="uuid")Integer id)throws Exception;
}
