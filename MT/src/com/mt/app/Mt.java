package com.mt.app;

import com.mt.entity.IntArray;
import com.mt.threads.ArrayThread;

public class Mt {
	
	private int[] array;
	private boolean increment;
	private boolean arrayIncrement;
	private boolean arraySynchIncrement;
	private boolean mutex;
	private boolean reentrant;
	
	public Mt(int[] array, boolean increment, boolean arrayIncrement, boolean arraySynchIncrement, boolean mutex, boolean reentrant){
		this.array = array;
		this.increment = increment;
		this.arrayIncrement = arrayIncrement;
		this.arraySynchIncrement = arraySynchIncrement;
		this.mutex = mutex;
		this.reentrant = reentrant;
	}
	
	private void process(IntArray intArray) throws InterruptedException {
		Thread thread1 = new ArrayThread(intArray, increment, arrayIncrement, arraySynchIncrement, mutex, reentrant);
		Thread thread2 = new ArrayThread(intArray, increment, arrayIncrement, arraySynchIncrement, mutex, reentrant);
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
