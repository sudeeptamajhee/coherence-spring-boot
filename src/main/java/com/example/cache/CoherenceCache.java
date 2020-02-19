package com.example.cache;

import java.util.Collections;
import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceCache implements Cache {

	private NamedCache cache;
	private String name;
	
	public CoherenceCache(String name){
		this.name = name;
		this.cache = CacheFactory.getCache(name);
	}
	
	@Override
	public void clear() {
		this.cache.clear();
	}

	@Override
	public void evict(Object key) {
		this.cache.remove(key);		
	}

	@Override
	public ValueWrapper get(Object key) {
		Object value = this.cache.get(key);
		return value != null ? new SimpleValueWrapper(value) : null;
	}

	@Override
	public <T> T get(Object arg0, Class<T> arg1) {
		return null;
	}

	@Override
	public <T> T get(Object arg0, Callable<T> arg1) {
		return null;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return this.cache;
	}

	@Override
	public void put(Object key, Object value) {
		if(value == null){
			return;
		}
		this.cache.putAll(Collections.singletonMap(key, value));
	}

}
