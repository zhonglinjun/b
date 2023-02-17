package cn.mldn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author hasee 类介绍：通过jdbc配置属性文件 连接数据库
 */
public class Query {

	public static Connection getConnection() throws Exception {
		Connection conn = null; //申明一个Connection 对象,此对象不能放入try catch里面，否则不能返回
		
		
		String driver = Config_mysql.getInstance().getString("jdbc.driver.class");
		String url = Config_mysql.getInstance().getString("jdbc.connection.url");
		String username = Config_mysql.getInstance().getString("jdbc.connection.username");
		String password = Config_mysql.getInstance().getString("jdbc.connection.password");
		// 读取sql.properties配置文件中jdbc.driver.class值
		
		try {
			
			// 载入数据库驱动
			Class.forName(driver);
			// 建立数据库连接getconnection(jdbc:mysql://地址:端口号/数据库名,数据库用户名，密码)
			conn = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
