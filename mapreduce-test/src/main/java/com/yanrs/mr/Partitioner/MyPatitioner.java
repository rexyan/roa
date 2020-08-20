package com.yanrs.mr.Partitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * KEY , VALUE 是 Mapper 输出的 key，value 的类型
 */
public class MyPatitioner extends Partitioner<Text, PartitionerFlowBean> {

    // getPartition 方法就是计算分区，numPartitions 为总的分区数，也就是 ReduceTask 的数量
    @Override
    public int getPartition(Text text, PartitionerFlowBean partitionerFlowBean, int numPartitions) {
        int partitionNum = 0;

        // 获取手机号的前三位
        String suffix = text.toString().substring(0, 3);

        switch(suffix){
            case "136":
                partitionNum = 1;  // 手机号如果是 136 开头，那么就分到 1 分区
                break;
            case "137":
                partitionNum = 2;  // 手机号如果是 137 开头，那么就分到 2 分区
                break;
            case "138":
                partitionNum = 3;  // 手机号如果是 138 开头，那么就分到 3 分区
                break;
            case "139":
                partitionNum = 4;  // 手机号如果是 139 开头，那么就分到 4 分区
                break;
        }
        return partitionNum;
    }
}
