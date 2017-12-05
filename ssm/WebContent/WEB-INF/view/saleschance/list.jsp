<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>销售机会管理</title>
</head>

<body class="main">
	<form action="" method="post" id="hiddenForm">
		<input type="hidden" value="delete" name="_method">
	</form>
	<form id="command" action="${ctp }/chance/list" method="post">
		<div class="page_title">
			销售机会管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new">
				新建
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
			<c:if test="${empty page.content }">
			没有数据
			</c:if>
			<c:if test="${!empty page.content }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						客户名称
					</th>
					<th>
						概要
					</th>
					<th>
						联系人
					</th>
					<th>
						联系人电话
					</th>
					<th>
						创建时间
					</th>
					<th>
						操作
					</th>
				</tr>
					<c:forEach items="${page.content }" var="item">
					<tr>
						<td class="list_data_number">${item.id }</td>
						<td class="list_data_text">${item.custName }</td>
						<td class="list_data_text">${item.title }</td>
						<td class="list_data_text">${item.contact }</td>
						<td class="list_data_text">${item.contactTel }</td>
						<td class="list_data_text">
							<fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href = '${ctp}/saleschance/dispatcher${item.id }'" 
								title="指派" src="#" class="op_button" />
							<img onclick="window.location.href = '${ctp}/saleschance/updateUI${item.id }'" 
								title="编辑" src="${ctp }/static/images/bt_edit.gif"
								class="update" />
							<input type="hidden" value="${item.id }">
							<img onclick="#" 
								title="删除" src="${ctp }/static/images/bt_del.gif" class="delete" />
							<input type="hidden" value="${item.custName }">
							</td>
					</tr>
					</c:forEach>
				
			</table>	
			<div style="text-align:right; padding:6px 6px 0 0;">
				共 ${page.totalNum } 条记录 
				&nbsp;&nbsp;
				
				当前第 ${page.pageNow } 页/共 ${page.pageCount }  页
				&nbsp;&nbsp;
					<c:if test="${page.hasPrev }">
					<a href='?pageNow=1'>首页</a>
					&nbsp;&nbsp;
					<a href='?pageNow=${page.prev }'>上一页</a>
					&nbsp;&nbsp;
					</c:if>
				
				&nbsp;&nbsp;
					<c:if test="${page.hasNext }">
					<a href='?pageNow=${page.next }'>下一页</a>
					&nbsp;&nbsp;
					<a href='?pageNow=${page.pageCount }'>末页</a>
					&nbsp;&nbsp;
					</c:if> 
				
				转到 <input id="pageNow" size='1'/> 页
				&nbsp;&nbsp;
			
			</div>
			<script type="text/javascript" src="${ctp }/static/jquery/jquery-1.9.1.min.js"></script>
			<script type="text/javascript">
			
				$(function() {
					$("#pageNow").change(function(){
						var pageNow = $(this).val();
						var reg = /^\d+$/;
						if(!reg.test(pageNow)){
							alert("您输入的数字格式不对!");
							$(this).val("");
							return ;
						}
						
						var pageNow2 = parseInt(pageNow);
						if(pageNow2 < 1 || pageNow2 > parseInt("${page.pageCount}")){
							alert("页数范围不对!");
							$(this).val("");
							return ;
						}
						
						window.location.href = window.location.pathname + '?pageNow=' + pageNow;
					})
				})
				
				$(function(){
					$("#new").click(function(){
						window.location.href = "${ctp}/saleschance/addsaleschanceUI";
						return false;
					})
				})
				
				$(function(){
					$(".delete").click(function(){
						var id = $(this).prev(":hidden").val();
						var custName = $(this).next(":hidden").val();
						var flag = confirm("你确定要删除"+custName+"这个销售机会吗?");
						if(flag){
							alert(id+" "+custName);
							var url = "${ctp}/saleschance/delete" + id;
							$("#hiddenForm").attr("action",url).submit();
						}
						return false;
					})
				})
				
			</script>
			</c:if>
	</form>
	
</body>
</html>
