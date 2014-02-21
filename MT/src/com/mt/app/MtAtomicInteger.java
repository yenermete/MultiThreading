package com.mt.app;

import java.util.concurrent.atomic.AtomicInteger;

import com.mt.threads.AtomicIntegerThread;

public class MtAtomicInteger {

	private AtomicInteger[] array;

	public void increment(int[] intArray) {
		array = new AtomicInteger[intArray.length];
		for (int i = 0; i < intArray.length; i++) {
			array[i] = new AtomicInteger(intArray[i]);
		}

		Thread thread1 = new Thread(new AtomicIntegerThread(array));
		Thread thread2 = new Thread(new AtomicIntegerThread(array));

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}

	public AtomicInteger[] getArray() {
		return array;
	}
}
