package com.studyMultipleThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * From: https://blog.csdn.net/a369414641/article/details/48342253
 */
public class StudyExecutorThreadPool {

    public static void main(String[] args) throws InterruptedException {
        StudyExecutorThreadPool test = new StudyExecutorThreadPool();
//        test.method1();
        test.method2();
//        test.testMultipleTask();
    }

    public void method2(){
        ExecutorService m = //Executors.newCachedThreadPool();
                            Executors.newFixedThreadPool(3);
        for(int i=1;i<=10;i++){
            final int count=i;
            m.submit(() -> {
                Double d = Math.random() * 5000d;
                long random = d.longValue();
                try {
                    Thread.sleep(random);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程："+Thread.currentThread()+"延迟:"+random+"ms, 负责了第"+count+"次任务");
            });
        }
        m.shutdown();
        System.out.printf("Main线程：%s ends at:%s%n", Thread.currentThread(), System.currentTimeMillis());
    }

    public void method1(){
        for(int i=1;i<=10;i++){
            MyRunnableImpl impl = new MyRunnableImpl(i);
            Thread md = new Thread(impl);
            md.start();

        }
        System.out.printf("Main线程：%s ends at:%s%n", Thread.currentThread(), System.currentTimeMillis());
    }

    class MyRunnableImpl implements Runnable{
        int count;
        MyRunnableImpl(int count){
            this.count=count;
        }
        @Override
        public void run() {
            try {
                Double d = Math.random()*1000d;
                Thread.sleep(d.longValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("线程：%s负责了第%d次任务start at:%s%n", Thread.currentThread(), count, System.currentTimeMillis());
        }
    }

}
