package com.majia.designpattern.guardedsuspension;

import java.util.LinkedList;

public class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    // 超时时间30秒
    private final static Long TIMEOUT = 3000L;

    public Request getRequest() {
       /* synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();
        }*/

        // 30秒守护线程的条件还没有到 就抛出超时异常
        synchronized (queue) {
            long start_time = System.currentTimeMillis(); // 开始时间

            while (queue.size() <= 0) {
                long now = System.currentTimeMillis(); // 当前时间
                long reset = TIMEOUT - (now - start_time);

                if (reset <=0){
                    throw new LivenessException("TIME OUT");
                }
                try {
                    queue.wait(reset);
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
