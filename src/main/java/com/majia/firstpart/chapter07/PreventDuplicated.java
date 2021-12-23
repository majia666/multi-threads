package com.majia.firstpart.chapter07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PreventDuplicated {
    private final static String LOCK_PATH = "/home/majia/locks/";

    private final static String LOCK_FILE = ".lock";

    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException {
        // ② 检查是否存在 .lock 文件
        checkRunning();
        // ① 注入hook线程，在线程退出时删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("The program received kill SIGNAL.");
            getLocFile().toFile().delete();
        }));
        // ③ 简单模拟当前程序正在运行
        for (;;){
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("program is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkRunning() throws IOException {
        Path path = getLocFile();
        if (path.toFile().exists()){
            throw new RuntimeException("The program already running.");
        }
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path,PosixFilePermissions.asFileAttribute(perms));
    }

    private static Path getLocFile(){
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }
}
