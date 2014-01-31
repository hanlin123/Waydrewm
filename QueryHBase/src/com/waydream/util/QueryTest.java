package com.waydream.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.rest.client.Client;
import org.apache.hadoop.hbase.rest.client.Cluster;
import org.apache.hadoop.hbase.rest.client.RemoteHTable;
import org.apache.hadoop.hbase.util.Bytes;
public class QueryTest {

	private static Configuration conf = null;
	private static Cluster cluster = null;
	private static Client client = null;
	private static RemoteHTable table = null;
	
	private static final String dns = "ec2-50-19-21-128.compute-1.amazonaws.com";
	private static final String tableName = "ngram";
	private static final String CF = "grams";

	private static final int port = 8080;
	
	private static void init(){
		conf = HBaseConfiguration.create();
		cluster = new Cluster();
		cluster.add(dns, port);
		client = new Client(cluster);
		table = new RemoteHTable(client,conf, Bytes.toBytes(tableName));
	}
	
	public static void queryHBase(String time) throws IOException{
		if(conf==null || cluster==null || client==null || table==null)
			init();
		
		Get get = new Get(Bytes.toBytes(time));
		Result result = table.get(get);
		
		if(result==null)	return;
		
		int size = result.size();
		System.out.println("size:"+size);
		
		
		
		String gram = Bytes.toString(result.getValue(Bytes.toBytes(CF), Bytes.toBytes("0.00000}melting")));
			
		System.out.println("gram:"+gram);
		

	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		queryHBase("a%20look%20of");

		
		
	}

}
