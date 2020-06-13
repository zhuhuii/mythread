package org.io.file;

import org.junit.Test;

public class GetPath {

    @Test
    public void getPath() {
        /**
         * 获取路径的几种方法
         */
        System.out.println(this.getClass().getResource("").getPath());
        System.out.println(this.getClass().getResource("/").getPath());

        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
        // System.out.println(this.getClass().getClassLoader().getResource("/").getPath()); 错误的

        // 返回 InputStream
        System.out.println(this.getClass().getResourceAsStream("../abc.txt"));
        System.out.println(this.getClass().getClassLoader().getResourceAsStream("org/io/abc.txt"));
    }
}
