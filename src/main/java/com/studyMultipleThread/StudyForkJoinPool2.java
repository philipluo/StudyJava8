package com.studyMultipleThread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * From: https://blog.csdn.net/a369414641/article/details/48350795
 */
public class StudyForkJoinPool2 {

    public static void main(String[] args) {
        StudyForkJoinPool2 s = new StudyForkJoinPool2();
        long st = System.currentTimeMillis();
        s.testMultipleTask();
        long et = System.currentTimeMillis();
        System.out.println("Total cost: "+ (et - st) +"ms");
    }

    public void testMultipleTask() {
        List<Integer> terrs = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
        ForkJoinPool pool= new ForkJoinPool();//Or: new ForkJoinPool(Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, false);
        myTask task=new myTask(terrs);
        Integer rtn = pool.invoke(task);//Or: pool.submit(task);
        System.out.println("Return: "+ rtn);
    }

    class myTask extends RecursiveTask<Integer> {
        private static final long serialVersionUID = 1L;
        //定义一个分解任务的阈值——50,即一个任务最多承担50个工作量
        int THRESHOLD=3;

        List<Integer> allTerrs;

        myTask(List<Integer> myTerrs){
            this.allTerrs=myTerrs;
        }
        protected Integer compute() {
            int taskSize = allTerrs.size();
            if(taskSize<=THRESHOLD){
                try {
                    Double d = Math.random() * 2000d;
                    long random = d.longValue();
                    Thread.sleep(random);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"承担了:"+allTerrs);
                return taskSize;
            }else{
                List<Integer> leftTerrs = allTerrs.subList(0,THRESHOLD);
                List<Integer> rightTerrs = allTerrs.subList(THRESHOLD, allTerrs.size());
                myTask m1=new myTask(leftTerrs);
                m1.fork();
                myTask m2=new myTask(rightTerrs);
                return m2.compute() + m1.join();
            }
        }
    }
}
