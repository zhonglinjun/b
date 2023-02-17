package cn.mldn.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author hasee
 *	JDBC 属性配置
 */
public class Config_mysql {

	private static Config_mysql config_mysql; // 声明静态对象
	private static Properties properties; // 声明静态对象

	private Config_mysql() {
		
		String configFile = "sql.properties"; // 数据库配置文件
		
		properties = new Properties(); // 使用已声明静态对象 进行实例化对象
		InputStream is = Config_mysql.class.getClassLoader().getResourceAsStream(configFile);
		/*
		 * Config_mysql.class.getClassLoader() 得到一个类加载器 然后调用 getResourceAsStream
		 * 读取输入流的的资源 返回InputStream
		 */
		try {
			properties.load(is); // 从输入字节流中读取属性列表（键和元素对）。
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Config_mysql getInstance() {
		
		if (config_mysql == null) {
			config_mysql = new Config_mysql(); // 实例化对象
		}
		return config_mysql; // 返回对象名
	}

	// 通过配置文件Key的名称获取到Key的值。
	public String getString(String key) {
		return properties.getProperty(key);// 在此属性列表中搜索具有指定键的属性。
	}
}
