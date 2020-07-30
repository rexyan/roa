package com.yanrs.mr.nline;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 启动这个进程，那么就会运行该 job
 */
public class NLDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        // 获取文件系统
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop10:9000");
        // 设置几行为一片，默认一行一片
        // conf.set("mapreduce.input.lineinputformat.linespermap", "2");

        FileSystem fileSystem = FileSystem.get(conf);

        // 设置输入目录和输出目录
        Path inputPath = new Path("/mrinput/nline");
        Path outPath = new Path("/mroutput/nline");
        // 输出目录存在就删除
        if(fileSystem.exists(outPath)){
            fileSystem.delete(outPath, true);
        }

        // 创建 Job
        Job job = Job.getInstance(conf);

        // 设置使用 NLineInputFormat
        job.setInputFormatClass(NLineInputFormat.class);

        // 设置 job 名称
        job.setJobName("nline");

        // 设置job运行的 Mapper，Reducer
        job.setMapperClass(NLMapper.class);
        job.setReducerClass(NLReducer.class);

        // 设置 Mapper，Reducer 的输出 key 和 value 类型。
        // job 需要根据 Mapper，Reducer 输出的 key value 类型准备序列化器，通过序列化器对输出的 key value 进行序列化和反序列化
        // 如果 Mapper，Reducer 输出的 key 和 value 类型一致，那么可以像下面一样直接设置 job 的最终输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置输入输出目录
        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outPath);

        // 运行 Job 并打印日志信息
        job.waitForCompletion(true);
    }
}
