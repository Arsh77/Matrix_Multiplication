package matrix_multiplication;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;

public class MatrixMultiplication {
	
	public static void main(String[] args) throws Exception {
	
		if (args.length != 5) {
            System.err.println("Usage: MatrixMultiply <in_dir> <out_dir> <1st row> <common_row_col><2nd col>");
            System.exit(2);
        }
		
		Configuration conf = new Configuration();
		System.out.println("@@## i : "+args[2]+" j : "+args[3]+" k : "+args[4]);
		conf.set("i",args[2]);
		conf.set("j",args[3]);
		conf.set("k",args[4]);
		
		Job job = new Job(conf, "MatrixMultiply");
		
		job.setJarByClass(MatrixMultiplication.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		System.out.println(">>>>>>>going in map<<<<<<<");
		job.setMapperClass(Map.class);
		System.out.println(">>>>>>>going in reduce<<<<<<<");
		job.setReducerClass(Reduce.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
	    job.waitForCompletion(true);
	    
	}
	
}
