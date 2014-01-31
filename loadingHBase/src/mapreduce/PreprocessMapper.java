package mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import utility.ParseJSON1;

public class PreprocessMapper extends Mapper<LongWritable, Text, Text, Text>{

	private final String delimit = Character.toString((char)31);
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

		String[] elems = ParseJSON1.parseJSON(value.toString());
		if(elems==null)		return;

		Text theKey = new Text();
		Text theValue = new Text();
		theKey.set(elems[0]);
		theValue.set(elems[1]+delimit+elems[2]);
		
		context.write(theKey, theValue);
	}
}
