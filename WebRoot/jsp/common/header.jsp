<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="navbar">
	    <div class="navbar-header">
	      	<h3><a href="/menu.html">鴛鴦戲水后台管理系统</a></h3>
	    </div>
	    <c:if test="${user!=null}">
	    	<nav class="collapse navbar-collapse bs-navbar-collapse">
		    	<ul class="nav navbar-nav navbar-right">
		        	<li><a href="javascript:void(0)">${user.nickName}</a></li>
		        	<li><a href="/mgnpwdupdate.html">设置</a></li>
		         	<li><a href="/logout.html">登出</a></li>
		      	</ul>
	    	</nav>
	    </c:if>
	</div>
</div>