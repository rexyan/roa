package com.yanrs.mr.sort3;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class FlowBeanSort3 implements WritableComparable<FlowBeanSort3> {
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    /**
     * 序列化, 在写出属性时，如果属性为引用数据类型，那么属性不能为 null
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    /**
     * 反序列化，反序列化和序列化的顺序要一致
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        upFlow = dataInput.readLong();
        downFlow = dataInput.readLong();
        sumFlow = dataInput.readLong();
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    public FlowBeanSort3() {
    }

    @Override
    public String toString() {
        return "PartitionerFlowBean{" +
                "upFlow=" + upFlow +
                ", downFlow=" + downFlow +
                ", sumFlow=" + sumFlow +
                '}';
        }

    @Override
    public int compareTo(FlowBeanSort3 flowBeanSort3) {
        return this.sumFlow -flowBeanSort3.getSumFlow() > 0?-1:1;
    }
}
