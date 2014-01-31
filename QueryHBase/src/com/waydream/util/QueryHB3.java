package com.waydream.util;

import java.io.IOException;
//import java.util.HashSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.rest.client.Client;
import org.apache.hadoop.hbase.rest.client.Cluster;
import org.apache.hadoop.hbase.rest.client.RemoteHTable;
import org.apache.hadoop.hbase.util.Bytes;
public class QueryHB3 {

	private static Configuration conf = null;
	private static Cluster cluster = null;
	private static Client client = null;
	private static RemoteHTable table = null;
	
	private static final String dns = "ec2-54-221-168-52.compute-1.amazonaws.com";
	private static final String tableName = "newuser";
	private static final String CF = "Number";
	private static final int port = 8080;
	
	private static void init(){
		conf = HBaseConfiguration.create();
		cluster = new Cluster();
		cluster.add(dns, port);
		client = new Client(cluster);
		table = new RemoteHTable(client,conf, Bytes.toBytes(tableName));
	}
	
	public static int queryHB3(String startID, String stopID) throws IOException{
		if(conf==null || cluster==null || client==null || table==null)
			init();

		
		int size = 10-startID.length();
		for(int i=0;i<size;i++)
			startID = "0"+startID;
		
		stopID = Integer.toString(Integer.parseInt(stopID)+1);
		size = 10-stopID.length();
		for(int i=0;i<size;i++)
			stopID = "0"+stopID;
		
		Scan scan = new Scan();
		scan.setCacheBlocks(true);
		scan.setCaching(200);
		scan.setStartRow(Bytes.toBytes(startID));
		scan.setStopRow(Bytes.toBytes(stopID));
		
		ResultScanner scanner = table.getScanner(scan);
		int count = 0;
		
		int startInt = Integer.parseInt(startID);
		int stopInt = Integer.parseInt(stopID);
		try{
			for(Result result : scanner){
				String r = Bytes.toString(result.getRow());
				int rint = Integer.parseInt(r);
				if(rint<startInt || rint>stopInt)	continue;
				String c = Bytes.toString(result.getValue(Bytes.toBytes(CF), Bytes.toBytes("count")));
				//System.out.println(r+"\t"+c);
				count += Integer.parseInt(c);
			}
		}catch(NumberFormatException e){
			return 0;
		}
		
		//System.out.println("total num:"+count);
		return count;
	}
	
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//
//		QueryHB3.queryHB3("164900000","164907367");
//		
//
//	}

}
