package com.yanrs.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFSClient {
    @Test
    public void testMkdirs() throws IOException, URISyntaxException, InterruptedException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();

        // 配置在集群上运行
        // configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
        // FileSystem fs = FileSystem.get(configuration);
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");

        // 2 创建目录
        fs.mkdirs(new Path("/test_java_client"));
        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testMkdirConfigForXml() throws IOException, URISyntaxException, InterruptedException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");
        fs.copyFromLocalFile(false, true, new Path("/tmp/testFile"), new Path("/test_java_client"));
        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testRename() throws IOException, URISyntaxException, InterruptedException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");
        fs.rename(new Path("/test_java_client/testFile"), new Path("/test_java_client/newTestFile"));
        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testFileInfo() throws IOException, URISyntaxException, InterruptedException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");

        // 递归获取 / 目录下的文件信息
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()){
            LocatedFileStatus status = listFiles.next();

            System.out.println("-----------" + status.getPath().getName() + "-----------" );
            System.out.println(status.getAccessTime());
            System.out.println(status.getBlockSize());
            System.out.println(status.getModificationTime());
            System.out.println(status.getGroup());
            System.out.println(status.getOwner());
            System.out.println(status.getLen());
            System.out.println(status.getPermission());
            System.out.println(status.getReplication());

            // 获取文件块信息
            BlockLocation[] blockLocations = status.getBlockLocations();
            for (BlockLocation blockLocation:blockLocations) {
                // 块名称
                String[] names = blockLocation.getNames();
                for (String name:names) {
                    System.out.println(name);
                }
                // 块所在机器IP
                String[] hosts = blockLocation.getHosts();
                for (String host:hosts) {
                    System.out.println(host);
                }
                // 块大小
                System.out.println(blockLocation.getLength());
                // 块偏移量
                System.out.println(blockLocation.getOffset());
            }
        }
        // 3 关闭资源
        fs.close();
    }

    @Test
    public void testIsFile() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");
        System.out.println(fs.isDirectory(new Path("/test_java_client/newTestFile")));
        System.out.println(fs.isFile(new Path("/test_java_client/newTestFile")));
    }

    @Test
    public void testDownFile() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");
        fs.copyToLocalFile(new Path("/test_java_client/newTestFile"), new Path("/tmp/newTestFile"));
    }

    @Test
    public void testRemoveFile() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");
        fs.delete(new Path("/test_java_client/newTestFile"), true);
    }

    @Test
    public void testCustomizeUpload() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");

        // 创建输入流
        FileInputStream fileInputStream = new FileInputStream(new File("/Users/rex/Downloads/06_项目二在线教育 （隔壁班讲的，可看可不看）/工具.zip"));
        // 创建输出流
        FSDataOutputStream fsDataOutputStream = fs.create(new Path("/test_java_client/bigfile"));
        // 流的拷贝
        IOUtils.copyBytes(fileInputStream, fsDataOutputStream, 1024);

        // 关闭资源
        IOUtils.closeStream(fsDataOutputStream);
        IOUtils.closeStream(fileInputStream);
        fs.close();
    }

    @Test
    public void testCustomizeDown() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");
        // 获取输入流
        FSDataInputStream dataInputStream = fs.open(new Path("/test_java_client/uploadFile"));
        // 创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream(new File("/tmp/downFile"));
        // 流的拷贝
        IOUtils.copyBytes(dataInputStream, fileOutputStream, 1024);

        // 关闭资源
        IOUtils.closeStream(dataInputStream);
        IOUtils.closeStream(fileOutputStream);
        fs.close();
    }

    @Test
    public void testFirstBlockDown() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");
        // 获取输入流
        FSDataInputStream dataInputStream = fs.open(new Path("/test_java_client/bigfile"));
        // 创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream(new File("/tmp/bigfile.part1"));

        // 下载 1024 * 128（也就是 128M 的文件的大小）
        byte[] bytes = new byte[1024];
        for (int i = 0; i < 1024 * 128; i++) {
            dataInputStream.read(bytes);
            fileOutputStream.write(bytes);
        }
        // 关闭资源
        IOUtils.closeStream(dataInputStream);
        IOUtils.closeStream(fileOutputStream);
        fs.close();
    }

    @Test
    public void testSecondBlockDown() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop10:9000"), configuration, "rexyan");
        // 获取输入流
        FSDataInputStream dataInputStream = fs.open(new Path("/test_java_client/bigfile"));
        // 创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream(new File("/tmp/bigfile.part2"));
        // 定位数据文件
        dataInputStream.seek(1024*1024*128);
        // 拷贝 128M 之后的数据
        IOUtils.copyBytes(dataInputStream, fileOutputStream, 1024);
        // 关闭资源
        IOUtils.closeStream(dataInputStream);
        IOUtils.closeStream(fileOutputStream);
        fs.close();
    }
}
