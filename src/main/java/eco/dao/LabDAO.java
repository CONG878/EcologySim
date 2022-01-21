package eco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import eco.model.DbLoad;
import eco.model.LabVO;

public class LabDAO extends DbLoad {
	// Create
	public void write(LabVO lvo) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConn();
			StringBuffer qry = new StringBuffer().append(" INSERT INTO ecology.lab SET t_i=?, x0=?, x1=?, x2=? ")
					.append(" , h=?, n=?, a00=?, a10=?, a20=?, a21=?, a22=?, bb=? ")
					.append(" , r0=?, r1=?, r2=?, cc=?, dd=?, title=?, description=? ");
			String sql = qry.toString();
			stmt = conn.prepareStatement(sql);

			stmt.setDouble(1, lvo.getT_i());
			stmt.setDouble(2, lvo.getX0());
			stmt.setDouble(3, lvo.getX1());
			stmt.setDouble(4, lvo.getX2());
			stmt.setDouble(5, lvo.getH());
			stmt.setInt(6, lvo.getN());
			stmt.setDouble(7, lvo.getA00());
			stmt.setDouble(8, lvo.getA10());
			stmt.setDouble(9, lvo.getA20());
			stmt.setDouble(10, lvo.getA21());
			stmt.setDouble(11, lvo.getA22());
			stmt.setDouble(12, lvo.getBb());
			stmt.setDouble(13, lvo.getR0());
			stmt.setDouble(14, lvo.getR1());
			stmt.setDouble(15, lvo.getR2());
			stmt.setDouble(16, lvo.getCc());
			stmt.setDouble(17, lvo.getDd());
			stmt.setString(18, lvo.getTitle());
			stmt.setString(19, lvo.getDescription());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbClose(conn, stmt, null);
		}

	}

	// Read

	public ArrayList<LabVO> read(int pg, int ppn) {
		DbLoad dbload = new DbLoad();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<LabVO> al = new ArrayList<LabVO>();

		try {
			StringBuffer qry = new StringBuffer().append(" SELECT xnum,title,description FROM ")
					.append(" lab ORDER BY xnum DESC LIMIT " + ((pg - 1) * ppn) + ", " + ppn);
			String sql = qry.toString();
			// 드라이버 로드
			Class.forName(dbload.getDriver());
			// 연결
			conn = DriverManager.getConnection(dbload.getUrl(), dbload.getUser(), dbload.getPassword());
			// statement 생성
			stmt = conn.prepareStatement(sql);
			// sql 실행
			rs = stmt.executeQuery();
			// Resultset 결과처리
			while (rs.next()) {
				LabVO lvo = new LabVO();

				lvo.setXnum(rs.getInt("xnum"));
				lvo.setTitle(rs.getString("title"));
				lvo.setDescription(rs.getString("description"));

				al.add(lvo);
			}
			// 연결 닫기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}

// R2
	public LabVO read(int xnum) throws Exception {
		DbLoad dbload = new DbLoad();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LabVO lvo = null;

		try {
			StringBuffer qry = new StringBuffer().append(" SELECT t_i, x0, x1, x2, h, n, a00, a10, a20, a21, a22 ")
					.append(", bb, r0, r1, r2, cc, dd, Title, Description FROM ecology.lab WHERE xnum = ? ");
			String sql = qry.toString();
			// 드라이버 로드
			Class.forName(dbload.getDriver());
			// 연결
			conn = DriverManager.getConnection(dbload.getUrl(), dbload.getUser(), dbload.getPassword());
			// statement 생성
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, xnum);
			// sql 실행
			rs = stmt.executeQuery();
			// Resultset 결과처리
			if (rs.next()) {
				lvo = new LabVO();

				// lvo.setXnum(rs.getInt("xnum"));
				lvo.setT_i(rs.getDouble("t_i"));
				lvo.setX0(rs.getDouble("x0"));
				lvo.setX1(rs.getDouble("x1"));
				lvo.setX2(rs.getDouble("x2"));
				lvo.setH(rs.getDouble("h"));
				lvo.setN(rs.getInt("n"));
				lvo.setA00(rs.getDouble("a00"));
				lvo.setA10(rs.getDouble("a10"));
				lvo.setA20(rs.getDouble("a20"));
				lvo.setA21(rs.getDouble("a21"));
				lvo.setA22(rs.getDouble("a22"));
				lvo.setBb(rs.getDouble("bb"));
				lvo.setR0(rs.getDouble("r0"));
				lvo.setR1(rs.getDouble("r1"));
				lvo.setR2(rs.getDouble("r2"));
				lvo.setCc(rs.getDouble("cc"));
				lvo.setDd(rs.getDouble("dd"));
				lvo.setTitle(rs.getString("title"));
				lvo.setDescription(rs.getString("description"));
			}
			if (lvo == null) {
				throw new Exception("에러");
			}

			// 연결 닫기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lvo;
	}

	public int pCounting() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalRows = 0;
		StringBuffer qry = new StringBuffer().append(" SELECT COUNT(*) FROM ecology.lab ");

		String sql = qry.toString();
		try {
			// 드라이브로드
			// 디비연결
			conn = getConn();
			// statement 생성
			stmt = conn.prepareStatement(sql);
			// sql실행
			rs = stmt.executeQuery();

			if (rs.next()) {
				totalRows = rs.getInt("COUNT(*)");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbClose(conn, stmt, null);
		}
		return totalRows;
	}
}