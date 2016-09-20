package com.gamasoft.hps.sab.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtils {

	public static <T> List<T> getDuplicate(Collection<T> list) {

	    final List<T> duplicatedObjects = new ArrayList<T>();
	    Set<T> set = new HashSet<T>() {
	    	@Override
	    public boolean add(T e) {
	        if (contains(e)) {
	            duplicatedObjects.add(e);
	        }
	        return super.add(e);
	    }
	    };
	   for (T t : list) {
	        set.add(t);
	    }
	    return duplicatedObjects;
	}


	public static <T> boolean hasDuplicate(Collection<T> list) {
	    if (getDuplicate(list).isEmpty())
	        return false;
	    return true;
	}
}
