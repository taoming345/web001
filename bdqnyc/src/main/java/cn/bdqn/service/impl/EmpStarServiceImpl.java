package cn.bdqn.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.EmpStarMapper;
import cn.bdqn.entity.EmpStar;

@Service
public class EmpStarServiceImpl implements EmpStarService{
	
	@Resource
	private EmpStarMapper empStarMapper;
	
	@Override
	public int getCount() {
		int count = 0;
		System.out.println("½øÈë");
		System.out.println(empStarMapper);
		try {
			count=empStarMapper.getCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public EmpStar getEmpStarByUuid(Integer uuid) throws Exception {
		
		return empStarMapper.getEmpStarByUuid(uuid);
	}

	@Override
	public List<EmpStar> getEmpStarList(Integer currentPageNo, Integer pageSize) {
		List<EmpStar> list = new ArrayList();
		try {
			list=empStarMapper.getEmpStarList(currentPageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean addEmpStar(EmpStar empStar) throws Exception {
		boolean flag = false;
		if(empStarMapper.addEmpStar(empStar) > 0){
			flag = true;
		}
		return flag;
	}
	@Override
	public boolean modifyEmpStar(EmpStar empStar) throws Exception {
		boolean flag = false;
		if(empStarMapper.modifyEmpStar(empStar)> 0){
			flag = true;
		}
		return flag;
	}
	@Override
	public boolean deleteEmpStarByUuid(Integer uuid) throws Exception {
		boolean flag = false;
		if(empStarMapper.deleteEmpStarByUuid(uuid) > 0){
			flag = true;
		}
		return flag;
	}

}
