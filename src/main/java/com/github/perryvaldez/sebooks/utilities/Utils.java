package com.github.perryvaldez.sebooks.utilities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Utils {
	public static <T> List<T> castList(List<?> list) {
		@SuppressWarnings("unchecked")
		var typedList = list.stream().map(item -> (T) item).collect(Collectors.toList());	
		return typedList;
	}
	
	public static <T> List<T> listDifference(List<T> a, List<T> b) {
		Set<T> setA = new HashSet<T>(a);
		Set<T> setB = new HashSet<T>(b);
		
		setA.removeAll(setB);
		return setA.stream().collect(Collectors.toList()); 
	}
}
