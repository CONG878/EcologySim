package eco.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eco.dao.LabDAO;
import eco.model.LabVO;

/**
 * Servlet implementation class EcologyList
 */
@WebServlet("/EcologyList")
public class EcologyList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EcologyList() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pg = 1; // 현재페이지
		int ppn = 10; // 페이지에 보여줄 갯수

		String pgN = request.getParameter("page");
		try {
			pg = Integer.parseInt(pgN);
		} catch (Exception e) {
			pg = 1;
		}

		ArrayList<LabVO> al = new LabDAO().read(pg, ppn);
		request.setAttribute("list", al);

		int totalRows = new LabDAO().pCounting();
		int lastPage = ((totalRows - 1) / ppn) + 1;

		totalRows = totalRows - ((pg - 1) * ppn);
		request.setAttribute("pg", pg);
		request.setAttribute("ppn", ppn);
		request.setAttribute("totalRows", totalRows);
		request.setAttribute("lastPage", lastPage);

		RequestDispatcher dispatcher = request.getRequestDispatcher("ecologyList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
