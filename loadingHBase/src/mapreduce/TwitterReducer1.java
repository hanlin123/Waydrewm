package mapreduce;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

public class TwitterReducer1 extends TableReducer<Text, Text,ImmutableBytesWritable >{

//	private final String delimit = Character.toString((char)31);
	
	public void reduce(Text key, Iterable<Text> value, Context context) throws IOException, InterruptedException{
		
		String time = key.toString();
		HashSet<String> set = new HashSet<String>();
		for(Text t : value)	
			set.add(t.toString());
		
		String[] elems = set.toArray(new String[set.size()]);
		Arrays.sort(elems);
		
		Integer count = 10000;
		Put put = new Put(Bytes.toBytes(time));
		for(String s : elems){
			String[] content = s.split("}");
			put.add(Bytes.toBytes("tweetID"), Bytes.toBytes("id"+count.toString()), Bytes.toBytes(content[0]));
			put.add(Bytes.toBytes("text"), Bytes.toBytes("id"+count.toString()), Bytes.toBytes(content[1]));
			count++;
		}
		
		context.write(null, put);
		
	}
}
