package com.mt.app;

import java.util.concurrent.atomic.AtomicIntegerArray;

import com.mt.threads.AtomicIntegerArrayThread;

public class MtAtomicIntegerArray {

	private AtomicIntegerArray array;

	public void increment(int[] intArray) {
		array = new AtomicIntegerArray(intArray);

		Thread thread1 = new Thread(new AtomicIntegerArrayThread(array));
		Thread thread2 = new Thread(new AtomicIntegerArrayThread(array));

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

	public AtomicIntegerArray getArray() {
		return array;
	}
}
