package com.mt.threads;

import com.mt.entity.IntArray;
import com.mt.util.MtUtil;

public class ArrayThread extends Thread {

	private IntArray array;
	private final boolean increment;
	private final boolean arrayIncrement;
	private final boolean arraySynchIncrement;
	private final boolean mutex;

	/**
	 * This is a rather not efficient constructor, but for this scenario there
	 * was no need to have multiple constructors. Initially, array is checked
	 * against being null. If it is null, an IllegalArgumentException is thrown
	 * 
	 * @param array
	 *            IntArray object, which contains an array of primitive
	 *            integers.
	 * @param increment
	 *            When true, increasing array values is achieved with
	 *            {@link IntArray#increment}
	 * @param arrayIncrement
	 *            When true, increasing array values is achieved with
	 *            {@link IntArray#arrayIncrement}
	 * @param arraySynchIncrement
	 *            When true, increasing array values is achieved with
	 *            {@link IntArray#arrayIncrement}
	 * @param mutex
	 *            When true, lock is achieved with a dummy mutex object.
	 * @throws IllegalArgumentException
	 *             If the {@link IntArray} object is null or its {@code array}
	 *             value is null.
	 * @author Yener
	 * */
	public ArrayThread(IntArray array, boolean increment, boolean arrayIncrement, boolean arraySynchIncrement, boolean mutex) {
		MtUtil.checkArraySize(array);
		this.array = array;
		this.increment = increment;
		this.arrayIncrement = arrayIncrement;
		this.arraySynchIncrement = arraySynchIncrement;
		this.mutex = mutex;
	}

	private void increment(int index) {
		if (increment) {
			array.increment(index);
		} else if (arrayIncrement) {
			array.arrayIncrement(index);
		} else if (arraySynchIncrement) {
			array.arraySynchIncrement(index);
		} else if (mutex) {
			array.incrementMutex(index);
		}
	}

	@Override
	public void run() {
		int length = array.getLength();
		for (int i = 0; i < length; i++) {
			increment(i);
		}
	}
}
