package com.yanrs.mr.Partitioner;

import com.yanrs.mr.flowbean.FlowBean;
import com.yanrs.mr.flowbean.FlowBeanMapper;
import com.yanrs.mr.flowbean.FlowBeanReducer;
import com.yanrs.mr.wordcount.WCMapper;
import com.yanrs.mr.wordcount.WCReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * 启动这个进程，那么就会运行该 job
 */
public class PartitionerFlowBeanDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        // 获取文件系统
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop10:9000");

        FileSystem fileSystem = FileSystem.get(conf);

        // 设置输入目录和输出目录
        Path inputPath = new Path("/mrinput/flowbean");
        Path outPath = new Path("/mroutput/partition");
        // 输出目录存在就删除
        if(fileSystem.exists(outPath)){
            fileSystem.delete(outPath, true);
        }

        // 创建 Job
        Job job = Job.getInstance(conf);

        // 设置 ReduceTask 的数量为  5
        job.setNumReduceTasks(5);
        // 设置使用自定义分区器
        job.setPartitionerClass(MyPatitioner.class);

        // 设置 job 名称
        job.setJobName("PartitionerFlowBean");

        // 设置job运行的 Mapper，Reducer
        job.setMapperClass(PartitionerFlowBeanMapper.class);
        job.setReducerClass(PartitionerFlowBeanReducer.class);

        // 设置 Mapper，Reducer 的输出 key 和 value 类型。
        // job 需要根据 Mapper，Reducer 输出的 key value 类型准备序列化器，通过序列化器对输出的 key value 进行序列化和反序列化
        // 如果 Mapper，Reducer 输出的 key 和 value 类型一致，那么可以像下面一样直接设置 job 的最终输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PartitionerFlowBean.class);

        // 设置输入输出目录
        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outPath);

        // 运行 Job 并打印日志信息
        job.waitForCompletion(true);
    }
}
