package cn.minsin.core.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * util的帮助类 更多请参考 {@link org.apache.commons.collections4.ListUtils}
 * 
 * @author minsin
 *
 */
public class ListUtil {

	/**
	 * 创建一个list
	 * 
	 * @return
	 */
	public static <E> ArrayList<E> newArrayList() {
		return new ArrayList<>();
	}

	/**
	 * 创建一个并制定类型
	 * 
	 * @return
	 */
	public static <E> ArrayList<E> newArrayList(Class<E> clazz) {
		if (clazz == null) {
			return newArrayList();
		}
		return new ArrayList<>();
	}

	@SafeVarargs
	public static <E> ArrayList<E> newArrayList(E... elements) {
		checkNotNull(elements);
		int capacity = computeArrayListCapacity(elements.length);
		ArrayList<E> list = new ArrayList<>(capacity);
		Collections.addAll(list, elements);
		return list;
	}

	public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
		ArrayList<E> list = newArrayList();
		addAll(list, elements);
		return list;
	}

	public static <T> boolean addAll(final Collection<T> collection, final Iterator<? extends T> iterator) {
		Objects.requireNonNull(collection);
		Objects.requireNonNull(iterator);
		boolean wasModified = false;
		while (iterator.hasNext()) {
			wasModified |= collection.add(iterator.next());
		}
		return wasModified;
	}

	static int computeArrayListCapacity(int arraySize) {
		checkNonnegative(arraySize, "arraySize");
		return saturatedCast(5L + arraySize + (arraySize / 10));
	}

	static int checkNonnegative(int value, String name) {
		if (value < 0) {
			throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
		}
		return value;
	}

	static long checkNonnegative(long value, String name) {
		if (value < 0) {
			throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
		}
		return value;
	}

	public static <T> T checkNotNull(T reference) {
		if (reference == null) {
			throw new NullPointerException();
		}
		return reference;
	}

	public static int saturatedCast(long value) {
		if (value > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		if (value < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
		return (int) value;
	}

	/**
	 * 如果集合为空 则返回空list 如果不是就返回本身
	 * 
	 * @param list
	 * @return
	 */
	public static <T> List<T> emptyIfNull(final List<T> list) {
		return list == null ? Collections.emptyList() : list;
	}

	/**
	 * 判断集合是否为空 如果为空则返回true 反之为false
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(final List<?> list) {

		return list == null || list.isEmpty() ? true : false;
	}

	/**
	 * 判断数组集合
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(final List<?>... list) {
		for (List<?> list2 : list) {
			if (!isEmpty(list2)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotEmpty(final List<?> list) {

		return !isEmpty(list);
	}

	public static boolean isNotEmpty(final List<?>... list) {
		return !isEmpty(list);
	}
}
