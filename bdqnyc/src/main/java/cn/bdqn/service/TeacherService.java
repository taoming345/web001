package cn.bdqn.service;

import java.util.List;


import cn.bdqn.entity.Teacher;

public interface TeacherService {
	/**
	 * 		显示列表  分页查询
	 */
	public List<Teacher> getTeacherList(Integer currentPageNo,Integer pageSize );
	/**
	 * 		获取列表的总数量
	 */
	public int getTeacherCount();
	/**
	 * 		通过uuid获取Teacher
	 */
	public Teacher getTeacherById(Integer uuid);
	/**
	 * 		通过uuid删除Teacher
	 */
	public int deleteTeacher(Integer uuid);
	/**
	 * 		添加信息
	 */
	public int addTeacher(Teacher teacher);
}
