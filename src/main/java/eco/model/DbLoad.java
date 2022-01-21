package eco.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbLoad {
	String url = "jdbc:mysql://localhost:3306/ecology?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	String user = "root";
	String password = "smart";
	String driver = "com.mysql.cj.jdbc.Driver";

	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void DbClose(Connection conn, Statement stmt, ResultSet rs) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
}
