package org.io.bytestream;

import org.junit.Test;

import java.io.*;

/**
 * 内置缓冲区流
 */
public class BufferedInOutputStream {

    @Test
    public void copyTest() {
        // 测试拷贝大文件
        long start = System.currentTimeMillis();
        String originalPath = "D:\\软件包\\Java\\eclipse-jee-neon-3-win32-x86_64.zip";

        String newPath = "D:\\Java1.zip";
        copy1(originalPath, newPath);
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start + "\n");

        newPath = "D:\\Java2.zip";
        copy2(originalPath, newPath);
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - end1);

    }

    /**
     * BufferedInputStream和BufferedOutputStream
     * >这两个流是内置了缓冲区流,也就是说内部有一个字节数组
     * >这个两个类没有前面我们写的好用，因为它内部每次读8kb字节，如果想读80kb,没办法
     */
    public void copy1(String originalPath, String newPath) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(originalPath));

            bos = new BufferedOutputStream(new FileOutputStream(newPath));

            int b = 0;
            int count = 0;
            while ((b = bis.read()) != -1) {
                bos.write(b);
                count++;
            }
            System.out.println("一共执行了：" + count + "次");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关流,只需要关缓冲流，文件流不用关
            try {
                bis.close();    //【内部会关文件流输入流】
                bos.close();    //【内部会关文件流输出流】
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copy2(String originalPath, String newPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(originalPath);
            fos = new FileOutputStream(newPath);

            // 定义个8kb字节数组，也叫缓冲区流
            byte[] bytes = new byte[1024 * 8];
            int len = 0;
            int count = 0;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
                count++;
            }
            System.out.println("一共执行了：" + count + "次");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
