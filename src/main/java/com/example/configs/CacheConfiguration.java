package com.example.configs;

import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.cache.CoherenceCacheManager;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

@Configuration
@EnableCaching
public class CacheConfiguration {
	
	public CacheConfiguration(){
		System.out.println("@@@@@@@@ CacheConfiguration @@@@@@@@");
	}

	@Bean
	public NamedCache getCache(){
		return CacheFactory.getCache("simple-cache");
	}
	
	@Bean("cacheManager")
	public CacheManager getCacheManager(){
		Set<String> cacheNames = new HashSet<String>();
		cacheNames.add("simple-cache");
		CoherenceCacheManager cacheManager = new CoherenceCacheManager(cacheNames);
		return cacheManager;
	}
}
