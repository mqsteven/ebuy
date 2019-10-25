package com.igeek;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.igeek.ebuy.util.fdfs.FastDFSClient;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月25日 上午9:41:29
 */
public class TestFastDFS {
	
	
	//@Test
	public void testFastDFSClient1() {
		FastDFSClient client = new FastDFSClient("E:\\58营\\项目\\workspace\\ebuy-manager-web\\src\\test\\resources\\client.conf");
		try {
			File file = new File("C:\\Users\\cp\\Pictures\\images\\timg (5).jpg");
			FileInputStream in = new FileInputStream(file);
			byte [] buff = new byte[(int)file.length()];
			in.read(buff);
			String path = client.uploadFile(buff, "jpg");
			System.out.println(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testFastDFSClient() {
		FastDFSClient client = new FastDFSClient("E:\\58营\\项目\\workspace\\ebuy-manager-web\\src\\test\\resources\\client.conf");
		try {
			String path = client.uploadFile("C:\\Users\\cp\\Pictures\\images\\timg (1).jpg", "jpg");
			System.out.println(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	//@Test
	public void testFastDFS() throws FileNotFoundException, IOException, MyException {
//		1、加载配置文件，配置文件中的内容就是tracker服务的地址。
		ClientGlobal.init("E:\\58营\\项目\\workspace\\ebuy-manager-web\\src\\test\\resources\\client.conf");
//		配置文件内容：tracker_server=192.168.128.154:22122
//		2、创建一个TrackerClient对象。直接new一个。
		TrackerClient trackerClient = new TrackerClient();
//		3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
		TrackerServer trackerServer = trackerClient.getConnection();
//		4、创建一个StorageServer的引用，值为null
		StorageServer storageServer = null;
//		5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//		6、使用StorageClient对象上传图片。
		String[] infos = storageClient.upload_file("C:\\Users\\cp\\Pictures\\images\\caocao.jpg", "jpg", null);
//		7、返回数组。包含组名和图片的路径。
		for (int i = 0; i < infos.length; i++) {
			System.out.println(infos[i]);
		}
	}
}
