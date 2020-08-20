package com.yanrs.mr.sort2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class Sort2Reducer extends Reducer <LongWritable, Text, Text, LongWritable>{
    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value: values) {
            // 这里写出数据的时候，还是将手机号放在前面，排序好的总流量放在后面
            context.write(value, key);
        }
    }
}
