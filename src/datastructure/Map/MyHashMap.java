package datastructure.Map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 简单的hashmap
 * @param <k>
 * @param <v>
 */
public class MyHashMap<k,v> implements Map<k,v> {
	final ReadWriteLock lock=new ReentrantReadWriteLock();
	final Lock r=lock.readLock();
	final Lock w=lock.writeLock();
	final Map<k,v>map;

	public MyHashMap(Map<k, v> map) {
		this.map = map;
		if(map==null) throw new NullPointerException();
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		r.lock();
		try {
			return map.containsKey(key);
		}finally {
			r.unlock();
		}
	}

	@Override
	public boolean containsValue(Object value) {
		r.lock();
		try{
			return map.containsValue(value);
		}finally {
			r.unlock();
		}
	}

	@Override
	public v get(Object key) {
		r.lock();
		try {
			return map.get(key);
		}finally {
			r.unlock();
		}
	}

	@Override
	public v put(k key, v value) {
		w.lock();
		try {
			return map.put(key,value);
		}finally {
			w.unlock();
		}

	}

	@Override
	public v remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends k, ? extends v> m) {

	}

	@Override
	public void clear() {
		w.lock();
		try{
			map.clear();
		}finally {
			w.unlock();
		}
	}

	@Override
	public Set<k> keySet() {
		return null;
	}

	@Override
	public Collection<v> values() {
		return null;
	}

	@Override
	public Set<Entry<k, v>> entrySet() {
		return null;
	}
}