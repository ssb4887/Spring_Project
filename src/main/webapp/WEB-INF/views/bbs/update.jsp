<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

<title>JSP 게시판</title>
<meta http-equiv="Content-Type" content="text/html; Charset=UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="${path}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${path}/resources/css/custom.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${path}/resources/js/bootstrap.js"></script>

</head>
<body>

<jsp:include page="../mainNav.jsp"/>

<!-- 게시글 수정 양식 -->
<div class="container">
	<div class="row">
		<form method="POST" action="./updateAction">
			<input type="hidden" name="bbsID" value="${boarder.boarder_id }">
			<table class="table table-striped" style="text-align: center; border: 1px solid #bbbbbb;">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;">게시물</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width:20%;">제목</td> <!-- 밑에도 똑같이 20%씩 적용 -->
						
						<td><input type="text" class="form-control" name="title"value="${boarder.title }"></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td><input type="hidden" name="writer" value="${boarder.writer }">${boarder.writer }</td>
					</tr>
					<tr>
						<td>작성일</td>
						<td>${boarder.reg_date }</td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea class="form-control" name="content" style="height:350px">${boarder.contents }</textarea></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" class="btn btn-warning" value="수정">
			
		</form>
	</div>
</div>
<!-- 글쓰기 양식 종료 -->


<script>
$(document).ready(function(){
	var msg = '${msg}';
	if(msg != null && msg != '') alert(msg);	
});
</script>

</body>
</html>















