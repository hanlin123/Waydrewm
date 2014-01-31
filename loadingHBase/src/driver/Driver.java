package driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class Driver {

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Configuration conf = HBaseConfiguration.create();
		Job job = new Job(conf, "loadingHBase1");
		job.setJarByClass(Driver.class);
		job.setMapperClass(mapreduce.TwitterMapper1.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path(args[1]));
		TableMapReduceUtil.initTableReducerJob("newretweet2",mapreduce.TwitterReducer1.class,job);
		job.waitForCompletion(true);
	}

}
