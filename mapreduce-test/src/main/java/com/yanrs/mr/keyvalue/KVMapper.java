package com.yanrs.mr.keyvalue;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * KEYIN, VALUEIN: mapper 输入的 key-value 类型，由当前 JOb 的 InputFormat的 RecordReader 决定
 * KEYOUT, VALUEOUT：mapper 输出的 key-value 类型
 */
public class KVMapper extends Mapper<Text, Text, Text, IntWritable> {

    private IntWritable outValue = new IntWritable(1);

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        // key 是 * 之前的姓名，value 是计数1
        context.write(key, outValue);
    }
}
