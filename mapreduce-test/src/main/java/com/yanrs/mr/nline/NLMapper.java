package com.yanrs.mr.nline;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * KEYIN, VALUEIN: mapper 输入的 key-value 类型，由当前 JOb 的 InputFormat的 RecordReader 决定
 * KEYOUT, VALUEOUT：mapper 输出的 key-value 类型
 */
public class NLMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text outKey = new Text();
    private IntWritable outValue = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // key 是行号，value 是一行的文本内容
        System.out.println("keyin: " + key + " valuein: " + value);
        // 将文本内容进行拆分，得到一个个单词组成的数组
        String[] words = value.toString().split("\t");
        // 遍历数组，并输出，输出格式为（单词，1）
        for (String word:words) {
            outKey.set(word);
            context.write(outKey, outValue);
        }
    }
}
