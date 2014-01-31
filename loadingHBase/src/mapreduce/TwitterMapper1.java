package mapreduce;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TwitterMapper1 extends Mapper<LongWritable, Text, Text, Text>{

//	private final String delimit = Character.toString((char)31);
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

			String line = value.toString();
			if(line==null || line.equals("") || line.length()<21)		
				return;
			
			String time = line.substring(0, 19);
			String other = line.substring(20);
			Text theKey = new Text();
			Text theValue = new Text();
			theKey.set(time);
			theValue.set(other);
			
			context.write(theKey, theValue);
			
	}
	
//	private static String[] parseLine(String line){	
//		if(line.length()<21)
//			return null;
//		String time = line.substring(0,19);
//		String other = line.substring(20);
//		String[] elems = other.split("}");
//		String[] result = {time, elems[0], elems[1]};
//		return result;
//	}

}
