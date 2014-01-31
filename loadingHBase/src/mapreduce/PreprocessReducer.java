package mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PreprocessReducer extends Reducer<Text, Text, Text, Text> {

	private final String delimit = Character.toString((char)31);
	
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
		for(Text t : values){
			String[] elems = t.toString().split(delimit);
			Text outputV = new Text();
			outputV.set(elems[0]+"}"+elems[1]);
			context.write(key, outputV);
		}
	}
}
