package org.io.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Java File类
 * 1、常用的构造方法
 * 2、常用方法
 */
public class FIleTest {

    private final File ABCFILE = new File(this.getClass().getClassLoader().getResource("org/io/abc.txt").getPath());

    /**
     * File 常用的构造方法
     */
    @Test
    public void fileConstructor() {
        /**
         * 一个File对象代表硬盘中实际存在的一个文件或者目录。
         * File类构造方法不会给你检验这个文件或文件夹是否真实存在，因此无论该路径下是否存在文件或者目录，都不影响File对象的创建。
         */

        // 绝对路径
        File f1 = new File("D:/test.txt");

        // 相对路径
        File f2 = new File("test.txt");

        // 文件夹
        File f3 = new File("D:/dir");

        // 父路径 + 子路径
        File f4 = new File("D:/dir", "ABC.txt");

        // 父级File对象 + 子路径字符串
        File parent = new File("D:/dir");
        File f5 = new File(parent, "ABC.txt");
    }


    /**
     * File 常用方法
     */
    @Test
    public void fileMethod() throws IOException {
//        basicMethod();
//        createNewFile();
//        mkdir();
        delete();
    }

    /**
     * 文件或目录的基本信息的 获取/判断
     */
    private void basicMethod() {
        // 返回此File的绝对路径名字符串。
        System.out.println("文件绝对路径:" + ABCFILE.getAbsolutePath());

        // 将此File转换为路径名字符串。
        File f1 = new File("/a/b.txt");
        File f2 = new File("C:/aaa/bbb.txt");
        System.out.println("文件构造路径:" + f1.getPath());
        System.out.println("文件构造路径:" + f2.getPath());

        System.out.println("----------------------------------------------");

        // 返回由此File表示的文件或目录的名称。
        System.out.println("文件名称:" + ABCFILE.getName());
        // 返回由此File表示的文件的长度。
        System.out.println("文件长度:" + ABCFILE.length() + "字节");

        // 判断是否存在
        System.out.println("f1是否存在:" + f1.exists());
        System.out.println("f2是否存在:" + f2.exists());
        // 判断是文件还是目录
        System.out.println("abc是文件?:" + ABCFILE.isFile());
        System.out.println("abc是目录?:" + ABCFILE.isDirectory());
    }

    /**
     * 创建文件
     */
    private void createNewFile() throws IOException {
        File file1 = new File("D:/A.txt");
        file1.createNewFile();

        File file2 = new File("D:/", "B.txt");
        file2.createNewFile();

        File parent = new File("D:/");
        File file4 = new File(parent, "C.txt");
        file4.createNewFile();
    }

    /**
     * 创建目录
     */
    private void mkdir() {
        // 普通创建
        File dir1 = new File("dir");
        dir1.mkdir();

        // 递归创建
        File dir2 = new File("/home/dir/java");
        File dir3 = new File("D:/dir/java");
        dir2.mkdirs();
        dir3.mkdirs();
    }

    private void delete() throws IOException {
        // 文件的创建
        File f = new File("aaa.txt");
        System.out.println("aaa.txt 是否存在？ => " + f.exists()); // false
        System.out.println("aaa.txt 创建 => " + f.createNewFile()); // true
        System.out.println("aaa.txt 创建 => " + f.createNewFile()); // 以及创建过了所以再使用createNewFile返回false
        System.out.println("aaa.txt 是否存在？ => " + f.exists()); // true
        System.out.println("----------------------------------------------");

        // 目录的创建
        File f2 = new File("newDir");
        System.out.println("newDir 是否存在？ => " + f2.exists()); // false
        System.out.println("newDir 创建 => " + f2.mkdir());  // true
        System.out.println("newDir 是否存在？ => " + f2.exists()); // true
        System.out.println("----------------------------------------------");

        // 创建多级目录
        File f3 = new File("dirAAA\\dirBBB");
        System.out.println("创建单个目录：" + f3.mkdir());// false
        System.out.println("递归创建目录：" + f3.mkdirs());// true

        // 文件的删除
        System.out.println(f.delete());// true

        // 目录的删除
        System.out.println(f2.delete());// true

        // 目录必须为空才能删除。
        File f4 = new File("dirAAA");
        try {
            deleteFolder(f4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //需要注意的是当删除某一目录时，必须保证该目录下没有其他文件才能正确删除，否则将删除失败。
    public void deleteFolder(File folder) throws Exception {
        if (!folder.exists()) {
            throw new Exception("文件不存在");
        }
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //递归直到目录下没有文件
                    deleteFolder(file);
                } else {
                    //删除
                    file.delete();
                }
            }
        }
        //删除
        folder.delete();

    }


