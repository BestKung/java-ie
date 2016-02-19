/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.best.thread;

/**
 *
 * @author BestKung
 */
public class ThreadBasic implements Runnable {

    @Override
    public void run() {
        for(int i = 1 ; i < 8 ; i++){
        System.out.println("thread :" + Thread.currentThread().getName() + " = "+i);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadBasic(),"t1");
        Thread t2 = new Thread(new ThreadBasic(),"t2");
        Thread t3 = new Thread(new ThreadBasic(),"t2");
        Thread t4 = new Thread(new ThreadBasic(),"t2");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
