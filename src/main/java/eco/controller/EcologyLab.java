package eco.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eco.dao.LabDAO;
import eco.model.LabVO;

/**
 * Servlet implementation class EcologyLab
 */
@WebServlet("/EcologyLab")
public class EcologyLab extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EcologyLab() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("ecologyLab.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		double[] tx_i = new double[4];

		double x0 = Double.parseDouble(request.getParameter("x0"));
		double x1 = Double.parseDouble(request.getParameter("x1"));
		double x2 = Double.parseDouble(request.getParameter("x2"));
		// 곰, 늑대, 토끼의 개체수
		double t_i = Double.parseDouble(request.getParameter("t_i"));
		int n = Integer.parseInt(request.getParameter("n"));
		double h = Double.parseDouble(request.getParameter("h"));
		// 초기 시간, 점의 수, 시간 간격
		tx_i[0] = t_i;
		tx_i[1] = x0;
		tx_i[2] = x1;
		tx_i[3] = x2;

		// int n = (int) ((t - t_i) / h);

		double a00 = Double.parseDouble(request.getParameter("a00"));
		double a10 = Double.parseDouble(request.getParameter("a10"));
		double a20 = Double.parseDouble(request.getParameter("a20"));
		double a21 = Double.parseDouble(request.getParameter("a21"));
		double a22 = Double.parseDouble(request.getParameter("a22"));
		// 곰의 영역 다툼
		// 곰이 늑대를 살해하는 비율
		// 곰이 토끼를 사냥하는 비율
		// 늑대가 토끼를 사냥하는 비율
		// 토끼의 포화 한계 상수
		double bb = Double.parseDouble(request.getParameter("bb"));
		double r0 = Double.parseDouble(request.getParameter("r0"));
		double r1 = Double.parseDouble(request.getParameter("r1"));
		double r2 = Double.parseDouble(request.getParameter("r2"));
		double cc = Double.parseDouble(request.getParameter("cc"));
		double dd = Double.parseDouble(request.getParameter("dd"));
		// 토끼 외의 식량
		// 곰의 번식력
		// 늑대의 번식력
		// 토끼의 번식력
		// 곰의 포화 한계 상수
		// 늑대의 포화 한계 상수

		String title = request.getParameter("title");
		String description = request.getParameter("description");
		LabVO lvo = new LabVO();
		lvo.setT_i(tx_i[0]);
		lvo.setX0(tx_i[1]);
		lvo.setX1(tx_i[2]);
		lvo.setX2(tx_i[3]);
		lvo.setN(n);
		lvo.setH(h);
		lvo.setA00(a00);
		lvo.setA10(a10);
		lvo.setA20(a20);
		lvo.setA21(a21);
		lvo.setA22(a22);
		lvo.setBb(bb);
		lvo.setR0(r0);
		lvo.setR1(r1);
		lvo.setR2(r2);
		lvo.setCc(cc);
		lvo.setDd(dd);
		lvo.setTitle(title);
		lvo.setDescription(description);
		new LabDAO().write(lvo);
		response.sendRedirect("EcologyList");
	}
}
