package cn.bdqn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bdqn.dao.dao.TeacherMapper;
import cn.bdqn.entity.Teacher;
import cn.bdqn.service.TeacherService;
@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Resource
	private TeacherMapper teacherMapper;
	
	public TeacherMapper getTeacherMapper() {
		return teacherMapper;
	}

	public void setTeacherMapper(TeacherMapper teacherMapper) {
		this.teacherMapper = teacherMapper;
	}
	/**
	 * 		显示列表  分页查询
	 */
	@Override
	public List<Teacher> getTeacherList(Integer currentPageNo, Integer pageSize) {
		
		return teacherMapper.getTeacherList(currentPageNo, pageSize);
	}
	/**
	 * 		获取列表的总数量
	 */
	@Override
	public int getTeacherCount() {
		
		return teacherMapper.getTeacherCount();
	}
	/**
	 * 		通过uuid获取Teacher
	 */
	@Override
	public Teacher getTeacherById(Integer uuid) {
		
		return teacherMapper.getTeacherById(uuid);
	}
	/**
	 * 		通过uuid删除Teacher
	 */
	@Override
	public int deleteTeacher(Integer uuid) {
		
		return teacherMapper.deleteTeacher(uuid);
	}
	/**
	 * 		添加信息
	 */
	@Override
	public int addTeacher(Teacher teacher) {
		
		return teacherMapper.addTeacher(teacher);
	}

}
