package com.waydream.util;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
//import org.apache.hadoop.hbase.client.ResultScanner;
//import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.rest.client.Client;
import org.apache.hadoop.hbase.rest.client.Cluster;
import org.apache.hadoop.hbase.rest.client.RemoteHTable;
import org.apache.hadoop.hbase.util.Bytes;
public class QueryHB4 {

	private static Configuration conf = null;
	private static Cluster cluster = null;
	private static Client client = null;
	private static RemoteHTable table = null;
	
	private static final String dns = "ec2-54-221-168-52.compute-1.amazonaws.com";
	private static final String tableName = "newretweet";
	private static final String userCF = "userID";
	private static final int port = 8080;
	
	private static void init(){
		conf = HBaseConfiguration.create();
		cluster = new Cluster();
		cluster.add(dns, port);
		client = new Client(cluster);
		table = new RemoteHTable(client,conf, Bytes.toBytes(tableName));
	}
	
	public static String[] queryHB4(String originUserID) throws IOException{
		if(conf==null || cluster==null || client==null || table==null)
			init();

		int count = 0;
		
		Get get = new Get(Bytes.toBytes(originUserID));
		Result result = table.get(get);
		
		if(result==null)	return null;
		ArrayList<String> output = new ArrayList<String>();
		

		int size = result.size();
		//System.out.println(size);
		while(count<size){
			String key = "id"+Integer.toString(count+100000);
			//String originID = Bytes.toString(result.getRow());
			String userID = Bytes.toString(result.getValue(Bytes.toBytes(userCF), Bytes.toBytes(key)));
			
			if(userID!=null){
				userID = Integer.toString(Integer.parseInt(userID));
				if(!output.contains(userID))	
					output.add(userID);
			}
			count++;
		}
		
//		String[] tmp = output.toArray(new String[output.size()]);
//		for(String s : tmp)
//			System.out.println(s);
		return output.toArray(new String[output.size()]);
	}
	
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//
//		QueryHB4.queryHB4("133700508");
//		
//	}

}
