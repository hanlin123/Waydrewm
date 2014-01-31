package mapreduce;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import utility.ParseJSON2;



public class TwitterMapper2 extends Mapper<LongWritable, Text, Text, Text>{
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

			String[] result = ParseJSON2.parseJSON(value.toString());
			if(result==null)		return;

			Text theKey = new Text();
			Text theValue = new Text();
			theKey.set(result[0]);
			theValue.set(result[1]);
			
			context.write(theKey, theValue);
			
	}

}
