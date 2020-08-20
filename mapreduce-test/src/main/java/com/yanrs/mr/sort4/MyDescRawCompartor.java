package com.yanrs.mr.sort4;

import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.RawComparator;

import java.io.IOException;

public class MyDescRawCompartor implements RawComparator<FlowBeanSort4> {
    private  FlowBeanSort4 key1 = new FlowBeanSort4();
    private  FlowBeanSort4 key2 = new FlowBeanSort4();
    private  DataInputBuffer buffer = new DataInputBuffer();
    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
        try {
            this.buffer.reset(b1, s1, l1);
            this.key1.readFields(this.buffer);
            this.buffer.reset(b2, s2, l2);
            this.key2.readFields(this.buffer);
            this.buffer.reset((byte[])null, 0, 0);
        } catch (IOException var8) {
            throw new RuntimeException(var8);
        }

        return this.compare(this.key1, this.key2);
    }

    @Override
    public int compare(FlowBeanSort4 o1, FlowBeanSort4 o2) {
        return o1.getSumFlow() - o2.getSumFlow() > 0?-1:1;
    }
}
