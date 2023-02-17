package cn.mldn.user;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 作者 Jackson Tang
 * @version 创建时间：2022年10月30日 下午4:01:42 类说明:
 */
public class Page<T> {

	private int pageSize; // 每页显示多少条记录
	private int pageNo; // 第几页
	private int pageCount;// 总页数
	private int count; // 总共多少条数据
	private List<T>	list = new ArrayList<T>(); // 声明一个list泛型实例化对象

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		 
		if(count % pageSize == 0)   // 总条数除以每页显示的条数 
		{
			return count / pageSize; // 等于整页数的情况
		}else {
			return (count/pageSize)+1; // 不等于整页数的情况
		}
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	

}
