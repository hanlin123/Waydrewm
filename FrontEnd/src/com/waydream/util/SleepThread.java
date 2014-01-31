package com.waydream.util;

import java.io.IOException;

public class SleepThread extends Thread {
	private static double percentage;
	private static double[] records;
	
	final static double Q1BENCHMARK = 2200.0;
	final static double Q2BENCHMARK = 360;
	final static double Q3BENCHMARK = 12;
	final static double Q4BENCHMARK = 340.0;
	
	public static double getPercent(){
		return percentage;
	}
	
	private static void init(double newData){
		records = new double[3];
		for(int i=0; i<3; i++)	records[i]=newData;
	}
	private static double update(int q1, int q2, int q3, int q4){
		double recordSum = 0.0;
		double newData = Math.max(0.0, (q1/Q1BENCHMARK));
		newData = Math.max(newData, (q2/Q2BENCHMARK));
		newData = Math.max(newData, (q3/Q3BENCHMARK));
		newData = Math.max(newData, (q4/Q4BENCHMARK));
		if(records==null)
			init(newData);
		for(int i=1; i<3;i++){
			records[i-1] = records[i];
			recordSum += records[i];
		}
		
		records[2] = newData;
		recordSum += newData;
		return recordSum/3;
	}
	public void run(){
	
		System.out.println("start calculating throuput.");
		
		while(true){
			
			try {
				sleep(60000);
				
				int newQ1 = CountRequest.getCountQ1();
				int newQ2 = CountRequest.getCountQ2();
				int newQ3 = CountRequest.getCountQ3();
				int newQ4 = CountRequest.getCountQ4();
				
				
				percentage = update(newQ1/60, newQ2/60, newQ3/60, newQ4/60)*100;
				
				System.out.println("q1:"+newQ1+"\tq2:"+newQ2+"\tq3:"+newQ3+"\tq4"+newQ4+"\tpercent:"+percentage);
				String cmd = "/home/ubuntu/customMetrics/tpsInfo.py "+ Integer.toString((int)percentage);
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}

