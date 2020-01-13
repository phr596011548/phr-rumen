package cn.p.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.*;
import java.util.UUID;

public class FileUtilesalbb {
	/*
	 * 将文件保存到指定目录下   
	 * 返回保存文件的路径
	 */
	private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	// 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
	private static String accessKeyId = "LTAI4FtYvfXqMMKtMwuL1vJV";
	private static String accessKeySecret = "NLfdVSpWvxj4uAebtrX5A5C0mxDtFA";

	// Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
	// Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
	private static String	 bucketName = "feihu-1905b";

	public static String  saveFile(InputStream is,String filename) {
		String fileType=filename.substring(filename.lastIndexOf("."));
		//设置新名  （必须唯一）   uuid 随机生成32位的字符串 不重复
		String newName=UUID.randomUUID().toString()+fileType;

		OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
		ossClient.putObject(bucketName,newName,is);
		/*http://你的BucketName.你的Endpoint/自定义路径/*/
		String url ="http://"+bucketName+".oss-cn-beijing.aliyuncs.com/"+newName;
		return url;
	}

}
