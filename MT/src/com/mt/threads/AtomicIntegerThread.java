package com.mt.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerThread implements Runnable {

	private AtomicInteger[] array;
	private int index;
	
	public AtomicIntegerThread(AtomicInteger[] array){
		if(array == null){
			throw new IllegalArgumentException("Array can not be null!");
		}
		this.array = array;
	}
	
	private void increment(int indice){
		array[indice].getAndIncrement();
	}
	
	@Override
	public void run(){
		for(int i = 0; i < array.length; i++){
			increment(index++);
		}
	}
}
