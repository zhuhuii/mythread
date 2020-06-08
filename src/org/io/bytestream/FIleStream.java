package org.io.bytestream;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FIleStream {

    private final String ABCTXT = this.getClass().getResource("../abc.txt").getPath();

    @Test
    public void getPath() {
        /**
         * 获取路径的几种方法
         */
        System.out.println(this.getClass().getResource("").getPath());
        System.out.println(this.getClass().getResource("/").getPath());

        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
        // System.out.println(this.getClass().getClassLoader().getResource("/").getPath()); 错误的

        System.out.println(this.getClass().getResourceAsStream("../abc.txt"));
        System.out.println(this.getClass().getClassLoader().getResourceAsStream("org/io/abc.txt"));
    }

    @Test
    public void readTest() {
        String path = this.getClass().getResource("/org/io/abc.txt").getPath();
        String result = this.fileInputStream(path);
        System.out.println(result);
    }

    @Test
    public void writeTest() {
        String path = this.getClass().getResource("/org/io/abc.txt").getPath();
        String content = "my name is zhangsan";
        this.fileOutputStream(path, content);
    }

    @Test
    public void copyFileTest() {
        String originalPath = ABCTXT;
        String newPath = "D:/a.txt";

        //this.copyFile(originalPath, newPath);
        this.copyFileUp(originalPath, newPath);
    }


    private String fileInputStream(String path) {
        FileInputStream fileInputStream = null;
        String result = "";
        try {
            // 1、根据path路径实例化一个输入流的对象
            fileInputStream = new FileInputStream(path);

            // 2、返回这个输入流中可以被读的剩下的bytes字节的估计值；
            int size = fileInputStream.available();
            //3. 根据输入流中的字节数创建byte数组；
            byte[] bytes = new byte[size];

            //4.把数据读取到数组中；
            fileInputStream.read(bytes);

            //5.根据获取到的Byte数组新建一个字符串，然后输出；
            result = new String(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void fileOutputStream(String path, String content) {
        FileOutputStream fos = null;
        try {
            // 1、根据文件路径创建输出流
            fos = new FileOutputStream(path);

            // 2、把string转换为byte数组；
            byte[] bytes = content.getBytes();

            // 3、把byte数组输出；
            fos.write(bytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyFile(String originalPath, String newPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            // 根据path路径实例化一个输入流的对象
            fis = new FileInputStream(originalPath);
            //2. 返回这个输入流中可以被读的剩下的bytes字节的估计值；
            //3. 根据输入流中的字节数创建byte数组；
            byte[] bytes = new byte[fis.available()];
            //4.把数据读取到数组中；
            fis.read(bytes);

            //5、根据文件路径创建输出流
            fos = new FileOutputStream(newPath);
            //5、把byte数组输出；
            fos.write(bytes);
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

    private void copyFileUp(String originalPath, String newPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(originalPath);
            fos = new FileOutputStream(newPath);

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fis.read(bytes)) > 0) {
                fos.write(bytes, 0, len);
            }
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
