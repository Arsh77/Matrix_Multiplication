package matrix_multiplication;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, Text, Text, Text>{
	public void reduce(Text key, Iterable<Text> value, Context context ) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		int j = Integer.parseInt(conf.get("j"));
		
		HashMap<Integer, Float> hmA  = new  HashMap<Integer, Float>();
		HashMap<Integer, Float> hmB  = new  HashMap<Integer, Float>();
		
		for(Text val : value) {
			String[] values = val.toString().split(" ");
			
			if(values[0].equalsIgnoreCase("A")) {
				hmA.put(Integer.parseInt(values[1]), Float.parseFloat(values[2]));
			}
			else {
				hmB.put(Integer.parseInt(values[1]), Float.parseFloat(values[2]));
			}
		}
		
		float result = 0.0f;
		for(int i=0; i<j; i++) {
			float a_ij=hmA.containsKey(i)?hmA.get(i):0;
			float b_jk=hmB.containsKey(i)?hmB.get(i):0;
			result+=a_ij*b_jk;
		}
		context.write(key, new Text(""+result));
	}
}
