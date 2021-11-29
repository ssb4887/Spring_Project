<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 로그인 되었을 때 해당 유저의 id가 저장될 변수
	String userId = null;

	// 세션에 userId 이름을 가지는 value가 존재하면
	// userId 변수에 해당 value를 저장
	// 로그인 상태 확인
	if(session.getAttribute("userId") != null) {
		userId = (String) session.getAttribute("userId"); //문자형변환
	}
%>    
<!DOCTYPE html>
<html>
<head>

<title>JSP 게시판</title>
<meta http-equiv="Content-Type" content="text/html; Charset=UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/custom.css">
</head>
<body>

<!-- 메인 네비게이션 -->
<!-- nav: 네비게이션 리스트 만들어주는 HTML 태그 -->
<nav class="navbar navbar-default">
	<!-- div : 웹 페이지에서 공간을 지정하는 HTML 태그 -->
	<!-- 네비게이션 헤더 -->
	<div class="navbar-header">
	<!-- button : 버튼을 만들어주는 HTML 태그 -->
		<button type="button" class="navbar-toggle collapsed" 
		data-toggle="collapse" data-target="#bs-navbar-collapse" 
		aria-expanded="false">
		<!-- span : 웹 페이지에서 공간을 지정하는 HTML 태그(컨텐츠 사이즈 가로) -->
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		</button>
		<!-- a : url을 이동시켜주는 태그 href="" 속서에 경로 지정 -->
		<a class="navbar-brand" href="main.jsp">JSP 게시판</a>
	</div>
	<!-- 네비게이션 헤더 종료-->
	
	<!-- 네비게이션 메뉴 -->
	<div class="collapse navbar-collapse" id="bs-navbar-collapse">
		<!-- ul : 순서가 없는 리스트를 만들어주는 태그 -->
		
		<!-- 메인 메뉴 -->
		<ul class="nav navbar-nav">
			<!-- li : 리스트 하나의 요소를 만들어주는 HTML 태그 -->
			<li class="active"><a href="main.jsp">메인</a></li>
			<li><a href="bbs.jsp">게시판</a></li>
		</ul>
		<!-- 메인 메뉴 종료-->
		
		<!-- 마이페이지 메뉴 -->
		<!-- 로그인이 되있지 않을 때 표시 -->
		<%
			if(userId == null) {
		%>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" 
					data-toggle="dropdown" role="button" 
					aria-haspopup="true" aria-expanded="false">
					마이페이지<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="login.jsp">로그인</a></li>	
					<li><a href="join.jsp">회원가입</a></li>
				</ul>
			</li>
		</ul>
		<%
			} else {
		%>
		
		
		<!-- 로그인이 되었을 때 표시 -->
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" 
					data-toggle="dropdown" role="button" 
					aria-haspopup="true" aria-expanded="false">
					마이페이지<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="logout.jsp">로그아웃</a></li>	
				</ul>
			</li>
		</ul>
		<%
			}
		%>
		<!-- 마이페이지 메뉴 종료 -->
	</div>
	<!-- 네비게이션 메뉴 종료 -->
	
</nav>
<!-- 메인 네비게이션 종료 -->

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
</body>
</html>