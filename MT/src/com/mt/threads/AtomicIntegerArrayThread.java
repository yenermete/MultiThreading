package com.mt.threads;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayThread implements Runnable {

	private AtomicIntegerArray array;
	
	public AtomicIntegerArrayThread(AtomicIntegerArray array){
		this.array = array;
	}
	
	private void increment(AtomicIntegerArray array, int index){
		array.getAndIncrement(index);
	}
	
	@Override
	public void run() {
		int length = array.length();
		for(int i = 0; i < length; i++) {
			increment(array, i);
		}

	}

}
