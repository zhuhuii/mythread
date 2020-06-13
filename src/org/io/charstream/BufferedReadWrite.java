package org.io.charstream;

import org.junit.Test;

import java.io.*;

/**
 * read()方法读取字符时会一次读取若干字符到缓冲区,然后逐个返回给程序, 降低读取文件的次数, 提高效率
 * write()方法写出字符时会先写到缓冲区缓冲区写满时才会写到文件, 降低写文件的次数, 提高效率
 * 查看源码:字符缓冲区的大小是 8192(B)
 */
public class BufferedReadWrite {
    @Test
    public void copy() throws IOException {
        /**
         * 带缓冲的字符流 BufferedReader/BufferedWriter
         * 1.这两个类，内部都一个缓冲区，字符数组
         * 2.br.read()方法，内部会读 8192（8*1024）个字符
         * 3.bw.write(),内部会写 8192（8*1024）个字符
         *
         * 回顾：带缓冲的字节流,BufferedInputStream/BufferedOutputStream
         * 1.这两个类，内部都有一个缓冲区，字节数组
         */

        //需求：拷贝文件,a.txt -> b.txt
        //1.创建Reader
        FileReader fr = new FileReader("D:/a.txt");
        BufferedReader br = new BufferedReader(fr);

        //2.创建Writer
        FileWriter fw = new FileWriter("D:/b.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        //3.读写
        int c = 0;
        while ((c = br.read()) != -1) {
            bw.write(c);
        }

        //4.关流
        br.close();//内部关闭FileReader
        bw.close();//内部关闭FileWriter
    }

    /**
     * readLine()，每次读取一行数据
     */
    @Test
    public void readLine() throws IOException {
        //1.创建Reader
        FileReader fr = new FileReader("D:/a.txt");
        BufferedReader br = new BufferedReader(fr);

        //2.读一行数据
        /*System.out.println(br.readLine());
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        System.out.println(br.readLine());*/
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        //3.关流
        br.close();
    }

    /**
     * newLine()，换行
     */
    @Test
    public void newLine() throws IOException {
        //1.创建Reader
        FileWriter fw = new FileWriter("D:/a.txt");
        BufferedWriter br = new BufferedWriter(fw);

        //2.写一行数据
        br.write("abc123");
        br.newLine();
        br.write("abc987");

        //3.关流
        br.close();
    }
}
