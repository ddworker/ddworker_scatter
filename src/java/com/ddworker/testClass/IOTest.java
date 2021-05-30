package com.ddworker.testClass;


import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOTest {
	
	private final String CHARSET = "UTF-8";
	private final String  IOt1 ="D:\\IOt1.txt";
	private final int ARRAY_LENGTH = 1024;
	
	/**X
	 * 
	 * 常用IO类测试
	 * @throws IOException 
	 * 
	 * 
	 */
	@Test
	public void IOTestFunc() throws IOException {
//		非流式文件类
//		FIle,作用,新增删除文件,查看文件/目录部分属性,
		File  file = new File("D:\\","IOtest.txt");
		//file 只是创建了对象,但指定文件或者目录比一定存在
		if(!file.exists()) {
			file.createNewFile(); //创建文件
		}
		System.out.println(file.length()); //内容字节长度,中文3字节
		
		byte[] b = new byte[ARRAY_LENGTH];
//		byte[] OddEven = new byte[] {1,1,1,1,1,1,1};
		int c;
		
//		文件字节流:后缀为Steam 的皆为字节流,字节流输出方法不能append
		
		//InputStream.read(byte[].off,len) off,从byte数组开始填充数据,len,长度多少,多久结束 off + len <= byte.length,没有的位置null填充
		//InputStream.read() 只能读取内容的长度
		
		try (
				InputStream s = new FileInputStream(file);
				BufferedInputStream bin=new  BufferedInputStream(s); 
//				OutputStream op = new FileOutputStream(file);  
				OutputStream op = new FileOutputStream(IOt1); //将会清空文件内容,文件已存在会覆盖文件
				BufferedOutputStream  bot=new BufferedOutputStream(op); //字节流输出方法不能append
				) {
			
//			while((c =s.read()) != -1){
			while((c =s.read(b,0,ARRAY_LENGTH)) != -1){
				System.out.println(c);
				op.write(c); //写入文件,由于b是定长,所以会有多余的null元素,可优化
			}
//			int c;
//			while((c = bin.read())!= -1){
//				bot.write(c);
//			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
		//System.InputStream 系统输入流
//		System.in
		//System.OutputStream 系统输出流
//		System.out
		
		//文字字符流 wirte,reader
//		char ch[] = new char[ARRAY_LENGTH];
//		int bi;
//		try (
//				FileReader fr = new FileReader(file);
//				InputStream is = new FileInputStream(file);
//				InputStreamReader sin = new InputStreamReader(is,CHARSET); //转编码
//				BufferedReader bin = new BufferedReader(sin);
//				
//				
//				FileWriter fw = new FileWriter(IOt1); //字符
//				OutputStream op = new FileOutputStream(IOt1); // 将会清空文件内容,文件已存在会覆盖文件
//				OutputStreamWriter osw = new OutputStreamWriter(op, CHARSET); //(转编码)如果不是在try ()中执行
//				BufferedWriter bw = new BufferedWriter(osw); //如果不是在try ()中执行
//				
//				
//				//则必须 osw.flush()/close(),不然最后的数据可能写进去
//				) {
//			
//			while ((bi =bin.read()) != -1) {
////			while ((bin.read(ch,0,1024)) != -1) {
////				System.out.println(String.valueOf(ch));
////				bw.write(ch);
//				fw.write(bi);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
}
