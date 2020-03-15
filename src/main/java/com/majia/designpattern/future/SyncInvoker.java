package com.majia.designpattern.future;

/**
 * Future -> 代表的是未来的一个凭据
 * FutureTask -> 将你的调用逻辑进行了隔离
 * FutureService -> 桥接 Future 和 FutureTask
 */
public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {
//        String result = get();
//        System.out.println(result);
        FutureService service = new FutureService();
        Future<String> future = service.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        },System.out::println);

        System.out.println("==========================");
        System.out.println("do other things");


//        Thread.sleep(1000);
//
//        System.out.println("0000000000000000000000");
//
//        System.out.println(future.get());

    }

    public static String get() throws InterruptedException {

        Thread.sleep(10000);
        return "FINISH";
    }
}
