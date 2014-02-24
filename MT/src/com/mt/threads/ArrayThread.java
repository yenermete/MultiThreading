package com.mt.threads;

import com.mt.entity.IntArray;
import com.mt.util.MtUtil;

public class ArrayThread extends Thread {

	private IntArray array;
	private boolean increment;
	private boolean arrayIncrement;
	private boolean arraySynchIncrement;
	private boolean mutex;
	private boolean reentrant;

	/**
	 * Initially, array is checked against being null. If it is null, an
	 * IllegalArgumentException is thrown. With the lockType parameter,
	 * synchronization method is chosen. For example,
	 * {@link MtUtil#INCREMENT_WITH_SYNCHRONIZED_MUTEX_PARAMETER} will result
	 * the synchronization to be achieved by locking a dummy mutex object.
	 * 
	 * @param array
	 *            IntArray object, which contains an array of primitive
	 *            integers.
	 * @param lockType
	 *            One of the five methods to be used for synchronization is
	 *            chosen depending on the value of this parameter. It will
	 *            default to
	 *            {@link MtUtil#INCREMENT_WITH_SYNCHRONIZED_THIS_PARAMETER}
	 * @throws IllegalArgumentException
	 *             If the {@link IntArray} object is null or its {@code array}
	 *             field is null.
	 * @author Yener
	 * */
	public ArrayThread(IntArray array, int lockType) {
		MtUtil.checkArraySize(array);
		this.array = array;

		switch (lockType) {
		case MtUtil.INCREMENT_WITH_SYNCHRONIZED_THIS_PARAMETER:
			increment = true;
		case MtUtil.INCREMENT_WITH_SYNCHRONIZED_METHOD:
			arrayIncrement = true;
		case MtUtil.INCREMENT_WITH_SYNCHRONIZED_ARRAY_PARAMETER:
			arraySynchIncrement = true;
		case MtUtil.INCREMENT_WITH_SYNCHRONIZED_MUTEX_PARAMETER:
			mutex = true;
		case MtUtil.INCREMENT_WITH_REENTRANT_LOCK:
			reentrant = true;
		default:
			increment = true;
			break;
		}
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
		} else if (reentrant) {
			array.incrementReentrant(index);
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
