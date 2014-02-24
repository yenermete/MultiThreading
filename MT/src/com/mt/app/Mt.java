package com.mt.app;

import com.mt.entity.IntArray;
import com.mt.threads.ArrayThread;

public class Mt {
	
	private int[] array;
	private final int lockType;
	
	public Mt(int[] array, int lockType){
		this.array = array;
		this.lockType = lockType;
	}
	
	private void process(IntArray intArray) throws InterruptedException {
		Thread thread1 = new ArrayThread(intArray, lockType);
		Thread thread2 = new ArrayThread(intArray, lockType);
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch(InterruptedException e){
			throw new InterruptedException("Interrupted. Result not accurate!");
		}
	}
	
	public void increment() {
		IntArray intArray = new IntArray(array);
		try{
			process(intArray);
		} catch(InterruptedException e){
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public int[] getArray() {
		return array;
	}
}
