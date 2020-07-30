package com.yanrs.mr.flowbean;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 输入 key 和 value 的类型分别为 Text 和 FlowBean
 *
 */
public class FlowBeanReducer extends Reducer <Text, FlowBean, Text, FlowBean>{

    private FlowBean outValue = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        // 累加每个手机号的上行流量和下行流量，并计算总流量
        long sumUpFlow = 0;
        long sumDownFlow = 0;

        for (FlowBean flowBean: values) {
            sumUpFlow += flowBean.getUpFlow();
            sumDownFlow += flowBean.getDownFlow();
        }

        // 将值封装进入 FlowBean 中
        outValue.setDownFlow(sumDownFlow);
        outValue.setUpFlow(sumUpFlow);
        outValue.setSumFlow(sumDownFlow + sumUpFlow);

        context.write(key, outValue);
    }
}
