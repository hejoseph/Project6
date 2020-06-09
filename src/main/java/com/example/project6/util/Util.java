package com.example.project6.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;

public class Util {
private static final Logger logger = LogManager.getLogger("Util");
	
	public static <D,S> D copyObject(S from, Class<D> destClass) {
		JMapperAPI simpleApi = new JMapperAPI().add(JMapperAPI.mappedClass(destClass));
		JMapper simpleMapper = new JMapper(destClass, from.getClass(), simpleApi);
		return (D) simpleMapper.getDestination(from);
	}
	
	public static <D,S> List<D> copyListObject(List<S> fromList, Class<D> destClass) {
		if(fromList.size()==0) {
			return null;
		}
		try {
			JMapperAPI simpleApi = new JMapperAPI().add(JMapperAPI.mappedClass(destClass));
			JMapper simpleMapper = new JMapper(destClass, fromList.get(0).getClass(), simpleApi);
			List<D> result = new ArrayList<D>();
			for(S from : fromList) {
				result.add((D) simpleMapper.getDestination(from));
			}
			return result;
		}catch(Exception e) {
			logger.error("cannot copy list object",e);
		}
		return null;
	}
}
