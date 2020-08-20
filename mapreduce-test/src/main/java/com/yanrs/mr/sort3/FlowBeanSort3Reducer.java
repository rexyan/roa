package com.yanrs.mr.sort3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowBeanSort3Reducer extends Reducer <FlowBeanSort3, Text, Text, FlowBeanSort3>{
    @Override
    protected void reduce(FlowBeanSort3 key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value:values) {
            context.write(value, key);
        }
    }
}
