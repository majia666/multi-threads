package com.majia.thirdpart.chapter14;

import java.net.Socket;
import java.sql.Connection;

// final 不允许被继承
public final class Singleton {

    /**
     *  1.饿汉式单例
     */
    // 实例变量
    /*private byte[] data = new byte[1024];

    // 在定义实例对象的时候直接初始化
    private static Singleton instance = new Singleton();

    // 私有构造方法，不允许外部new
    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }*/

    /**
     * 2.懒汉式单例
     */
    // 实例变量
    /*private byte[] data = new byte[1024];

    // 定义实例，但是不直接初始化
    private static Singleton instance = null;

    private Singleton(){};

    public static Singleton getInstance(){
        if(null == instance){
            instance = new Singleton();
        }
        return instance;
    }*/


    /**
     * 3.懒汉式单例 + 同步方法
     */
   /* // 实例变量
    private byte[] data = new byte[1024];

    // 定义实例，但是不直接初始化
    private static Singleton instance = null;

    private Singleton(){};

    // 向getInstance 方法加入同步控制，每次只能有一个线程可以进入
    public static synchronized Singleton getInstance(){
        if(null == instance){
            instance = new Singleton();
        }
        return instance;
    } */

    /**
     * 4.Double-Check
     */
    // 实例变量
   /* private byte[] data = new byte[1024];

    // 定义实例，但是不直接初始化
    private static Singleton instance = null;

    Connection conn;

    Socket socket;

    private Singleton(){};

    public static synchronized Singleton getInstance(){
       // 当instance为null时，进入同步代码块，同时该判断避免了每次都需要进入同步代码块，可以提高效率
        if (null == instance) {
            // 只有一个线程能够获得Singleton.class 关联的monitor
            synchronized (Singleton.class){
                // 判断如果instance为null则创建
                if (null == instance) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
*/
    /**
     * 5.Holder 方式
     */
    // 实例变量
   /*private byte[] data = new byte[1024];

   private Singleton(){}

   // 在静态内部类中持有Singleton的实例,并且可被直接初始化
    private static class Holder{
       private static Singleton instance = new Singleton();
   }
    // 调用 getInstance 方法，事实上是获得Holder的instance静态属性
    public static Singleton getInstance(){
       return Holder.instance;
    }*/

    /**
     * 7.枚举  + holder
     */
    private byte[] data = new byte[1024];

    private Singleton(){}

    // 使用枚举holder
    private enum EnumHolder{
        INSTANCE;

        private Singleton instance;

        EnumHolder(){
            this.instance = new Singleton();
        }
        private  Singleton getSingleton(){
          return instance;
        }
    }
    public static Singleton getInstance(){
        return EnumHolder.INSTANCE.getSingleton();
    }
}
