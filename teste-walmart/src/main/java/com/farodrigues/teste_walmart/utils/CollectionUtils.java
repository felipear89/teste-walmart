package com.farodrigues.teste_walmart.utils;

import java.util.Set;

public class CollectionUtils {

	public static <T> T find(Set<T> conjunto, T value) {
		for(T t : conjunto) {
			if (t.equals(value)) {
				return t;
			}
		}
		return null;		
	}
}
