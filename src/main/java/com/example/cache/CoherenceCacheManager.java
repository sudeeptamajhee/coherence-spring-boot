package com.example.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class CoherenceCacheManager implements CacheManager, DisposableBean, InitializingBean {

	private ConcurrentMap<String, CoherenceCache> cacheMap = new ConcurrentHashMap<String, CoherenceCache>();
	private Set<String> cacheNames = new LinkedHashSet<String>();

	public CoherenceCacheManager() {
		CacheFactory.ensureCluster();
	}

	public CoherenceCacheManager(Set<String> cacheNames) {
		this.cacheNames = cacheNames;
		CacheFactory.ensureCluster();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.cacheMap.clear();
		if (this.cacheNames.isEmpty()) {
			return;
		}
		
		for(String name : cacheNames){
			CoherenceCache cache = new CoherenceCache(name);
			this.cacheMap.put(name, cache);
		}
	}

	@Override
	public void destroy() throws Exception {
		for(CoherenceCache cache : cacheMap.values()){
			CacheFactory.releaseCache((NamedCache)cache.getNativeCache());
		}
	}

	@Override
	public Cache getCache(String name) {
		return this.cacheMap.get(name);
	}

	@Override
	public Collection<String> getCacheNames() {
		return Collections.unmodifiableSet(this.cacheNames);
	}

}
