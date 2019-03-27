package com.github.perryvaldez.sebooks.utilities;

import java.util.List;
import java.util.stream.Collectors;

public final class Utils {
	public static <T> List<T> castList(List<?> list) {
		@SuppressWarnings("unchecked")
		var typedList = list.stream().map(item -> (T) item).collect(Collectors.toList());	
		return typedList;
	}
}
