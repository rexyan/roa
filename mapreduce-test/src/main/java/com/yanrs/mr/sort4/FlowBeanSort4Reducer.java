package com.yanrs.mr.sort4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowBeanSort4Reducer extends Reducer <FlowBeanSort4, Text, Text, FlowBeanSort4>{
    @Override
    protected void reduce(FlowBeanSort4 key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value:values) {
            context.write(value, key);
        }
    }
}
