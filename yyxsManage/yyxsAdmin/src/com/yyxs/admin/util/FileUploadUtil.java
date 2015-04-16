package com.yyxs.admin.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

/**
 * 图片上传util
 * @author power
 */
public class FileUploadUtil {
	
	/**
	 * 上传图片
	 * @param req
	 * @param resp
	 */
	public static Map<String, String> fileUploadUtil(HttpServletRequest req, HttpServletResponse resp, String uploadAddress) throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart == true) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			ServletFileUploadParamSetUp(upload);				//ServletFileUpload参数设置
			uploadProgress(upload);								//上传进度
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> itr = items.iterator();
			Map<String, String> fileNameMap = fileUpload(itr, uploadAddress);	//文件上传
			return fileNameMap;
		}
		return null;
	}
	
	/**
	 * ServletFileUpload参数设置
	 * @param upload
	 */
	private static void ServletFileUploadParamSetUp(ServletFileUpload upload){
		upload.setSizeMax(-1);
	}
	
	/**
	 * 上传进度
	 * @param upload
	 */
	private static void uploadProgress(ServletFileUpload upload){
		ProgressListener progressListener = new ProgressListener() {
			public void update(long pBytesRead, long pContentLength, int pItems) {}
		};
		upload.setProgressListener(progressListener);
	}
	
	/**
	 * 文件上传
	 * @param itr
	 * @apram uploadAddress		-->上传地址，不加文件名称
	 * @throws Exception
	 */
	private static Map<String, String> fileUpload(Iterator<FileItem> itr, String uploadAddress) throws Exception{
		Map<String, String> fileNameMap = new HashMap<String, String>();
		String fileName = StringUtils.EMPTY;
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if(StringUtils.isNotBlank(item.getName())){
				fileName = UUID.randomUUID() + getFileSuffix(item.getName());
				File savedFile = new File(uploadAddress, fileName);
				savedFile.getParentFile().mkdirs();		//没有文件系统自动创建
				item.write(savedFile);
				fileNameMap.clear();
				fileNameMap.put("filename", fileName);
			}
		}
		return fileNameMap;
	}
	
	/**
	 * 获取文件后缀
	 * @param fName	-->文件名称
	 */
	private static String getFileSuffix(String fName){
		return fName.substring(fName.lastIndexOf("."), fName.length());
	}
	
	/**
	 * 文件是否存在
	 * @param filePath	//文件地址
	 */
	public static boolean fileWhetherExists(String filePath){
		return new File(filePath).exists();
	}
	
	/**
	 * 文件删除
	 * @param filePath	//文件地址
	 */
	public static boolean fileDelete(String filePath){
		return new File(filePath).delete();
	}
	
	/**
	 * 获取图片后缀
	 * @param path
	 * @throws IOException
	 */
	public static String getImageSuffix(String path) throws IOException {     
        FileInputStream fis = new FileInputStream(path);     
        int leng = fis.available();     
        BufferedInputStream buff = new BufferedInputStream(fis);     
        byte[] mapObj = new byte[leng];     
        buff.read(mapObj, 0, leng);     
        String type = "";     
        ByteArrayInputStream bais = null;     
        MemoryCacheImageInputStream mcis = null;     
        bais = new ByteArrayInputStream(mapObj);     
        mcis = new MemoryCacheImageInputStream(bais);     
        Iterator<ImageReader> itr = ImageIO.getImageReaders(mcis);     
        while (itr.hasNext()) {     
            ImageReader reader = (ImageReader)  itr.next();     
            String imageName = reader.getClass().getSimpleName();     
            if(imageName!=null){  
                if("GIFImageReader".equals(imageName)){  
                    type = "gif";  
                }else if("JPEGImageReader".equals(imageName)){  
                    type = "jpg";  
                }else if("PNGImageReader".equals(imageName)){  
                    type = "png";  
                }else if("BMPImageReader".equals(imageName)){  
                    type = "bmp";  
                }else{  
                    type = "noPic";  
                }  
             }    
         }     
        if (bais != null) {     
                bais.close();     
        }     
        if (mcis != null) {     
                mcis.close();     
        }     
        return type;     
    } 
}
