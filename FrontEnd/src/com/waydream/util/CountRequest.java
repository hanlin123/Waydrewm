package com.waydream.util;

import java.util.concurrent.atomic.AtomicInteger;

public class CountRequest {

	private static AtomicInteger numOfRequsetQ1 = null;
	private static AtomicInteger numOfRequsetQ2 = null;
	private static AtomicInteger numOfRequsetQ3 = null;
	private static AtomicInteger numOfRequsetQ4 = null;
	private static boolean on = false;
	
	private static synchronized void init(){
			if(on==true) return;
	       SleepThread thread = new SleepThread();
	       thread.start(); 
	       on = true;
	}
	private static synchronized void initQ1(){
	   numOfRequsetQ1 = new AtomicInteger(0);
	}
	private static synchronized void initQ2(){
		   numOfRequsetQ2 = new AtomicInteger(0);
	}
	private static synchronized void initQ3(){
		   numOfRequsetQ3 = new AtomicInteger(0);
	}
	private static synchronized void initQ4(){
		   numOfRequsetQ4 = new AtomicInteger(0);
	}
	
	public static int addCountQ1(){
		if(numOfRequsetQ1==null &&
				numOfRequsetQ2==null &&
				numOfRequsetQ3==null &&
				numOfRequsetQ4==null)
			init();
		if(numOfRequsetQ1==null)
			initQ1();
		return numOfRequsetQ1.addAndGet(1);
	}
	public static int addCountQ2(){
		if(numOfRequsetQ1==null &&
				numOfRequsetQ2==null &&
				numOfRequsetQ3==null &&
				numOfRequsetQ4==null)
			init();
		if(numOfRequsetQ2==null)
			initQ2();
		return numOfRequsetQ2.addAndGet(1);
	}
	public static int addCountQ3(){
		if(numOfRequsetQ1==null &&
				numOfRequsetQ2==null &&
				numOfRequsetQ3==null &&
				numOfRequsetQ4==null)
			init();
		if(numOfRequsetQ3==null)
			initQ3();
		return numOfRequsetQ3.addAndGet(1);
	}
	public static int addCountQ4(){
		if(numOfRequsetQ1==null &&
				numOfRequsetQ2==null &&
				numOfRequsetQ3==null &&
				numOfRequsetQ4==null)
			init();
		if(numOfRequsetQ4==null)
			initQ4();
		return numOfRequsetQ4.addAndGet(1);
	}
	
	
	public static int getCountQ1(){
		if(numOfRequsetQ1==null)	return 0;
		int tmp = numOfRequsetQ1.get();
		numOfRequsetQ1.set(0);
		return tmp;
	}
	public static int getCountQ2(){
		if(numOfRequsetQ2==null)	return 0;
		int tmp = numOfRequsetQ2.get();
		numOfRequsetQ2.set(0);
		return tmp;
	}
	public static int getCountQ3(){
		if(numOfRequsetQ3==null)	return 0;
		int tmp = numOfRequsetQ3.get();
		numOfRequsetQ3.set(0);
		return tmp;
	}
	public static int getCountQ4(){
		if(numOfRequsetQ4==null)	return 0;
		int tmp = numOfRequsetQ4.get();
		numOfRequsetQ4.set(0);
		return tmp;
	}
	public static int[] getValues(){
		int[] values = new int[4];
		if(numOfRequsetQ1==null)	values[0]=0;
		else						values[0]=numOfRequsetQ1.get();
		
		if(numOfRequsetQ2==null)	values[1]=0;
		else						values[1]=numOfRequsetQ2.get();
		
		if(numOfRequsetQ3==null)	values[2]=0;
		else						values[2]=numOfRequsetQ3.get();
		
		if(numOfRequsetQ4==null)	values[3]=0;
		else						values[3]=numOfRequsetQ4.get();
		
		return values;
	}
}