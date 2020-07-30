package com.yanrs.mr.flowbean;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * mapper 输入参数 key 为行号，value 为一行的文本
 * mapper 输出参数 key 手机号，value bean 对象（对象中分别有上行，下行，总流量三个属性）
 */
public class FlowBeanMapper extends Mapper<LongWritable, Text, Text, FlowBean>{
    private Text outKey = new Text();
    private FlowBean flowBean = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // key 为序列号，value 为每行的内容
        String[] words = value.toString().split("\t");

        // 封装手机号
        outKey.set(words[1]);
        // 上行流量
        flowBean.setUpFlow(Long.parseLong(words[words.length - 3]));
        // 下行流量
        flowBean.setDownFlow(Long.parseLong(words[words.length - 2]));
        context.write(outKey, flowBean);
    }
}
