package cn.mldn.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.mldn.user.Page;
import cn.mldn.user.User;

public class UserDao {

	public void add(User user) throws Exception {

		Connection conn = Query.getConnection();
		String sql = "insert into user(username,password,addr,sex) values(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getAddr());
		ps.setString(4, user.getSex());

		ps.execute();

	}

	public void delete(int id) throws Exception {

		Connection conn = Query.getConnection();
		String sql = "delete from user where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ps.execute();
	}

	public void update(User user) throws Exception {
		Connection conn = Query.getConnection();
		String sql = "update user set username = ?, password = ?, addr = ?,sex = ? where id = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getAddr());
		ps.setString(4, user.getSex());
		ps.setInt(5, user.getId());

		ps.execute();
	}

	public User queryId(int id) throws Exception {

		User user = new User();

		Connection conn = Query.getConnection();
		String sql = "select * from user where id=? ";
		

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();

		int id1 = rs.getInt(1);
		String name = rs.getString(2);
		String addr = rs.getString(3);
		String sex = rs.getString(4);
		String password = rs.getString(5);

		user.setId(id1);
		user.setUsername(name);
		user.setAddr(addr);
		user.setSex(sex);
		user.setPassword(password);

		return user;

	}

//	public List<User> query() throws Exception //修改前
//	{
//		List<User> list = new ArrayList<User>();
//		
//		Connection conn = Query.getConnection();
//		String sql = "select * from user";
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ResultSet rs = ps.executeQuery();
//
//		while (rs.next()) {
//			User u = new User();
//			u.setId(rs.getInt("id"));
//			u.setUsername(rs.getString("username"));
//			u.setPassword(rs.getString("password"));
//			u.setAddr(rs.getString("addr"));
//			u.setSex(rs.getString("sex"));
//
//			list.add(u);
//		}
//
//		return list;
//	}
	public void query(Page<User> page) throws Exception //修改后
	{
		String sql1 = "select count(*)as num from user"; //查询总数量(也就是总页数)
		
		Connection conn = Query.getConnection();
		PreparedStatement pstm = conn.prepareStatement(sql1);
		ResultSet rst = pstm.executeQuery();
		rst.next();
		int count = rst.getInt("num");
		
		page.setCount(count);       // 设置Page类总页数的属性变量
		
//						( 当前所在的页 - 1 ) * 单页显示数量 = 当页显示的起始下标
		int s = (page.getPageNo()-1) * page.getPageSize();
		
		List<User> list = new ArrayList<User>();	// 声明一个list实例化对象，并指定list中的元素类型
		String sql = "select * from user limit ?,?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, s);					// 设置第一个占位符 起始下标
		pstmt.setInt(2, page.getPageSize()); // 设置第二个占位符 每页显示的数量
		
		ResultSet rs = pstmt.executeQuery();  

		while (rs.next()) {
			User u = new User();
			u.setId(rs.getInt("id")); 				 // 获取属性并设置对象u的属性id
			u.setUsername(rs.getString("username")); // 获取属性并设置对象u的属性username
			u.setPassword(rs.getString("password")); // 获取属性并设置对象u的属性password
			u.setAddr(rs.getString("addr"));		 // 获取属性并设置对象u的属性addr
			u.setSex(rs.getString("sex"));			 // 获取属性并设置对象u的属性sex

			list.add(u); 							 // 将对象u添加到list末尾处 
		}
		
		page.setList(list);							 // 设置Page类的中的list的set
		
	}
}