    /**
     * File 类的属性方法
     */
    @Test
    public void allMethod() {
        /**
         * 获取FILE属性方法：
         * String   getAbsolutePath()   // 获取绝对路径
         * long   getFreeSpace()       // 返回分区中未分配的字节数。
         * String   getName()         // 返回文件或文件夹的名称。
         * String   getParent()         // 返回父目录的路径名字符串；如果没有指定父目录，则返回 null。
         * File   getParentFile()      // 返回父目录File对象
         * String   getPath()         // 返回路径名字符串。
         * long   getTotalSpace()      // 返回此文件分区大小。
         * long   getUsableSpace()    //返回占用字节数。
         * int   hashCode()             //文件哈希码。
         * long   lastModified()       // 返回文件最后一次被修改的时间。
         * long   length()          // 获取长度,字节数。
         * boolean canRead()  //判断是否可读
         * boolean canWrite()  //判断是否可写
         * boolean isHidden()  //判断是否隐藏
         * boolean exists()    //判断文件或文件夹是否存在
         * String[] list()     //获取指定目录下的文件名称
         * File[] listFiles()  //获取指定目录下的文件以及文件夹对象
         */

        /**
         * 设置FILE属性方法
         * static File[]    listRoots()    // 列出可用的文件系统根。
         * boolean    renameTo(File dest)    // 重命名
         * boolean    setExecutable(boolean executable)    // 设置执行权限。
         * boolean    setExecutable(boolean executable, boolean ownerOnly)    // 设置其他所有用户的执行权限。
         * boolean    setLastModified(long time)       // 设置最后一次修改时间。
         * boolean    setReadable(boolean readable)    // 设置读权限。
         * boolean    setReadable(boolean readable, boolean ownerOnly)    // 设置其他所有用户的读权限。
         * boolean    setWritable(boolean writable)    // 设置写权限。
         * boolean    setWritable(boolean writable, boolean ownerOnly)    // 设置所有用户的写权限。
         */

        /**
         * FIlE 操作
         * 1、创建文件、文件夹
         * 2、删除文件、文件夹
         */
    }

    /**
     * File类常用方法
     */
    @Test
    public void example() {
        File file = new File(this.getClass().getResource("../abc.txt").getPath());
        //判断文件是否可读。
        System.out.println("canRead==" + file.canRead());
        //判断文件是否可写。
        System.out.println("canWrite==" + file.canWrite());
        //判断文件是否是一个隐藏文件。
        System.out.println("isHidden==" + file.isHidden());
        //返回绝对路径名字符串。
        System.out.println("绝对路径==" + file.getAbsolutePath());
        //返回文件或文件夹的名称。
        System.out.println("文件名称==" + file.getName());
        //得到文件的保存目录。
        System.out.println("文件的保存目录==" + file.getParent());
        //将此抽象路径名转换为一个路径名字符串。
        System.out.println("getPath==" + file.getPath());
        //返回此抽象路径名表示的文件最后一次被修改的时间【毫秒数】。
        long time = file.lastModified();
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd E HH:mm");
        String newtime = sdf.format(date);
        System.out.println("最后一次被修改的时间==" + newtime);
        //返回文件的长度。
        System.out.println("length==" + file.length());
        //判断该对像是否为一个文件夹。【文件夹必须存在】
        boolean isd = file.isDirectory();
        System.out.println("isdir==" + isd);
    }
}
