<%@page import="java.util.Iterator"%>
<%@page import="eco.model.LabVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String session_id = (String) session.getAttribute("loginId");
String search = request.getParameter("search");

int pg = (int) request.getAttribute("pg");
int ppn = (int) request.getAttribute("ppn");
int lastPage = (int) request.getAttribute("lastPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시판 목록</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 내부  -->
<link rel="stylesheet" href="css/mainStyle.css" />
</head>
<body>
	<div>
		<header></header>
		<main>
			<section>
				<article>
					<div class="title_outer">
						<h2>실험 목록</h2>
					</div>
					<div>
						<table class="table">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>실험 설명</th>
								</tr>
							</thead>
							<tbody>
								<%
								ArrayList<LabVO> al = (ArrayList) request.getAttribute("list");
								Iterator<LabVO> it = al.iterator();
								while (it.hasNext()) {
									LabVO lvo = it.next();
								%>
								<tr>
									<th><label><a
											href="EcologyView?xnum=<%=lvo.getXnum()%>"><%=lvo.getXnum()%></a></label></th>
									<th><label><a
											href="EcologyView?xnum=<%=lvo.getXnum()%>"><%=lvo.getTitle()%></a></label></th>
									<th><label><a
											href="EcologyView?xnum=<%=lvo.getXnum()%>"><%=lvo.getDescription()%></a></label></th>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</article>
			</section>
		</main>
		<footer>
			<nav>
				<ul class="pager">
					<li class=""><a href="?page=1">첫 페이지</a></li>
					<%
					if (pg != 1) {
					%>
					<li class=""><a href="?page=<%=pg - 1%>">Previous</a></li>
					<%
					}
					for (int i = Math.max(1, Math.min(pg - 2, lastPage - 4)); i <= Math.min(lastPage, Math.max(pg + 2, 5)); i++) {
					if (i != pg) {
					%>
					<li class="page-item"><a href="?page=<%=i%>"><%=i%></a></li>
					<%
					} else {
					%>
					<li class="page-item active"><a href="?page=<%=i%>"><%=i%></a></li>
					<%
					}
					}
					if (pg != lastPage) {
					%>
					<li class=""><a href="?page=<%=pg + 1%>">Next</a></li>
					<%
					}
					%>
					<li class=""><a href="?page=<%=lastPage%>">마지막 페이지</a></li>
				</ul>
			</nav>
			<button style="flaot: right;" type="button"
				class="btn btn-default col-md-1"
				onclick="location.href = 'EcologyLab'">실험 하러 가기</button>

		</footer>
	</div>
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/list.js"></script>
</body>
</html>