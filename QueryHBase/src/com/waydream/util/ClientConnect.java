package com.waydream.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class ClientConnect {

	public static void main(String[] args) throws MasterNotRunningException, ZooKeeperConnectionException {
		// TODO Auto-generated method stub
		Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum","ec2-54-211-131-39.compute-1.amazonaws.com");
		conf.set("hbase.zookeeper.property.clientPort","2181");
		conf.set("hbase.master","ec2-54-211-131-39.compute-1.amazonaws.com");
		conf.set("hbase.master.port","60000");
		
		HBaseAdmin.checkHBaseAvailable(conf);
	}

}
