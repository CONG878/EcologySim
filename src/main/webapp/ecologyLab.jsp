<%@page import="eco.equation.FoodWeb"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
/*
가상 실험실
곰, 늑대, 토끼의 시간에 따른 개체수 변화를 확인
3종의 초기 개체수와 11개의 상수를 입력

*/
double[] tx_i = new double[4];
int n;
double h;
double a00;
double a10;
double a20;
double a21;
double a22;
double bb;
double r0;
double r1;
double r2;
double cc;
double dd;
String title;
String description;
try {
	title = request.getParameter("title");
	description = request.getParameter("description");

	double x0 = Double.parseDouble(request.getParameter("x0"));
	double x1 = Double.parseDouble(request.getParameter("x1"));
	double x2 = Double.parseDouble(request.getParameter("x2"));
	// 곰, 늑대, 토끼의 개체수
	double t_i = Double.parseDouble(request.getParameter("t_i"));
	n = Integer.parseInt(request.getParameter("n"));
	h = Double.parseDouble(request.getParameter("h"));
	// 초기 시간, 점의 수, 시간 간격
	tx_i[0] = t_i;
	tx_i[1] = x0;
	tx_i[2] = x1;
	tx_i[3] = x2;

	//int n = (int) ((t - t_i) / h);

	a00 = Double.parseDouble(request.getParameter("a00"));
	a10 = Double.parseDouble(request.getParameter("a10"));
	a20 = Double.parseDouble(request.getParameter("a20"));
	a21 = Double.parseDouble(request.getParameter("a21"));
	a22 = Double.parseDouble(request.getParameter("a22"));
	// 곰의 영역 다툼
	// 곰이 늑대를 살해하는 비율
	// 곰이 토끼를 사냥하는 비율
	// 늑대가 토끼를 사냥하는 비율
	// 토끼의 포화 한계 상수
	bb = Double.parseDouble(request.getParameter("bb"));
	r0 = Double.parseDouble(request.getParameter("r0"));
	r1 = Double.parseDouble(request.getParameter("r1"));
	r2 = Double.parseDouble(request.getParameter("r2"));
	cc = Double.parseDouble(request.getParameter("cc"));
	dd = Double.parseDouble(request.getParameter("dd"));
	// 토끼 외의 식량
	// 곰의 번식력
	// 늑대의 번식력
	// 토끼의 번식력
	// 곰의 포화 한계 상수
	// 늑대의 포화 한계 상수
} catch (Exception e) {
	title = "실험 제목";
	description = "설명을 입력 해 주세요.";

	double x0 = 600.0;
	double x1 = 4200.0;
	double x2 = 30000.0;

	double t_i = 0.0;
	n = 50;
	h = 0.002;

	tx_i[0] = t_i;
	tx_i[1] = x0;
	tx_i[2] = x1;
	tx_i[3] = x2;

	//int n = (int) ((t - t_i) / h);

	a00 = 0.15 * Math.random();// 곰의 영역 다툼
	a10 = 0.02 * Math.random();// 곰이 늑대를 살해하는 비율
	a20 = 0.01 * Math.random();// 곰이 토끼를 사냥하는 비율
	a21 = 0.002 * Math.random();// 늑대가 토끼를 사냥하는 비율
	a22 = 0.002 * Math.random();// 토끼의 포화 상수
	bb = 6000 * Math.random();// 토끼 외의 식량
	r0 = 1.5 * Math.random();// 곰의 번식력
	r1 = 10 * Math.random();// 늑대의 번식력
	r2 = 50 * Math.random();// 토끼의 번식력
	cc = 0.2 * Math.random();// 곰의 포화 상수
	dd = 0.5 * Math.random();// 늑대의 포화 상수
}
%>


<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		/* var data = google.visualization.arrayToDataTable([
				[ 'Time', 'Bear', 'Wolf', 'Rabbit' ], [ 8, 12, 18, 10 ],
				[ 4, 5.5, 10, 10 ], ]); */

		var data = google.visualization
				.arrayToDataTable([
						[ 'Time', 'Bear', 'Wolf', 'Rabbit' ],
<%out.print(Arrays.toString(tx_i));
FoodWeb d2 = new FoodWeb();
double[] tx = tx_i;
for (int i = 0; i < n; i++) {
	tx = d2.RK4(tx, h, a00, a10, a20, a21, a22, bb, r0, r1, r2, cc, dd);

	out.print("," + Arrays.toString(tx));
}%>
	]);

		var options = {
			title : 'Time vs. Population comparison',
			hAxis : {
				title : 'Time'
			},
			vAxis : {
				title : 'Population'
			}
		};

		var chart = new google.visualization.ScatterChart(document
				.getElementById('chart_div'));

		chart.draw(data, options);
	}
</script>
</head>
<body>
	<form action="" method="get">
		<div>
			<fieldset>
				<legend>초기 개체수</legend>
				곰 <input id="x0" name="x0" value="<%=tx_i[1]%>">늑대 <input
					id="x1" name="x1" value="<%=tx_i[2]%>">토끼 <input id="x2"
					name="x2" value="<%=tx_i[3]%>">
			</fieldset>
			<fieldset>
				<legend>시간</legend>
				시작 <input id="t_i" name="t_i" value="<%=tx_i[0]%>"> 간격 <input
					id="h" name="h" value="<%=h%>"> 점의 수 <input id="n" name="n"
					value="<%=n%>">
			</fieldset>
		</div>
		<fieldset>
			<legend>실험 상수</legend>
			곰의 영역 다툼<input id="a00" name="a00" value="<%=a00%>"> 곰의 늑대
			살해율<input id="a10" name="a10" value="<%=a10%>"> 곰의 토끼 포식율<input
				id="a20" name="a20" value="<%=a20%>"> 늑대의 토끼 포식율<input
				id="a21" name="a21" value="<%=a21%>"> <br>토끼의 먹이 경쟁<input
				id="a22" name="a22" value="<%=a22%>">토끼 외의 먹이<input id="bb"
				name="bb" value="<%=bb%>"> 곰의 번식율<input id="r0" name="r0"
				value="<%=r0%>"> 늑대의 번식율<input id="r1" name="r1"
				value="<%=r1%>"> 토끼의 번식율<input type="number" id="r2"
				name="r2" value="<%=r2%>"> <br> 곰의 먹이 경쟁<input id="cc"
				name="cc" value="<%=cc%>"> 늑대의 먹이 경쟁<input id="dd" name="dd"
				value="<%=dd%>">
		</fieldset>
		<div>
			<button type="submit">Calculate</button>
			<button type="reset">Reset</button>
			<button type="button" onclick="location.href='EcologyLab'">Randomize</button>
		</div>
		<div id="chart_div" style="width: 900px; height: 500px;"></div>
		<div>
			제목<input id="title" name="title" value="<%=title%>"> 실험 설명
			<textarea id="description" name="description"><%=description%></textarea>
			<button type="button" onclick="registXP(this.form)">실험 등록</button>
			<button type="button" onclick="location.href='EcologyList'">실험
				목록으로</button>
		</div>
	</form>
	<script>
		function registXP(f) {
			f.method = "post";
			//f.action = "";
			f.submit();
		}
	</script>
</body>
</html>