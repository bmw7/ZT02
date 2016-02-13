package com.mavict.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



/**
 * 注意，用/upload不行,原因未知。改为/fileUpload
 * 
 * @author 沧海软件(北京)有限公司
 * @date   2015年8月6日 上午10:40:21
 */
@Controller
@RequestMapping("/fileUpload")
public class UploadController {

	private static String SP = File.separator;
	private String originalFilename = null;
	
	/**
	 * 将图片上传至临时文件夹 /upload/images/temp
	 * */
	@RequestMapping("/article/images") 
    public String uploadArticleImages(@RequestParam MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws Exception{  
        String tempDir = request.getSession().getServletContext().getRealPath(SP+"upload"+SP+"article"+SP+"temp"+SP+"images");  
        response.setContentType("text/plain; charset=UTF-8");  
        PrintWriter out = response.getWriter();  
          
        
        for(MultipartFile myfile : myfiles){
        	
        	
        	if (UploadUtil.isImage(myfile)) {
				
        		
        		
        		
        		if(myfile.isEmpty()){  
                    out.print("1`请选择文件后上传");  
                    out.flush();  
                    return null;  
                }else{  
                    originalFilename = myfile.getOriginalFilename();         
                    try {  
                    	
                    	/** 找到上传目录中 后缀为.mark的标志文件。若没有，则创建[0].mark*/
                    	File markFile = findMarkFile(tempDir);
                    
                    	/** 上传文件，并序列化文件名*/
                    	File file = new File(tempDir, createSerialFileName(myfile, tempDir));
                    	FileUtils.copyInputStreamToFile(myfile.getInputStream(), file);  
                    	
                    	/** 将上传文件的文件名附加到 文件名标注文件 的文件之后，以[&&]做间隔，构造出新的文件名 */
                    	handleMarkFile(markFile, tempDir);
                    	
                    } 
                    catch (IOException e) {  
                        System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");  
                        e.printStackTrace();  
                        out.print("1`文件上传失败，请重试！！");  
                        out.flush();  
                        return null;  
                    }  
                }
                

        		
			}else{
				System.out.println("------------------------------您上传的不是图片-----------------------------------------");
			}
        	  

            
        }  
        
        out.print("0`" + request.getContextPath() + "/upload/" + originalFilename);  
        out.flush();  
        return null;  
    } 
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/article/files") 
    public String uploadArticleFiles(@RequestParam MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws Exception{  
        String tempDir = request.getSession().getServletContext().getRealPath(SP+"upload"+SP+"article"+SP+"temp"+SP+"files");  
        response.setContentType("text/plain; charset=UTF-8");  
        PrintWriter out = response.getWriter();  
                  
        for(MultipartFile myfile : myfiles){		
        		        		
        		if(myfile.isEmpty()){  
                    out.print("1`请选择文件后上传");  
                    out.flush();  
                    return null;  
                }else{  
                    originalFilename = myfile.getOriginalFilename();         
                    try {  
                    	
                    	/** 找到上传目录中 后缀为.mark的标志文件。若没有，则创建[0].mark*/
                    	File markFile = findMarkFile(tempDir);
                    
                    	/** 上传文件，并序列化文件名*/
                    	File file = new File(tempDir, createSerialFileName(myfile, tempDir));
                    	FileUtils.copyInputStreamToFile(myfile.getInputStream(), file);  
                    	
                    	/** 将上传文件的文件名附加到 文件名标注文件 的文件之后，以[&&]做间隔，构造出新的文件名 */
                    	handleMarkFile(markFile, tempDir);
                    	
                    } 
                    catch (IOException e) {  
                        System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");  
                        e.printStackTrace();  
                        out.print("1`文件上传失败，请重试！！");  
                        out.flush();  
                        return null;  
                    }  
                }
                        
        }  
        
        out.print("0`" + request.getContextPath() + "/upload/" + originalFilename);  
        out.flush();  
        return null;  
    } 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 依据临时文件夹中文件数目，构造序列化的数字文件名
	 * */
	public String createSerialFileName(MultipartFile file,String dir){
		File dirFile = new File(dir);
		File[] files = dirFile.listFiles();
		int size = files.length;	
		return String.valueOf(size)+"."+UploadUtil.getSuffix(file);
	}
	
	
	/** 
	 * 找到临时上传目录tempDir中 后缀为.mark的标志文件。若没有，则创建[0].mark
	 * @throws IOException 
	 * */
	public File findMarkFile(String tempDir) throws IOException{
		
    	File tempDirFile = new File(tempDir);
    	if (!tempDirFile.exists()) { tempDirFile.mkdirs(); }
		
    	Iterator<File> markFiles = FileUtils.iterateFiles(tempDirFile, new String[]{"mark"}, false);
    	File markFile = null;

    	if (markFiles.hasNext()) {  
    		markFile = markFiles.next();  
    	} 
    	else {
    		markFile = new File(tempDir,"[0].mark");  /** 若不存在，则创建初始 文件名标注文件 [0].mark */
			markFile.createNewFile();
		}
    	
    	return markFile;
	}
	
	/** 
	 * 1、markFile文件名不超过250字节时，将上传文件的文件名附加到 文件名标注文件 的文件之后，以[&&]做间隔，构造出新的文件名 
	 * 2、构造新的markFile的文件名的长度将要超过250字节时，放弃修改文件名，转而将上传的文件名写入已经存在的markFile文件内
	 * 3、操作系统对文件名长度有限制
	 * */
	public void handleMarkFile(File markFile,String tempDir) throws IOException{

    	String noSuffixMarkFileName = markFile.getName().replace(".mark", "");
    	String newName = noSuffixMarkFileName+"[&&]"+originalFilename+".mark";
    	
		if (markFile.length() == 0 && newName.getBytes().length < 250) {
	    	File newMarkFile = new File(tempDir,newName);
	    	markFile.renameTo(newMarkFile);
		}else{
			String content = FileUtils.readFileToString(markFile,"UTF-8");
			FileUtils.write(markFile, content+"[&&]"+originalFilename,"UTF-8");	
		}	
	}
	

	
}
