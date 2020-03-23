/**
 *
 */
package com.allendowney.thinkdast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of a HashMap using a collection of MyLinearMap and
 * resizing when there are too many entries.
 *
 * @author downey
 * @param <K>
 * @param <V>
 *
 */
public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K, V> {

	// average number of entries per map before we rehash
	protected static final double FACTOR = 1.0;

	@Override
	public V put(K key, V value) {
		V oldValue = super.put(key, value);

//		System.out.println("Put " + key + " in " + maps + " size now " + maps.size());
		
		// check if the number of elements per map exceeds the threshold
		if (size() > maps.size() * FACTOR) {
			rehash();
		}
		return oldValue;
	}

	/**
	 * Doubles the number of maps and rehashes the existing entries.
	 */
	/**
	 *
	 */
	protected void rehash() {
		// TODO: FILL THIS IN!
		
		//����
		List<MyLinearMap<K, V>> oldMaps = maps;
		int newK = maps.size() * 2;
		makeMaps(newK);
		for(MyLinearMap<K, V> map : oldMaps) {
			for(Map.Entry<K, V> entry : map.getEntries()) {
				put(entry.getKey(), entry.getValue());
			}
		}
		
		//�� �ڵ�
//		List<Entry<K, V>> temp = new ArrayList<>();
//		for(MyLinearMap<K, V> map : maps) {
//			for(Entry<K, V> entry : map.getEntries()) {
//				temp.add(entry);
//			}
//		}
//		int k = maps.size() * 2;
//		makeMaps(k);
//		for(Entry<K, V> entry : temp) {
//			super.put(entry.getKey(), entry.getValue());
//		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Integer> map = new MyHashMap<String, Integer>();
		for (int i=0; i<10; i++) {
			map.put(new Integer(i).toString(), i);
		}
		Integer value = map.get("3");
		System.out.println(value);
	}
}
