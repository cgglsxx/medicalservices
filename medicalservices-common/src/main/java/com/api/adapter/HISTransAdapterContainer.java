package com.api.adapter;

import com.api.result.GlobalErrorInfoException;
import com.api.result.messageenum.GlobalErrorInfoEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 适配器容器
 */
public class HISTransAdapterContainer {
	static Map<String,Object> adapterMap = new HashMap<String,Object>();
	
	public static AHISTransAdapter getAdapter(String key) throws GlobalErrorInfoException {
		try {
			return (AHISTransAdapter) adapterMap.get(key);
		} catch (Exception e) {
			throw new GlobalErrorInfoException(GlobalErrorInfoEnum.ADAPTER_ERROR);
		}
	}
	
	public static void putAdapter(String key, Object adapter){
		adapterMap.put(key, adapter);
	}
	
}
