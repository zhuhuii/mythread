package org.io.charstream;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;

public class FileReadWrite {
    private File file = new File("D:/a.txt");

    /**
     * FileReader 用于读取字符
     */
    @Test
    public void read() throws IOException {
        //1.创建对象
        FileReader reader = new FileReader(file);

        //2.读取数据，reader.read() : 先读取到字节数据, 然后转为字符
        System.out.println((char) reader.read());
        System.out.println((char) reader.read());
        System.out.println((char) reader.read());
        System.out.println((char) reader.read());
        System.out.println((char) reader.read());
        System.out.println(reader.read());

        int c;
        while ((c = reader.read()) != -1) {
            System.out.println((char) c);
        }

        //3.关闭流
        reader.close();
    }

    @Test
    public void write() throws IOException {
        //1.创建writer对象
        FileWriter writer = new FileWriter(file, true);

        //2.写内容
        writer.write("你好!");
        writer.write("\r\n");
        writer.write("你好!");
        writer.write("\r\n");
        writer.write("你好!");
        writer.write("\r\n");
        writer.write("你好!");

        //3.关闭
        writer.close();
        this.read();
    }

    @Test
    public void copy() throws IOException {
        //案例:字符流的拷贝
        //思路：用FileReader读字符,用FileWriter写字符

        //1.创建 “读取流” 对象
        FileReader reader = new FileReader("D:\\a.txt");

        //2.创建 "写入流" 对象
        FileWriter writer = new FileWriter("D:\\b.txt");

        //3.读取和写入【缓冲思想】
        char[] buf = new char[8 * 1024];
        int len = 0;
        while ((len = reader.read(buf)) != -1) {
            writer.write(buf, 0, len);
        }

        //4.关闭流
        reader.close();
        writer.close();
    }

    @Test
    public void test1() throws IOException {
        //读字节
        FileInputStream fis = new FileInputStream(file);
        //int 00000000 00000000 00000000 11111111
        System.out.println((byte) fis.read());
        System.out.println((byte) fis.read());
        System.out.println((byte) fis.read());
        System.out.println((byte) fis.read());
        System.out.println((byte) fis.read());
        System.out.println((byte) fis.read());
        System.out.println((byte) fis.read());
    }
}
