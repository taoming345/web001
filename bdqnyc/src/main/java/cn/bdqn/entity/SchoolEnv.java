package cn.bdqn.entity;

import cn.bdqn.utils.FormatUtil;

public class SchoolEnv {
	
	private Integer uuid;
	private String picPath;
	private Long createTime;
	
	private String createTimeView;
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
		this.createTimeView = FormatUtil.formatDate(createTime);
	}
	public String getCreateTimeView() {
		return createTimeView;
	}
}
