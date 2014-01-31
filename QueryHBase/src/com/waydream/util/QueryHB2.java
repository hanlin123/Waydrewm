package com.waydream.util;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.rest.client.Client;
import org.apache.hadoop.hbase.rest.client.Cluster;
import org.apache.hadoop.hbase.rest.client.RemoteHTable;
import org.apache.hadoop.hbase.util.Bytes;
public class QueryHB2 {

	private static Configuration conf = null;
	private static Cluster cluster = null;
	private static Client client = null;
	private static RemoteHTable table = null;
	
	private static final String dns = "ec2-54-221-168-52.compute-1.amazonaws.com";
	private static final String tableName = "newtweet";
	private static final String tweetCF = "tweetID";
	private static final String textCF = "text";
	private static final int port = 8080;
	
	private static void init(){
		conf = HBaseConfiguration.create();
		cluster = new Cluster();
		cluster.add(dns, port);
		client = new Client(cluster);
		table = new RemoteHTable(client,conf, Bytes.toBytes(tableName));
	}
	
	public static String[] queryHB2(String time) throws IOException{
		if(conf==null || cluster==null || client==null || table==null)
			init();
		
		ArrayList<String> output = new ArrayList<String>();
		
		String r = time.replace("+", "%2b");
		Get get = new Get(Bytes.toBytes(r));
		Result result = table.get(get);
		int count = 0;
		
		if(result==null)	return null;
		
		int size = result.size()/2;
		while(count<size){
			String key = "id"+Integer.toString(count+10000);
			String tweetID = Bytes.toString(result.getValue(Bytes.toBytes(tweetCF), Bytes.toBytes(key)));
			String text = Bytes.toString(result.getValue(Bytes.toBytes(textCF), Bytes.toBytes(key)));
			if(text!=null){
				
				text = text.replaceAll("\\{", "\\\\"+"u00"+Integer.toHexString(123));
				text = text.replaceAll("\\}", "\\\\"+"u00"+Integer.toHexString(125));
				
				text = StringEscapeUtils.escapeJava(text);
				
				text = convertString(text);
				text = text.replaceAll("/", "\\\\/");
				text = text.replaceAll("[\\\\]+", "\\\\");
				
				
				output.add(tweetID+":"+text);
				//System.out.println(output.get(output.size()-1));
			}
			count++;
		}

		return output.toArray(new String[output.size()]);
	}
	
	public static String convertString(String line){
		StringBuilder builder = new StringBuilder();
		int lastIndex = 0;
		int newIndex = 0;
		while(newIndex>=0){
			newIndex = line.indexOf("\\u",newIndex);
			
			if(newIndex<0){
				builder.append(line.substring(lastIndex));
				break;
			}
			builder.append(line.substring(lastIndex, newIndex));
			builder.append("\\u"+line.substring(newIndex+2, newIndex+6).toLowerCase());
			newIndex += 6;
			lastIndex = newIndex;
		}
		return builder.toString();
	}
	
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//
//		QueryHB2.queryHB2("2013-10-03+01:13:00");
//		String s = "\ud83d";
//		s = s.replace("\ud83d", "\\\\"+"\ud83d");
//		System.out.println(s);
//
//		
//		
//	}

}
