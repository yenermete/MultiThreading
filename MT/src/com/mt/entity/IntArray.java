package com.mt.entity;

import java.util.concurrent.locks.ReentrantLock;

/**
 * This class holds the array on which threads will work on synchronously.<br/>
 * <br/>
 * 
 * I prefer the method {@link #incrementMutex(int)} as it is locking the final
 * field mutex and nothing else is locked. {@link #incrementReentrant(int)} is
 * also useful since Java 1.5 and provides even more control over locks and the
 * threads fighting for the lock.
 * 
 * @author Yener
 */
public class IntArray {

	/**
	 * Threads are supposed to work synchronously on this field of
	 * {@link IntArray}.
	 * 
	 */
	private int[] array;
	/**
	 * This is a dummy object for locking access to {@link #array} in
	 * {@link IntArray#incrementMutex(int)}
	 * 
	 */
	private final Object mutex = new Object();
	private final ReentrantLock lock = new ReentrantLock();

	public IntArray(int[] array) {
		this.array = array;
	}

	/**
	 * 
	 * @return returns the length of the array field named {@link #array}, or
	 *         zero if it is null
	 */
	public int getLength() {
		return array == null ? 0 : array.length;
	}

	/**
	 * Lock is achieved with the {@link #lock} object.<br/>
	 * 
	 * <br/>
	 * <b>Advantages</b><br/>
	 * With this lock, a lock can be shared between different methods.<br/>
	 * There is a fairness policy which is supposed to make the thread waiting
	 * for the longest time for the resource to access it first when the lock is
	 * unlocked.<br/>
	 * Lock can be achieved with a certain amount of time.<br/>
	 * With the {@link ReentrantLock #lockInterruptibly()} and
	 * {@link ReentrantLock #tryLock()} methods, indefinite blcoking of threads
	 * can be avoided.<br/>
	 * <br/>
	 * <b>Disadvantages</b><br/>
	 * The only disadvantage I can think of is the finally block added.
	 * 
	 * @param index
	 *            The index of the array which will be incremented.
	 */
	public void incrementReentrant(int index) {
		lock.lock();
		try {
			incrementChosenIndex(index);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Lock is achieved on the mutex object.<br/>
	 * <br/>
	 * <b>Advantages</b><br/>
	 * Lock is achieved on the mutex object, which is final and not reachable
	 * from anywhere else.<br/>
	 * Lock begins in the beginning of the synchronized block, rather than whole
	 * method.<br/>
	 * It performs better then locking the whole method.<br/>
	 * Synchronized code can be changed without changing method signature.<br/>
	 * This provides more granularity. It will reduce the scope of synchronized
	 * code compared to synchronized method. <br/>
	 * <b>Disadvantages</b><br/>
	 * It will throw a {#link NullPointerException} if the parameter of the
	 * synchronized block is null. But this can be avoided with careful use of
	 * locking object.<br/>
	 * 
	 * @param index
	 *            The index of the array which will be incremented.
	 */
	public void incrementMutex(int index) {
		synchronized (mutex) {
			incrementChosenIndex(index);
		}
	}

	/**
	 * The lock is achieved by the synchronized block, on 'this'.<br/>
	 * <br/>
	 * For advantages and disadvantages, {@link #arrayIncrement} can be
	 * inspected.<br/>
	 * 
	 * @param index
	 *            The index of the array which will be incremented.
	 * @author Yener
	 */
	public void increment(int index) {
		synchronized (this) {
			incrementChosenIndex(index);
		}
	}

	/**
	 * Lock is achieved on the {@link #array} object.<br/>
	 * 
	 * 
	 * @param index
	 *            The index of the array which will be incremented.
	 * @author Yener
	 */
	public void arraySynchIncrement(int index) {
		synchronized (array) {
			incrementChosenIndex(index);
		}
	}

	/**
	 * This method is wholly synchronized.<br/>
	 * <br/>
	 * 
	 * <b>Advantages</b><br/>
	 * The syntax is more compact.<br/>
	 * Forces to split the synchronized blocks to separate methods.<br/>
	 * 
	 * <br/>
	 * <b>Disadvantages</b><br/>
	 * This way, synchronization is done on 'this', so synchronization can be
	 * done from outside too(i.e. external code calling this method can also
	 * lock object).<br/>
	 * When the whole code is not neccesary to be synchronized and you want to
	 * make a code block synchronized, this is harder to achieve.<br/>
	 * 
	 * @param index
	 *            The index of the array which will be incremented.
	 * @author Yener
	 */
	public synchronized void arrayIncrement(int index) {
		incrementChosenIndex(index);
	}

	private void incrementChosenIndex(int index) {
		array[index]++;
	}

}
