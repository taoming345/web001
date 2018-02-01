<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<div class="clearfix"></div>
<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel">
		<div class="x_content">
			<p class="text-muted font-13 m-b-30"></p>
			<div id="datatable-responsive_wrapper"
				class="dataTables_wrapper form-inline dt-bootstrap no-footer">
				<div class="row">
					<div class="col-sm-12">
					<a href="${pageContext.request.contextPath}/sys/add.html" class="btn btn-success btn-sm">新增客户体验卡</a>
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="First name: activate to sort column descending"
										aria-sort="ascending">软件名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										APK名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										软件大小(单位:M)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										所属平台</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										创建日期</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 124px;"
										aria-label="Last name: activate to sort column ascending">
										操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="customercardlist" items="${customerCardList}">
									<tr>
										<td>${customercardlist.name }</td>
										<td>${customercardlist.tele }</td>
										<td>${customercardlist.qq }</td>
										<td>${customercardlist.email }</td>
										<td>${customercardlist.createTimeView }</td>
										<td>
											<div class="btn-group">
							                      <button type="button" class="btn btn-danger">点击操作</button>
							                      <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
							                        <span class="caret"></span>
							                        <span class="sr-only">Toggle Dropdown</span>
							                      </button>
							                      <ul class="dropdown-menu" role="menu">
							                        <li><a href="${pageContext.request.contextPath}/sys/add.html">新增</a></li>
													<li><a class="deletecustomercard" href="${pageContext.request.contextPath }/sys/delCustomerCard.html?id=${customercardlist.uuid }">删除</a></li>
							                      </ul>
	                    					</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<input type="hidden" id="totalPageCount" value="${pages.totalPageCount}"/>
				<div class="row">
					<div class="col-sm-5">
						<div class="dataTables_info" id="datatable-responsive_info"
							role="status" aria-live="polite">共${pages.totalCount }条记录
							${pages.currentPageNo }/${pages.totalPageCount }页</div>
					</div>
					<div class="col-sm-7">
						<div class="dataTables_paginate paging_simple_numbers"
							id="datatable-responsive_paginate">
							<ul class="pagination">
								<c:if test="${pages.currentPageNo > 1}">
									<li class="paginate_button previous"><a
										href="${pageContext.request.contextPath }/sys/customer_card.html?pageIndex=1"
										aria-controls="datatable-responsive" data-dt-idx="0"
										tabindex="0">首页</a>
									</li>
									<li class="paginate_button "><a
										href="${pageContext.request.contextPath }/sys/customer_card.html?pageIndex=${pages.currentPageNo-1}"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">上一页</a>
									</li>
								</c:if>
								<c:if test="${pages.currentPageNo < pages.totalPageCount }">
									<li class="paginate_button "><a
										href="${pageContext.request.contextPath }/sys/customer_card.html?pageIndex=${pages.currentPageNo+1}"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">下一页</a>
									</li>
									<li class="paginate_button next"><a
										href="${pageContext.request.contextPath }/sys/customer_card.html?pageIndex=${pages.totalPageCount }"
										aria-controls="datatable-responsive" data-dt-idx="7"
										tabindex="0">最后一页</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
<script src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
<script src="${pageContext.request.contextPath }/statics/localjs/appinfolist.js"></script>