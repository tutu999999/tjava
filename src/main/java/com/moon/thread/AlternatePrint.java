package com.moon.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程交替输出 example
 */
public class AlternatePrint {
    private static Object syncObject = new Object();

    public static void main(String[] args)throws Exception {
        System.out.println("v1=====>");
        v1();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("");
        System.out.println("v2=====>");
        new AlternatePrint().v2();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("");
        System.out.println("v3=====>");
        v3();
    }

    public static void v1(){
        new Thread(()->{
            synchronized (syncObject){
                for(int i=1; i<=26; i++){
                    System.out.print("" + i);
                    try {
                        syncObject.wait();
                        syncObject.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        }).start();

        new Thread(()->{
            synchronized (syncObject){
                for(int i= 97; i<98+25; i++){
                    System.out.print( (char)(i));
                    try {

                        syncObject.notify();
                        syncObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 使用volate
     */
    static  boolean flag = true;
    public  void v2(){
        new Thread(()->{
            for(int i=1; i<=26; i++){
                while(!flag){
                }
                System.out.print("" + i);
                flag = false;
            }

        }).start();
        new Thread(()->{
            new Thread(()->{
                for(int i= 97; i<97+26; i++){
                    while(flag){
                    }
                    System.out.print( (char)(i));
                    flag = true;
                }

            }).start();
        }).start();
    }

    static AtomicInteger flag0 = new AtomicInteger(0);

    // 使用cas
    public static void v3(){
        new Thread(()->{
            for(int i=1; i<=26; i++){
                while(flag0.get() != 0){
                }
                System.out.print("" + i);
                flag0.set(1);
            }

        }).start();
        new Thread(()->{
            new Thread(()->{
                for(int i= 97; i<97+26; i++){
                    while(flag0.get() == 0){
                    }
                    System.out.print( (char)(i));
                    flag0.set(0);
                }

            }).start();
        }).start();
    }
}
