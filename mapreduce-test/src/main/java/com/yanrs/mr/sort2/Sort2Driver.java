package com.yanrs.mr.sort2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 启动这个进程，那么就会运行该 job
 */
public class Sort2Driver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        // 获取文件系统
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop10:9000");

        FileSystem fileSystem = FileSystem.get(conf);

        // 设置输入目录和输出目录
        Path inputPath = new Path("/mroutput/flowbean/part-r-00000");
        Path outPath = new Path("/mroutput/flowbean/sort2");
        // 输出目录存在就删除
        if(fileSystem.exists(outPath)){
            fileSystem.delete(outPath, true);
        }

        // 创建 Job
        Job job = Job.getInstance(conf);

        // 设置 job 名称
        job.setJobName("sort1");

        // 设置使用自定义的比较器
        job.setSortComparatorClass(MyDescComparator.class);

        // 设置job运行的 Mapper，Reducer
        job.setMapperClass(Sort2Mapper.class);
        job.setReducerClass(Sort2Reducer.class);

        // 设置 Mapper，Reducer 的输出 key 和 value 类型。
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // 设置输入输出目录
        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outPath);

        // 运行 Job 并打印日志信息
        job.waitForCompletion(true);
    }
}
