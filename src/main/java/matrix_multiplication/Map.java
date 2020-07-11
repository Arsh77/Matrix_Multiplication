package matrix_multiplication;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, Text, Text>{
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		
		Configuration conf= context.getConfiguration();
		int i = Integer.parseInt(conf.get("i"));
		int k = Integer.parseInt(conf.get("k"));
		
		String[] val = value.toString().split(" ");
		Text outKey = new Text();
		Text outVal = new Text();
		if(val[0].equalsIgnoreCase("A")) {
			for(int x=0; x<k; x++) {
				outKey.set(val[1]+" "+x);
				outVal.set(val[0]+" "+val[2]+" "+val[3]);
				context.write(outKey, outVal);
			}
		}
		else {
			for(int x=0; x<i; x++) {
				outKey.set(x+" "+val[2]);
				outVal.set(val[0]+" "+val[1]+" "+val[3]);
				context.write(outKey, outVal);
			}
		}
				
	}
}
