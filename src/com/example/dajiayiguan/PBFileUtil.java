package com.example.dajiayiguan;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import android.os.Handler;
import android.util.Log;

public class PBFileUtil {
	
	private static final String TAG = "SNSFileUtil";
	/**
	 * 从一个InputStream中读取全部内容到一个字符串String。
	 * @param in - 输入流
	 * @return 文本内容
	 */
	public static String readTextFromInputStream(InputStream in) 
	{
		char[] bytes = new char[4096];
		StringBuilder str = new StringBuilder();
		try {
			InputStreamReader reader = new InputStreamReader(in, "UTF-8");
			int len = reader.read(bytes);
			while(len>0) {
				str.append(bytes, 0, len);
				len = reader.read(bytes);
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	
	/**
	 * 从一个文件中读取全部内容到一个字符串String。
	 * @param filepath - 输入文件
	 * @return 文本内容
	 */
	public static String readTextFromFile(String filepath)
	{
		char[] bytes = new char[4096];
		StringBuilder str = new StringBuilder();
		try {
			File f = new File(filepath);
			if(!f.exists()) return null;
			FileReader reader = new FileReader(filepath);
			int len = reader.read(bytes);
			while(len>0) {
				str.append(bytes, 0, len);
				len = reader.read(bytes);
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	
	/**
	 * 将字符串写入一个文件。
	 * @param filepath － 带目录的文件名
	 * @param content － 写入内容
	 * @param append － 是否添加模式
	 * @return － 写入成功返回true，失败返回false
	 */
	
	public static Boolean writeTextToFile(String filepath, String content, Boolean append)
	{
		try {
			FileWriter wr = new FileWriter(filepath, append);
			wr.write(content);
			wr.close();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static Boolean writeTextToFile(String filepath, String content)
	{
		return writeTextToFile(filepath,content,false);
	}

	/**
	 * 将字符串写入一个文件。
	 * @param filepath － 带目录的文件名
	 * @param content － 写入内容
	 * @param append － 是否添加模式
	 * @return － 写入成功返回true，失败返回false
	 */
	
	public static Boolean writeTextToOutputStream(OutputStream out, String content)
	{
		try {
			out.write(content.getBytes("UTF-8"));
			// out.close();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean writeBytesArrayToFile( byte[] bytes, String file )
	{
		try {
			
			File f = new File(file);
			f.delete();
			FileOutputStream output = new FileOutputStream( f );
			output.write(bytes);
			output.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static boolean unpackZip(String zipname)
	{
		int len = zipname.length()-1;
		while(zipname.charAt(len)!='/') { 
			len--;
			if(len==0) break;
		}
		String path = zipname.substring(0,len+1);
		String fileName = zipname.substring(len+1);
		return unpackZip(path, fileName);
	}
	public static boolean unpackZip(String path, String zipname)
	{       
	     InputStream is;
	     ZipInputStream zis;
	     try 
	     {
	         String filename;
	         is = new FileInputStream(path + zipname);
	         zis = new ZipInputStream(new BufferedInputStream(is));          
	         ZipEntry ze;
	         byte[] buffer = new byte[4096];
	         int count;

	         while ((ze = zis.getNextEntry()) != null) 
	         {
	             // zapis do souboru
	             filename = ze.getName();

	             // Need to create directories if not exists, or
	             // it will generate an Exception...
	             if (ze.isDirectory()) {
	                File fmd = new File(path + filename);
	                fmd.mkdirs();
	                continue;
	             }

	             FileOutputStream fout = new FileOutputStream(path + filename);
	             int totalBytes = 0;

	             // cteni zipu a zapis
	             while ((count = zis.read(buffer)) != -1) 
	             {
	                 fout.write(buffer, 0, count); totalBytes += count;             
	             }

	             fout.close();               
	             zis.closeEntry();
	             Log.d(TAG, "unzip "+zipname +" "+filename+" ok: "+totalBytes+" bytes");
	         }

	         zis.close();
	     } 
	     catch(IOException e)
	     {
	         e.printStackTrace();
	         return false;
	     }

	    return true;
	}	
	
	
	public static byte[] zipBuffer( byte[] bytes, String entryName )
	{
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
		ZipOutputStream zipOutput = new ZipOutputStream(byteOutput);
		
		try {
			zipOutput.putNextEntry(new ZipEntry(entryName));
			
			zipOutput.write(bytes);
			zipOutput.flush();
			zipOutput.closeEntry();
			zipOutput.close();
			byteOutput.close();
			return byteOutput.toByteArray();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static byte[] unzipBuffer( byte[] zipBytes, String entryName )
	{
		ByteArrayInputStream byteInput = new ByteArrayInputStream(zipBytes);
		ZipInputStream zipInput = new ZipInputStream(byteInput);

		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

		try {
			
			if( zipInput.getNextEntry() != null ){
				byte[] bytes = new byte[1024];

				int readed = zipInput.read(bytes);
				while( -1 != readed ){
					byteOutput.write(bytes, 0, readed);
					readed = zipInput.read(bytes);
				}
				byteOutput.flush();
				byteOutput.close();
				zipInput.close();
				byteInput.close();
				
				return byteOutput.toByteArray();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	static public boolean loadFileFromUrl( String fileUrl, String filePath ) {
		String url = fileUrl;
		URL m;
		InputStream i = null; FileOutputStream fout = null;
		try {
			Log.d(TAG, "loadFacebookIcon "+url);
			m = new URL(url);
			URLConnection conn = m.openConnection();
			conn.connect();
			//int fileSize = conn.getContentLength();//根据响应获取文件大小
			i = (InputStream) m.getContent();
			fout = new FileOutputStream(filePath);
			byte[] b = new byte[4096]; 
			int totalLen = 0;
			int len = i.read(b);
			while(len>0) {
				fout.write(b, 0, len); totalLen += len;
				len = i.read(b);
			}
			i.close();
			fout.close();
			Log.d(TAG, "load "+fileUrl+" ok: "+totalLen+" bytes.");
			if(totalLen==0) return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Delete a folder
	 * @param file
	 */
    public static void delete(File file) {  
        if (file.isFile()) {  
           file.delete();  
           return;  
        }  
  
        if(file.isDirectory()){  
            File[] childFiles = file.listFiles();  
           if (childFiles == null || childFiles.length == 0) {  
                file.delete();  
                return;  
            }  
    
           for (int i = 0; i < childFiles.length; i++) {  
              delete(childFiles[i]);  
            }  
           file.delete();  
        }  
    }
    
    public static void cleanFolder(File file) {
  
        if(file.isDirectory()){  
            File[] childFiles = file.listFiles();  
           if (childFiles == null || childFiles.length == 0) {
                return;  
            }  
    
           for (int i = 0; i < childFiles.length; i++) {  
        	   delete(childFiles[i]);
            } 
        }  
    }
}
