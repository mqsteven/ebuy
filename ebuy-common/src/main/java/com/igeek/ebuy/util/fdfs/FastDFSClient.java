package com.igeek.ebuy.util.fdfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * @author www.igeekhome.com
 * 
 * @description TODO
 *
 * 2019年3月25日 上午10:12:55
 */
public class FastDFSClient {
	//1 绝对路径
	//2 classpath:相对路径
	private String confPath = null;
	public FastDFSClient(String confPath) {
		this.confPath = confPath;
		try {
			if(confPath.indexOf("classpath")!=-1) {
				String path = confPath.substring(confPath.indexOf("classpath")+10);
				//相对路径
				URL resource = FastDFSClient.class.getClassLoader().getResource(path);
				String path2 = resource.getPath();
				//根据获取的绝对路径加载配置文件
				ClientGlobal.init(path2);
			}else {
				//绝对路径
				ClientGlobal.init(confPath);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		}
	}
	
	public String uploadFile(String fileName,String ext) throws Exception {
//		2、创建一个TrackerClient对象。直接new一个。
		TrackerClient trackerClient = new TrackerClient();
//		3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
		TrackerServer trackerServer = trackerClient.getConnection();
//		4、创建一个StorageServer的引用，值为null
		StorageServer storageServer = null;
//		5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//		6、使用StorageClient对象上传图片。
		String[] infos = storageClient.upload_file(fileName, ext, null);
//		7、返回数组。包含组名和图片的路径。
		return infos[0]+"/"+infos[1];
	}
	
	public String uploadFile(byte[] fileData,String ext) throws Exception {
//		2、创建一个TrackerClient对象。直接new一个。
		TrackerClient trackerClient = new TrackerClient();
//		3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
		TrackerServer trackerServer = trackerClient.getConnection();
//		4、创建一个StorageServer的引用，值为null
		StorageServer storageServer = null;
//		5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//		6、使用StorageClient对象上传图片。
		String[] infos = storageClient.upload_file(fileData, ext, null);
//		7、返回数组。包含组名和图片的路径。
		return infos[0]+"/"+infos[1];
	}
	
}
