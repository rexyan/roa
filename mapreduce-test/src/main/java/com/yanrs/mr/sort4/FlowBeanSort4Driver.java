package com.yanrs.mr.sort4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 启动这个进程，那么就会运行该 job
 */
public class FlowBeanSort4Driver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取文件系统
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop10:9000");

        FileSystem fileSystem = FileSystem.get(conf);

        // 设置输入目录和输出目录
        Path inputPath = new Path("/mrinput/flowbean");
        Path outPath = new Path("/mroutput/flowbean/sort4");
        // 输出目录存在就删除
        if(fileSystem.exists(outPath)){
            fileSystem.delete(outPath, true);
        }

        // 创建 Job
        Job job = Job.getInstance(conf);

        // 设置 job 名称
        job.setJobName("sort4");

        // 设置自定义比较器
        job.setSortComparatorClass(MyDescRawCompartor.class);

        // 设置job运行的 Mapper，Reducer
        job.setMapperClass(FlowBeanSort4Mapper.class);
        job.setReducerClass(FlowBeanSort4Reducer.class);

        // 设置 Mapper，Reducer 的输出 key 和 value 类型。
        job.setMapOutputKeyClass(FlowBeanSort4.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBeanSort4.class);

        // 设置输入输出目录
        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outPath);

        // 运行 Job 并打印日志信息
        job.waitForCompletion(true);
    }
}
