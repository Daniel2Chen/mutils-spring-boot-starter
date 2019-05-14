package cn.minsin.pinyin.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import cn.minsin.core.rule.AbstractModelRule;

/**
 * 汉字转拼音
 * 
 * @author minton.zhang
 * @date 2019年5月10日
 * @since 0.0.3.RELEASE
 */
public class PinyinResult extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8001335658359626567L;

	/**
	 * 首字母
	 */
	private Set<String> firstLetter = new HashSet<>();

	/**
	 * 全拼
	 */
	private Set<String> fullLetter = new HashSet<>();

	/**
	 * 汉字 读音
	 */
	private TreeMap<Integer, String[]> multiLetter = new TreeMap<Integer, String[]>();

	/**
	 * 汉字长度
	 */
	private int length;
	
	/**
	 * 需要解析的汉字
	 */
	private String source;

	public PinyinResult(int length,String source) {
		super();
		this.length = length;
		this.source =source;
	}

	public PinyinResult() {
		super();
	}
	
	public String getSource() {
		return source;
	}

	public TreeMap<Integer, String[]> getMultiLetter() {
		return multiLetter;
	}

	public void setMultiLetter(int index, String[] result) {
		this.multiLetter.put(index, result);
	}

	public int getLength() {
		return length;
	}

	public Set<String> getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String[] firstLetter) {
		this.firstLetter.addAll(Arrays.asList(firstLetter));
	}

	public Set<String> getFullLetter() {
		return fullLetter;
	}

	public void setFullLetter(String[] fullLetter) {
		this.fullLetter.addAll(Arrays.asList(fullLetter));
	}

	/**
	 * 解析词组及首字母
	 */
	public synchronized void resolve() {
		int index = 0;
		String[] fillLetter = null;
		String[] firstLetter = null;
		while (true) {
			String[] strings = multiLetter.get(index);
			if (strings == null) {
				break;
			}
			fillLetter = this.fillLetter(fillLetter, strings);
			firstLetter = this.firstLetter(firstLetter, this.subString1(strings));
			if (index >= length) {
				break;
			}
			index++;
		}
		this.setFullLetter(fillLetter);
		this.setFirstLetter(firstLetter);
	}

	/**
	 * 全拼
	 * @param a
	 * @param b
	 * @return
	 */
	private String[] fillLetter(String[] a, String[] b) {
		if(a==null&&b==null) {
			return b;
		}
		if (a == null) {
			return b;
		}
		String[] s = new String[a.length * b.length];
		int index = 0;
		for (String string : a) {
			for (String string2 : b) {
				s[index] = string + " " + string2;
				index++;
			}
		}
		return s;
	}

	/**
	 * 首字母
	 * @param a
	 * @param b
	 * @return
	 */
	private String[] firstLetter(String[] a, String[] b) {
		if(a==null&&b==null) {
			return b;
		}
		if (a == null) {
			return b;
		}
		String[] s = new String[a.length * b.length];
		int index = 0;
		for (String string : a) {
			for (String string2 : b) {
				s[index] = string + string2.substring(0, 1);
				index++;
			}
		}
		return s;
	}

	/**
	 * 截取第一个字母
	 * @param s
	 * @return
	 */
	private String[] subString1(String[] s) {
		String[] result = new String[s.length];
		for (int i = 0; i < s.length; i++) {
			result[i] = s[i].substring(0, 1);
		}
		return result;
	}

}
