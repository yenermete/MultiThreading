package com.mt.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.mt.app.Mt;
import com.mt.app.MtAtomicInteger;
import com.mt.app.MtAtomicIntegerArray;
import com.mt.util.MtUtil;


public class MtTest extends TestCase {

	@Test
	public void testIncrement() {
		Mt mt = new Mt(new int[MtUtil.ARRAY_LENGTH], true, false, false, false);
		mt.increment();
		
		testEquality(mt.getArray(), MtUtil.EXPECTED_RESULT);
	}
	
	@Test
	public void testArrayIncrement() {
		Mt mt = new Mt(new int[MtUtil.ARRAY_LENGTH], false, true, false, false);
		mt.increment();
		
		testEquality(mt.getArray(), MtUtil.EXPECTED_RESULT);
	}
	
	@Test
	public void testArraySynchIncrement() {
		Mt mt = new Mt(new int[MtUtil.ARRAY_LENGTH], false, false, true, false);
		mt.increment();
		
		testEquality(mt.getArray(), MtUtil.EXPECTED_RESULT);
	}
	
	@Test
	public void testMutexIncrement() {
		Mt mt = new Mt(new int[MtUtil.ARRAY_LENGTH], false, false, false, true);
		mt.increment();
		
		testEquality(mt.getArray(), MtUtil.EXPECTED_RESULT);
	}
	
	@Test
	public void testAtomicIncrement() {
		MtAtomicInteger atomic = new MtAtomicInteger();
		atomic.increment(new int[MtUtil.ARRAY_LENGTH]);
		
		for(int i = 0; i < MtUtil.ARRAY_LENGTH; i++){
			assertEquals(MtUtil.EXPECTED_RESULT, atomic.getArray()[i].get());
		}
		
	}
	
	@Test
	public void testAtomicArrayIncrement() {
		MtAtomicIntegerArray atomic = new MtAtomicIntegerArray();
		atomic.increment(new int[MtUtil.ARRAY_LENGTH]);
		
		for(int i = 0; i < MtUtil.ARRAY_LENGTH; i++){
			assertEquals(MtUtil.EXPECTED_RESULT, atomic.getArray().get(i));
		}
		
	}
	
	private void testEquality(int[] array, final int expectedValue) {
		for(int i = 0; i < MtUtil.ARRAY_LENGTH; i++) {
			assertEquals(expectedValue, array[i]);
		}
	}
}
