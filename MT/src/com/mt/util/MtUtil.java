package com.mt.util;

import com.mt.entity.IntArray;

public class MtUtil {

	public static final int ARRAY_LENGTH = 100000;
	public static final int EXPECTED_RESULT = 2;

	public static void checkArraySize(IntArray array){
		if(array == null || array.getLength() == 0){
			throw new IllegalArgumentException("Array can not be empty");
		}
	}
}
