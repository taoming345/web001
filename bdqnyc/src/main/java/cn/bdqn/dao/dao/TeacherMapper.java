package cn.bdqn.dao.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.entity.Teacher;

public interface TeacherMapper {
	/**
	 * 		显示列表  分页查询
	 */
	public List<Teacher> getTeacherList(@Param("currentPageNo")Integer currentPageNo,
												@Param("pageSize")Integer pageSize );
	/**
	 * 		获取列表的总数量
	 */
	public int getTeacherCount();
	/**
	 * 		通过uuid获取Teacher
	 */
	public Teacher getTeacherById(@Param("uuid") Integer uuid);
	/**
	 * 		通过uuid删除Teacher
	 */
	public int deleteTeacher(@Param("uuid") Integer uuid);
	/**
	 * 		添加信息
	 */
	public int addTeacher(Teacher teacher);
}
