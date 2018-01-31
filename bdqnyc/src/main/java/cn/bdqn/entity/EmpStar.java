package cn.bdqn.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.bdqn.utils.FormatUtil;

public class EmpStar {
	private Integer uuid;
	private String title;
	private String profile;
	private String content;
	private String picPath;
	private Long createTime;
	
	private String createTimeView;
	public String getCreateTimeView() {
		return createTimeView;
	}
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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

	
}
