package com.example;

import java.util.ArrayList;
import java.util.List;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

public class App {

	public static void main(String[] args) {
		CacheFactory.ensureCluster();
		NamedCache cache = CacheFactory.getCache("simple-cache");

		cache.put("k1", "Hello World!");
		cache.put("k2", "Hello India!");

		List<String> keys = new ArrayList<String>();
		keys.add("k1");
		keys.add("k2");
		System.out.println(cache.getAll(keys));

		CacheFactory.shutdown();

	}

}
