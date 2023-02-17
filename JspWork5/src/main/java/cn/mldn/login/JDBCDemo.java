package cn.mldn.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCDemo {
	String name;
	String password;

	public String getName() // get方法
	{

		return name;
	}

	public void setName(String name) // set方法
	{
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	String sql = "select count(*) as num from user_table where username = ? and password = ?";
	// 这条SQL使用前先在数据库验证一下
	public int query() throws Exception {
		
		Connection conn = getConnct();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name); // 序号从1开始
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		int num = rs.getInt("num"); // 获取别名查询的结果集
		
		return num;
		
	}
	
	public Connection getConnct() throws Exception {
		
		String Driver = PropertiesUtil.getValue("jdbc.driver.class"); // 获取属性配置赋值给String变量
		String Url = PropertiesUtil.getValue("jdbc.connection.url1");  // 获取属性配置赋值给String变量
		String Root = PropertiesUtil.getValue("jdbc.connection.username"); // 获取属性配置赋值给String变量
		String Password = PropertiesUtil.getValue("jdbc.connection.password");

		Connection conn = null;
		Class.forName(Driver);
		conn = DriverManager.getConnection(Url, Root, Password);
		if (conn != null) {
			System.out.println("数据库连接成功");
		}
		return conn;
	}
}

