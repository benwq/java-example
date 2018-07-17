package com.wqb.classload;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author benwq
 * @Description:
 * @Date: 16:10 2018/6/8
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myLoader.loadClass("com.wqb.classload.ClassLoaderTest").newInstance();
        ClassLoader.getSystemClassLoader();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.wqb.classload.ClassLoaderTest);
    }
}
