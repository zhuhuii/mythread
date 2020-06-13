package org.io.bytestream;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.io.*;


/**
 * 无参的 read()方法返回的int类型，是表示数据下一个字节的字节码，如果已经到达流的最后面了，那就返回-1.
 * <p>
 * read(byte b[], int off, int len)方法它的返回值的大小取决于两方面。
 * 1、假如当流数据的字节数小于我们给定的buffer[]数组长度的话，那么这个返回值，就应该是流数据的字节长度。
 * 2、当流数据的总字节长度大于我们的buffer[]数组长度的话，那么这个返回值就是我们的数字长度了。
 */
public class FIleInOutputStream {
    private File file = new File("D:/a.txt");

    @Test
    public void read() {
        FileInputStream fileInputStream = null;
        String result = "";
        try {
            // 1、根据path路径实例化一个输入流的对象
            fileInputStream = new FileInputStream(file);

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
        System.out.println(result);
    }

    @Test
    public void write() {
        String content = "\n-zhangsan-";
        FileOutputStream fos = null;
        try {
            // 1、根据文件路径创建输出流
            // fos = new FileOutputStream(file);   // 替换内容
            fos = new FileOutputStream(file, true);     // 追加内容

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

        this.read();
    }

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
        //copy2(originalPath, newPath);
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - end1 + "\n");

        newPath = "D:\\Java3.zip";
        copy3(originalPath, newPath);
        long end3 = System.currentTimeMillis();
        System.out.println(end3 - end2 + "\n");
    }

    /**
     * 一次性复制所有，可能内存溢出
     */
    public void copy1(String originalPath, String newPath) {
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

    /**
     * 一次性读一个字节，大文件非常慢
     * 1kb = 1024b
     * 1byte = 8bit
     */
    public void copy2(String originalPath, String newPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(originalPath);
            fos = new FileOutputStream(newPath);

            int b = 0;
            int count = 0;
            while ((b = fis.read()) != -1) {
                fos.write(b);
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

    /**
     * 案例:拷贝视频
     * 1.每次只读一个字节，又觉得太慢
     * 2.一次性读一个文件的所有数据，又怕内存装不下，内存只有2G,视频3G,这样就会内存溢出
     * 3.最终解决方法：折中，定义每次读 8KB ，【这种方式开发中建议经常使用】
     */
    public void copy3(String originalPath, String newPath) {
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
