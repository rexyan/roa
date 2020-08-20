package com.yanrs.mr.sort1;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class Sort1Mapper extends Mapper<LongWritable, Text, LongWritable, Text>{
    private LongWritable outKey = new LongWritable();
    private Text outValue = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split("=");
        // 封装总流量为 key
        outKey.set(Long.parseLong(words[3].replace("}", "")));
        // 封装手机号为 value
        outValue.set(words[0].split("\t")[0]);
        context.write(outKey, outValue);
    }
}
