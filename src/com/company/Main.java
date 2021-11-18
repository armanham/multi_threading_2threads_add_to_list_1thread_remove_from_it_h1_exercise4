package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        List<String> stringList = new LinkedList<>();

        Runnable runnableAdd = () -> {
            while (stringList.size() <= 100) {
                String element = UUID.randomUUID().toString();
                System.out.println("Adding: " + Thread.currentThread().getName() + ": " + element);
                stringList.add(element);
            }
        };

        Runnable runnableDelete = new Runnable() {
            @Override
            public void run() {
                while (stringList.size() > 0) {
                    System.out.println("Removing: " + Thread.currentThread().getName() + ": " + stringList.remove(stringList.size() - 1));
                    // stringList.remove(stringList.size() - 1);
                }
            }
        };

        List<Thread> threadList = List.of(
                new Thread(runnableAdd, "Worker 1"),
                new Thread(runnableAdd, "Worker 2"),
                new Thread(runnableDelete, "Worker 3"));

        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
