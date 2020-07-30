package com.yanrs.mr.combine;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * KEYIN,VALUEIN: Mapper 的输出做为这里的输入
 * KEYOUT,VALUEOUT： 自定义，因为这个 MR 程序是统计单词出现的频率，所以这里类型为 Text, IntWritable
 */
public class CMReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable outValue = new IntWritable();

    //reduce 方法一次处理一组数据，key（单词） 相同的数据是一组
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        // 遍历每个 key（单词） ，让相同的 key（单词） 的值进行累加
        for (IntWritable value:values) {
            sum+=value.get();
        }

        outValue.set(sum);
        // 将结果写出，key 是单词，outValue 是累加的次数
        context.write(key, outValue);
    }
}
