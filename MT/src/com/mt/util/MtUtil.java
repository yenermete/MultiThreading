package com.mt.util;

import com.mt.entity.IntArray;

public class MtUtil {

	public static final int ARRAY_LENGTH = 100000;
	public static final int EXPECTED_RESULT = 2;
	public static final int INCREMENT_WITH_SYNCHRONIZED_THIS_PARAMETER = 0;
	public static final int INCREMENT_WITH_SYNCHRONIZED_METHOD = 1;
	public static final int INCREMENT_WITH_SYNCHRONIZED_ARRAY_PARAMETER = 2;
	public static final int INCREMENT_WITH_SYNCHRONIZED_MUTEX_PARAMETER = 3;
	public static final int INCREMENT_WITH_REENTRANT_LOCK = 4;

	public static void checkArraySize(IntArray array){
		if(array == null || array.getLength() == 0){
			throw new IllegalArgumentException("Array can not be empty");
		}
	}
}
