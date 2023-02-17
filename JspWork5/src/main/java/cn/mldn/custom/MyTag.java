package cn.mldn.custom;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.mldn.jdbc.Query;

/**
 * @author 作者 Jackson Tang
 * @version 创建时间：2022年10月26日 下午9:25:50 
 * 类说明:增删改查加入 自定义下拉框
 */
public class MyTag extends SimpleTagSupport {
	private Integer id;
	private String addr;
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<MyTag> getMyTags() {
		Query query = new Query();
		List<MyTag> list = new ArrayList();
		try {
			Connection conn = query.getConnection(); // 获取Connection 连接
			String sql = "select id,addr from addr";
			// 预编译查询SQL
			PreparedStatement psmt = conn.prepareStatement(sql);
			// 执行查询SQL
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				MyTag mytag = new MyTag();
				mytag.setId(rs.getInt("id"));
				mytag.setAddr(rs.getString("addr"));
				list.add(mytag);// 将对象添加到list当中
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void doTag() throws JspException, IOException // 核心功能
	{
		List<MyTag> list = getMyTags();
		JspWriter out = getJspContext().getOut();

		StringBuffer buffer = new StringBuffer(); // StringWriter 虽然也有append方法里面但是没有String类型

		buffer.append("<select ").append("name=").append("addr").append(">");
	
		if(value == null || value.equals("")) 
		{
			for (MyTag mytag : list) {
				buffer.append("<option value=").append(mytag.getAddr()).append(">").append(mytag.getAddr()).append("</option>");
			}
			
		}else {
			for(MyTag mytag :list) {
				
				if(mytag.getAddr().equals(value))
				{
					buffer.append("<option value=").append(mytag.getAddr()).append(" selected>").append(mytag.getAddr()).append("</option>");
				}else {
					
					buffer.append("<option value=").append(mytag.getAddr()).append(">").append(mytag.getAddr()).append("</option>");
				}
			}
			
		}		buffer.append("</select>");
		
		out.println(buffer.toString());
	}
}
