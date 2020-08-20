package com.yanrs.mr.Partitioner;

import com.yanrs.mr.flowbean.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 输入 key 和 value 的类型分别为 Text 和 PartitionerFlowBean
 *
 */
public class PartitionerFlowBeanReducer extends Reducer <Text, PartitionerFlowBean, Text, PartitionerFlowBean>{

    private PartitionerFlowBean outValue = new PartitionerFlowBean();

    @Override
    protected void reduce(Text key, Iterable<PartitionerFlowBean> values, Context context) throws IOException, InterruptedException {
        // 累加每个手机号的上行流量和下行流量，并计算总流量
        long sumUpFlow = 0;
        long sumDownFlow = 0;

        for (PartitionerFlowBean flowBean: values) {
            sumUpFlow += flowBean.getUpFlow();
            sumDownFlow += flowBean.getDownFlow();
        }

        // 将值封装进入 PartitionerFlowBean 中
        outValue.setDownFlow(sumDownFlow);
        outValue.setUpFlow(sumUpFlow);
        outValue.setSumFlow(sumDownFlow + sumUpFlow);

        context.write(key, outValue);
    }
}
