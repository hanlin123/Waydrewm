package mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

public class TwitterReducer3 extends TableReducer<Text, Text,ImmutableBytesWritable >{

	
	
	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException{

		ArrayList<String> array = new ArrayList<String>();
		for(Text t : value)
			array.add(t.toString());
		
		String[] elems = array.toArray(new String[array.size()]);
		Arrays.sort(elems);
		
		Put put = new Put(Bytes.toBytes(key.toString()));
		int count = 0;
		for(String s : elems){
			String id = "id"+Integer.toString(100000+count);
			put.add(Bytes.toBytes("userID"), Bytes.toBytes(id), Bytes.toBytes(s));
			count++;
		}
		
		context.write(null, put);
		
	}
}
