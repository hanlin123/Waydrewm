package mapreduce;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

public class TwitterReducer2 extends TableReducer<Text, Text,ImmutableBytesWritable >{


	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException{

		HashSet<String> set = new HashSet<String>();
		Put put = new Put(Bytes.toBytes(key.toString()));
		for(Text t : value){
			set.add(t.toString());
		}
		put.add(Bytes.toBytes("Number"), Bytes.toBytes("count"), Bytes.toBytes(Integer.toString(set.size())));
		context.write(null, put);
		
	}
}
