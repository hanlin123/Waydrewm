package mapreduce;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import utility.ParseJSON3;



public class TwitterMapper3 extends Mapper<LongWritable, Text, Text, Text>{
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

			String[] elems = ParseJSON3.parseJSON(value.toString());
			if(elems==null)		return;

			Text theKey = new Text();
			Text theValue = new Text();
			theKey.set(elems[0]);
			theValue.set(elems[1]);

			context.write(theKey, theValue);
			
	}

}
