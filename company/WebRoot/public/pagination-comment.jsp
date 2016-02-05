<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div class="panel">
			
	<c:forEach var="com" items="${comments }" varStatus="status" >
	
	<div class="panel-heading">
		<div class="row">
				<div class="col-md-10">
					<div class="row">
						<div class="col-md-2">
							<img id="sm-name" src="
								<%=path %>/${com.user_head_img}
						" class="img-responsive center-block img-circle" alt="图片无效">
						<p class="text-center">
						${com.username }
						</p>
						</div>
						<div class="col-md-10">
							<p>
								${com.content }
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<!-- text-muted：字体颜色的样式 -->
					<p class="text-muted text-right">${com.dateStr}
					</p>
				</div>
			</div>
		
	</div>
	</c:forEach>
	
	
</div>