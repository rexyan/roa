from hdfs import InsecureClient

client = InsecureClient('http://hadoop10:50070', user='rexyan')


def get_file_content(file_path):
    """
    读取文件内容
    :param file_path:
    :return:
    """
    with client.read(file_path, encoding="utf-8", buffer_size=1024) as reader:
        context = reader.read()
        return context


def upload_file(local_path, origin_path):
    """
    上传本地文件到 HDFS 中
    :param local_path:
    :param origin_path:
    :return:
    """
    with open(local_path, encoding="utf-8") as reader, client.write(origin_path, encoding="utf-8") as writer:
        for content in reader:
            writer.write(content)


def get_file_info(origin_path):
    """
    获取文件信息
    :param origin_path:
    :return:
    """
    # 文件/路径 信息
    content = client.content(origin_path)
    print(content)

    # 文件夹下所有文件名称
    fnames = client.list(origin_path)
    print(fnames)

    # 文件/路径 状态信息
    status = client.status(origin_path)
    print(status)

    # 重命名
    # client.rename('dat/features', 'features')
    # 删除文件
    # client.delete('dat', recursive=True)


if __name__ == '__main__':
    # print(get_file_content("/test_java_client/uploadFile"))
    # upload_file("/tmp/testFile", "/test_python_client/uploadFile")
    get_file_info("/")
