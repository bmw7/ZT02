package com.mavict.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件操作工具
 * 
 * 单文件上传，前台格式：
 * <form action="upload2/fff" method="post" enctype="multipart/form-data">
 * <input type="file" name="myfile">
 * <input type="submit" value="提交"> 
 * </form>
 * 
 * @author 沧海软件(北京)有限公司
 * @version 1.0
 */
public class FileOperateUtils {
	
	private static String SEP = File.separator;
	
	/**
	 * 单文件上传 - 不限文件格式  - MultipartFile表单
	 * 
	 * @param originFile 上传的文件
	 * @param fileName 上传之后，在服务器端的文件名。命名时不需要文件类型后缀
	 * @param destDir 上传的目标文件夹 - "相对项目根目录,固定格式  /abc/cde/fgh"
	 * 
	 * @return 上传之后，返回 原文件名 和 文件服务器URL 数组
	 * */
	public static String[] uploadFile(MultipartFile originFile,String fileName,String destDir,HttpServletRequest request){
		String realDestDir = realDir(destDir, request);
		String fileNameWithSuffix = fileName+"."+getSuffix(originFile);
		File file = new File(realDestDir, fileNameWithSuffix);
	
		if (file.exists()) {
			file.delete();
		}
		
		try {
			FileUtils.copyInputStreamToFile(originFile.getInputStream(),file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] resultArray = new String[2];
		resultArray[0] = originFile.getOriginalFilename();
		resultArray[1] = request.getContextPath()+destDir+"/"+fileNameWithSuffix;
		
		return resultArray;
	}
	
	/**
	 * 多文件上传 - 不限文件格式  - MultipartFile表单
	 * 
	 * @param originFiles 上传的文件数组
	 * @param fileNames 上传之后，在服务器端的文件名数组。命名时不需要文件类型后缀
	 * @param destDir 上传的目标文件夹 - "相对项目根目录,固定格式  /abc/cde/fgh"
	 * 
	 * @return List<String[]> 每个文件一个String[],第一个元素为 原文件名   第二个元素为文件服务器地址
	 * */
	public static List<String[]> uploadFiles(MultipartFile[] originFiles,String[] fileNames,String destDir,HttpServletRequest request){
		String realDestDir = realDir(destDir, request);
		List<String[]> resultList = new ArrayList<String[]>();
		for (int i = 0; i < originFiles.length; i++) {
			String[] fileArray = new String[3];
			
			String fileNameWithSuffix = fileNames[i]+"."+getSuffix(originFiles[i]);
			File file = new File(realDestDir, fileNameWithSuffix);
		
			if (file.exists()) {
				file.delete();
			}
			
			try {
				FileUtils.copyInputStreamToFile(originFiles[i].getInputStream(),file);
				fileArray[0] = originFiles[i].getOriginalFilename();
				fileArray[1] = request.getContextPath()+destDir+"/"+fileNameWithSuffix;
				resultList.add(fileArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return resultList;
	}
	
	/**
	 * 单图片上传 - 限制为图片格式  - MultipartFile表单
	 * 
	 * @param originFile 上传的文件
	 * @param fileName 上传之后，在服务器端的文件名。命名时不需要文件类型后缀
	 * @param destDir 上传的目标文件夹 - "相对项目根目录,固定格式  /abc/cde/fgh"
	 * 
	 * @return 上传之后，返回 原文件名 和 文件服务器URL 数组
	 * */
	public static String[] uploadImage(MultipartFile originFile,String fileName,String destDir,HttpServletRequest request){
		
		String[] resultArray = new String[2];
		
		if (isImage(originFile)) {
			
			String realDestDir = realDir(destDir, request);
			String fileNameWithSuffix = fileName+"."+getSuffix(originFile);
			File file = new File(realDestDir, fileNameWithSuffix);
		
			if (file.exists()) {
				file.delete();
			}
			
			try {
				FileUtils.copyInputStreamToFile(originFile.getInputStream(),file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			resultArray[0] = originFile.getOriginalFilename();
			resultArray[1] = request.getContextPath()+destDir+"/"+fileNameWithSuffix;
		}
			
		return resultArray;
	}
	
	
	/**
	 * 多图片上传 - 限制为图片格式  - MultipartFile表单
	 * 
	 * @param originFile 上传的图片文件数组
	 * @param fileName 上传之后，在服务器端的文件名数组。命名时不需要文件类型后缀
	 * @param destDir 上传的目标文件夹 - "相对项目根目录,固定格式  /abc/cde/fgh"
	 * 
	 * @return List<String[]> 每个文件一个String[],第一个元素为 原文件名   第二个元素为文件服务器地址
	 * */
	public static List<String[]> uploadImages(MultipartFile[] originFiles,String[] fileNames,String destDir,HttpServletRequest request){
		
		String realDestDir = realDir(destDir, request);
		List<String[]> resultList = new ArrayList<String[]>();
		for (int i = 0; i < originFiles.length; i++) {
			
			if (isImage(originFiles[i])) {		
				
				String[] fileArray = new String[3];
				
				String fileNameWithSuffix = fileNames[i]+"."+getSuffix(originFiles[i]);
				File file = new File(realDestDir, fileNameWithSuffix);
			
				if (file.exists()) {
					file.delete();
				}
				
				try {
					FileUtils.copyInputStreamToFile(originFiles[i].getInputStream(),file);
					fileArray[0] = originFiles[i].getOriginalFilename();
					fileArray[1] = request.getContextPath()+destDir+"/"+fileNameWithSuffix;
					resultList.add(fileArray);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
			
		}
		return resultList;
	}
	
	/**
	 * 单图片上传 - request中的图片文件 - 百度webuploader组件使用
	 * 
	 * @param fileName 文件名 - 无后缀
	 * @param desDir 目标文件夹,格式为 "/abc/cde/fgh" - 相对项目根目录
	 * 
	 * @return 上传是否成功
	 * */
	public static File uploadImage(HttpServletRequest request,String fileName,String destDir){
		
		String suffix = getSuffix(request.getParameter("name"));              // 获取文件名后缀
		
		if (isImage(suffix)) {
			String realDestDir = realDir(destDir, request);
			String fileNameWithSuffix = fileName+"."+suffix;
			File file = new File(realDestDir, fileNameWithSuffix);
			
			try {
				InputStream inputStream = request.getInputStream(); // 获取文件输入流
				FileUtils.copyInputStreamToFile(inputStream, file); // 上传
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			return file.exists() ? file : null;
		}
		
		return null;	
	}
	
	/**
	 * 获取随机字符串
	 * 
	 * @return 随机字符串
	 * */
	public static String getRandomString(){
		long randomBase = System.currentTimeMillis();
		int randomA = new Random().nextInt(10);
		int randomB = new Random().nextInt(10);
		return String.valueOf(randomBase)+String.valueOf(randomA)+String.valueOf(randomB);
	}
	
	/** 
	 * 判断目录dir下是否存在文件
	 * @param dir - 格式为相对根目录 /abc/efg 样式
	 * */
	public static boolean isExistFiles(String dir){
		File dirFile = new File(dir);
		return (dirFile.listFiles().length > 0) ? true : false;
	}
	
	/** 
	 * 判断上传的文件是否是图片格式
	 * 
	 * @param suffix 文件后缀名
	 * 
	 * @return 是否为图片
	 * */
	public static boolean isImage(String suffix){
		String[] images = new String[]{"jpg","jpeg","png","gif","bmp"}; 
		return Arrays.asList(images).contains(suffix.toLowerCase());	
	}
	
	/** 
	 * 判断上传的文件是否是图片格式
	 * 
	 * @param file 上传的文件
	 * 
	 * @return 是否为图片
	 * */
	public static boolean isImage(MultipartFile file){
		String suffix = getSuffix(file);
		String[] images = new String[]{"jpg","jpeg","png","gif","bmp"}; 
		return Arrays.asList(images).contains(suffix);	
	}
	
	/** 
	 * 获取文件名后缀 
	 * 
	 * @param file MultipartFile格式文件
	 * 
	 * @return 文件名后缀
	 * */
	public static String getSuffix(MultipartFile file){
		String names[] = file.getOriginalFilename().split("\\.");
		return names[names.length - 1];
	}
	
	/** 
	 * 获取文件名后缀 
	 * 
	 * @param fileName 文件名
	 * 
	 * @return 文件名后缀
	 * */
	public static String getSuffix(String fileName){
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	/**
	 * 目录转换 - 由 '/abc/edf' 格式转换为 兼容目录格式 (跨平台)
	 * 
	 * @param dir - /abc/edf' 格式
	 * 
	 * @return  兼容目录格式 (跨平台)
	 * */
	public static String compatibleDir(String dir,HttpServletRequest request){
		String[] dirArrays = dir.split("/");
		String compatibleDir = "";
		for (int i = 0; i < dirArrays.length; i++) {
			compatibleDir+=(SEP+dirArrays[i]);
		}
		return compatibleDir;
	}
	
	/**
	 * 目录转换 - 由 '/abc/edf' 格式转换为 File文件格式 (跨平台)
	 * 
	 * @param dir - /abc/edf' 格式
	 * 
	 * @return  File文件格式 (跨平台)
	 * */
	public static File dirFile(String dir,HttpServletRequest request){
		String realDir = request.getSession().getServletContext().getRealPath(compatibleDir(dir,request));
		return new File(realDir);
	}
	
	/** 
	 * 目录处理 - 经过getRealPath处理的真实目录地址;目录不存在则会创建
	 * 
	 * @param dir 文件夹地址 - "相对项目根目录,固定格式  /abc/cde/fgh"
	 * 
	 * @return getRealPath处理过的真实目录地址
	 * */
	public static String realDir(String dir,HttpServletRequest request){
		String realDir = request.getSession().getServletContext().getRealPath(compatibleDir(dir,request));
		File realDirFile = new File(realDir);
		if (!realDirFile.exists()) {
			realDirFile.mkdirs();
		}
		return realDir;
	}
	
	/** 
	 * 删除目录下所有文件 
	 * 
	 * @param dir 文件夹地址 - "相对项目根目录,固定格式  /abc/cde/fgh"
	 * 
	 * @return true or false
	 * */
	public static void delFiles(String dir,HttpServletRequest request){
		File dirFile = dirFile(dir, request);
		if (dirFile.exists()) {
			File[] dirFiles = dirFile.listFiles();
			if (dirFiles.length > 0) {
				for (int i = 0; i < dirFiles.length; i++) {
					dirFiles[i].delete();
				}
			}
			dirFile.delete();
		}

	}
	
	
}
