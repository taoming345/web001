<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
  <div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>新增APP基础信息 <i class="fa fa-user"></i><small>${devUserSession.devName}</small></h2>
             <div class="clearfix"></div>
      </div>
      <div class="x_content">
	  
           <div class="clearfix"></div>
        <form class="form-horizontal form-label-left" action="${pageContext.request.contextPath }/school/addsave" method="post" enctype="multipart/form-data">
          
           <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">图片 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            <input type="file" class="form-control col-md-7 col-xs-12" name="a_picPath"  required="required" id="a_logoPicPath"/>
            ${fileUploadError }
            </div>
          </div>
          
          
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">创建时间 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="APKName" class="form-control col-md-7 col-xs-12" 
              	data-validate-length-range="20" data-validate-words="1" name="createTime"   required="required"
              	placeholder="请输入创建时间" type="text">
            </div>
          </div>
          
          
          <div class="ln_solid"></div>
          <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
              <button id="send" type="submit" class="btn btn-success">保存</button>
              <button type="button" class="btn btn-primary" id="back">返回</button>
              <br/><br/>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
<%-- <script src="${pageContext.request.contextPath }/statics/localjs/appinfoadd.js"></script> --%>