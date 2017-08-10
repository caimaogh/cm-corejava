/**
 * 
 */
package com.caimao.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>Title: FileChannelDemo.java</p>
 * <p>Description: FileChannel：从文件中读写数据。</p>
 * @author caimao
 *
 * @date 2017年8月10日 下午3:11:03
 * @version 1.0
 * 
 */
public class FileChannelDemo {


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		try {
			/*
			 * RandomAccessFile(File file, String mode)   创建从中读取和向其中写入（可选）的随机访问文件流，该文件由 File 参数指定。   
			 * RandomAccessFile(String name, String mode) 从中读取和向其中写入（可选）的随机访问文件流，该文件具有指定名称。 
			 * mode的取值有下面四种情况 
			 * 	1) "r"    以只读方式打开。调用结果对象的任何 write 方法都将导致抛出 IOException。
			 * 	2) "rw"   打开以便读取和写入。
			 * 	3) "rws"  打开以便读取和写入。相对于 "rw"，"rws" 还要求对“文件的内容”或“元数据”的每个更新都同步写入到基础存储设备。  
			 * 	4) "rwd"  打开以便读取和写入，相对于 "rw"，"rwd" 还要求对“文件的内容”的每个更新都同步写入到基础存储设备。
			 *  "rw"  "rws"  "rwd"之间的区别
			 *  当操作的文件是存储在本地的基础存储设备上时(如硬盘, NandFlash等)，"rws" 或 "rwd", "rw" 才有区别。
			 *  当模式是 "rws" 并且 操作的是基础存储设备上的文件；那么，每次“更改文件内容[如write()写入数据]” 或 “修改文件元数据(如文件的mtime)”时，都会将这些改变同步到基础存储设备上。
			 *  当模式是 "rwd" 并且 操作的是基础存储设备上的文件；那么，每次“更改文件内容[如write()写入数据]”时，都会将这些改变同步到基础存储设备上。
			 *  当模式是 "rw" 并且 操作的是基础存储设备上的文件；那么，关闭文件时，会将“文件内容的修改”同步到基础存储设备上。至于，“更改文件内容”时，是否会立即同步，取决于系统底层实现。
			 *  
			 *  使用Buffer读写数据一般遵循以下四个步骤：
			 *  1)写入数据到Buffe(当向buffer写入数据时，buffer会记录下写了多少数据)
			 *  2)调用flip()方法(一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到读模式。在读模式下，可以读取之前写入到buffer的所有数据)
			 *  3)从Buffer中读取数据
			 *  4)调用clear()方法或者compact()方法(一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入;clear()方法会清空整个缓冲区。compact()方法只会清除已经读过的数据)
			 */
			RandomAccessFile raFile = new RandomAccessFile("D:/nio-data.txt","rw");
			//FileChannel getChannel() 返回与此文件关联的唯一 FileChannel 对象。
			FileChannel inChannel = raFile.getChannel();
			//create buffer with capacity of 48 bytes
			ByteBuffer buf = ByteBuffer.allocate(48);
			int bytesRead = inChannel.read(buf);//首先读取数据到Buffer,read into buffer
			while(bytesRead != -1){
				System.out.println("Read " + bytesRead);
				//把buffer的当前位置更改为buffer缓冲区的第一个位置 
				//调用flip之后，读写指针指到缓存头部，并且设置了最多只能读出之前写入的数据长度(而不是整个缓存的容量大小)。
				buf.flip();//然后反转Buffer make buffer ready for read
				while(buf.hasRemaining()){
					System.out.println((char)buf.get());//read buffer 1 byte at a time
				}
				buf.clear();//make buffer ready for writing
				bytesRead = inChannel.read(buf);//接着再从Buffer中读取数据
			}
			//// 关闭此随机访问文件流并释放与该流关联的所有系统资源。
			raFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("error:e " + e);
		}
	}

}
