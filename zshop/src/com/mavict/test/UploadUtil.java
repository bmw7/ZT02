package com.mavict.test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件工具
 * 
 * @author 沧海软件(北京)有限公司
 * @date   2015年8月7日 下午2:09:50
 */

public class UploadUtil {

	/** 判断目录dir下是否存在文件 */
	public static boolean isExistFiles(String dir){
		File dirFile = new File(dir);
		return (dirFile.listFiles().length > 0) ? true : false;
	}
	
	/** 获取目录dir下存在的所有文件信息  
	 *  @return Map<真实地址,原文件名> 
	 * */
	public static Map<String,String> getFiles(String dir) throws IOException{
		Map<String, String> fileMap = new HashMap<String, String>();
		
		File dirFile = new File(dir);
		File[] files = dirFile.listFiles();
		
		Iterator<File> markFiles = FileUtils.iterateFiles(dirFile, new String[]{"mark"}, false);
    	File markFile = markFiles.next();
    	String names[] = null;
    	if (markFile.length() == 0) {
			names = markFile.getName().replace(".mark", "").split("\\[\\&\\&\\]");
		}else{
			names = (markFile.getName().replace(".mark", "")+FileUtils.readFileToString(markFile, "UTF-8")).split("\\[\\&\\&\\]");
		}
		
		for (File file : files) {
			if (!file.equals(markFile)) {   // 此处用 file != markFile 失效
				int pos = Integer.parseInt((file.getName().split("\\."))[0]);
				String realName = file.getName();
				String originName = names[pos];
				fileMap.put(realName, originName);
			}	
		}	
		return fileMap;
	}
	
	/** 给图片加水印 */
	public static void addWaterMark(String watermark,String imagePath){
		
	}
	
	/** 判断上传的文件是否是图片格式*/
	public static boolean isImage(MultipartFile file){
		String suffix = getSuffix(file);
		String[] images = new String[]{"jpg","jpeg","png","gif","bmp"}; 
		return Arrays.asList(images).contains(suffix);	
	}
	
	/** 获取文件名后缀 */
	public static String getSuffix(MultipartFile file){
		String names[] = file.getOriginalFilename().split("\\.");
		return names[names.length - 1];
		//string type = filename.Substring(filename.LastIndexOf(".") + 1);//得到加载文件的扩展名
	}
	
}
