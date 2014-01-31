package com.waydream.util;

import java.io.IOException;

import org.apache.hadoop.hbase.ipc.CoprocessorProtocol;

public interface RowCountProtocol extends CoprocessorProtocol{

	long getRowCount(String startID, String stopID) throws IOException;
}
