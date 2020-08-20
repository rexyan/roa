package com.yanrs.mr.sort4;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class FlowBeanSort4Mapper extends Mapper<LongWritable, Text, FlowBeanSort4, Text>{
    private Text outValue = new Text();
    private FlowBeanSort4 outKey = new FlowBeanSort4();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // key 为序列号，value 为每行的内容
        String[] words = value.toString().split("\t");
        // 上行流量
        outKey.setUpFlow(Long.parseLong(words[words.length - 3]));
        // 下行流量
        outKey.setDownFlow(Long.parseLong(words[words.length - 2]));
        outKey.setSumFlow(Long.parseLong(words[words.length - 2]) + Long.parseLong(words[words.length - 3]));
        // 封装手机号
        outValue.set(words[1]);
        context.write(outKey, outValue);
    }
}
