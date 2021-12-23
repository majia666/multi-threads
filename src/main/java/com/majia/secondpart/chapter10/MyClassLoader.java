package com.majia.secondpart.chapter10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//  自定义类加载器必须是ClassLoader 的直接或者间接子类
public class MyClassLoader extends ClassLoader {
    // 定义默认的class存放路径
    private final static Path DEFAULT_CLASS_DIR = Paths.get("E:","classloader1");

    private final Path classDir;

    // 使用默认的class路径
    public MyClassLoader(){
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }

    // 允许传入指定路径的class路径
    public MyClassLoader(String classDir){
        super();
        this.classDir = Paths.get(classDir);
    }

    // 指定class路径的同时，指定父类加载器
    public MyClassLoader(String classDir,ClassLoader parent){
        super(parent);
        this.classDir = Paths.get(classDir);
    }
    // 重写父类的findClass方法，这是至关重要的步骤
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 读取class的二进制数据
        byte[] classBytes = this.readClassBytes(name);
        // 如果数据为 null，或者没有读取到任何信息，则抛出 ClassNotFoundException 异常
        if (null==classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("Can not load the class " + name);
        }
        // 调用defineClass 方法定义 class
        return this.defineClass(name,classBytes,0,classBytes.length);
    }

    // 将class文件写入到内存
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        // 将包名分隔符转换为文件路径分隔符
        String classPath = name.replace(".","/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if(!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class " + name + " not found.");
        }
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            Files.copy(classFullPath,baos);
            return baos.toByteArray();
        } catch (IOException e) {
//            e.printStackTrace();
            throw new ClassNotFoundException("load the class " + name + " occur error.",e);
        }

    }

}
