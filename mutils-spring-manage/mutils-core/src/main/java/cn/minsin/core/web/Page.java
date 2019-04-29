/**
 * 
 */
package cn.minsin.core.web;

import java.util.ArrayList;
import java.util.List;

import cn.minsin.core.rule.AbstractModelRule;

/**
 * <b style="color:red">layer数据表格的格式验证</b><br>
 * <b style="color:red">在controller层接收请使用page和limit进行接收页码和行数</b>
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 * @param <E> 泛型
 */
public class Page<E> extends AbstractModelRule{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5796659299635955253L;

	private int code;

	private String msg;
	
	private long count;

	private List<E> data;
	
	private long totalpage;
	
	private long nowpage;
	
	public long getTotalpage() {
		
		return totalpage;
	}

	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
	}

	public long getNowpage() {
		return nowpage;
	}

	public void setNowpage(long nowpage) {
		this.nowpage = nowpage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}
	

	public Page(int count, List<E> data,int nowpage,int limit) {
		this.code =0;
		this.msg="请求成功";
		this.count = count;
		this.data = data;
		this.nowpage =nowpage;
		this.totalpage =Page.getMaxPage(count, limit);
	}

	/**
	 * 检查page 是否满足条件
	 * 
	 * @param page
	 * @param def
	 * @return
	 */
	public static  int checkPage(Integer page, int def) {
		return  (page==null||page < 1) ? def : page;
	}

	/**
	 * 检查pagesize是否满足条件
	 * 
	 * @param limit
	 * @param def
	 * @return
	 */
	public static  int checkLimit(Integer limit, int def) {
		return (limit==null||limit < 1) ? def : limit;
	}

	/**
	 * 得到分页开始下标
	 * @param page
	 * @param limit
	 * @param def
	 * @return
	 * 2018年8月15日
	 * @author  mintonzhang@163.com
	 */
	public static int getStartIndex(Integer page, Integer limit, int def) {
		page = checkPage(page, 1);
		limit = checkLimit(limit, 10);
		return (page - 1) * limit < 0 ? def : (page - 1) * limit;
	}
	/**
	 * 得到最大页数
	 * @param page
	 * @param limit
	 * @param def
	 * @return
	 * 2018年8月15日
	 * @author  mintonzhang@163.com
	 */
	public static int getMaxPage(int total, int limit) {
		return total%limit==0?total/limit:(total/limit)+1;
	}
	
	/**
	 * 某些特殊情况不能再数据库分页 采用自行分页
	 * @param list
	 * @param page
	 * @param limit
	 * @return
	 * 2018年12月5日
	 * @author  mintonzhang@163.com
	 */
	public static <T> List<T> getList(List<T> list,Integer page,Integer limit){
		
		try {
			int size = list.size();
			if (size < limit) { 
				return list;
			} else if (page * limit > size) {
				return new ArrayList<>(list.subList((page - 1) * limit,size));
			} else {
				return  new ArrayList<>(list.subList((page - 1) * limit, limit * page));
			}
		}catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
