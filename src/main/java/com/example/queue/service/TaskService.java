package com.example.queue.service;


import com.example.queue.model.Task;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static java.lang.Thread.sleep;

@Service
@RequiredArgsConstructor
@Log4j2
public class TaskService {
    private final LinkedList<Task> listOfTask;
//    ThreadPoolTaskExecutor
    private final ExecutorService executor;
//    MyThread myThread = new MyThread();

    public void saveToQueue(Task task) {
        listOfTask.add(task);
        log.info("tasked saved to the Linked List: {}", task);
    }

    public LinkedList<Task> getListOfTask() {
        return listOfTask;
    }

//    private class MyThread extends Thread {
//        @Override
//        public void run() {
//            log.info("Start Thread, waiting for work: ");
//
//            while (true) {
//                if (list.size() > 0) {
//
//                    Task task = list.getFirst();
//                    list.removeFirst();
//                    log.info("task {} start.", task.getName());
//                    try {
//                        sleep(task.getMlsec());
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    log.info("task {} finish.", task.getName());
//                }
//            }
//        }
//    }

//        @PostConstruct
//        public void doit() {
//            log.info("********* Start Thread: ***********");
//            myThread.start();
//        }

    @PostConstruct
    public void doWork() {
        executor.execute(() -> {
            while (true) {
                if (listOfTask.size() > 0) {
                    Task task = listOfTask.getFirst();
                    listOfTask.removeFirst();
                    executor.execute(() -> {
                        executeTask(task);
                    });
                }
                try {
                    sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void executeTask(Task task) {
        log.info("***************** task {} start.", task.getName());
        try {
            sleep(task.getMlsec());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("***************** task {} finish.", task.getName());
    }


    public void saveToQueue(List<Task> list) {
        List<Task> tasks = new ArrayList<>();
        for (Task task: list) {
            tasks.add(task);
            log.info("get task: {}", task);
        }
        for (Task task:
             tasks) {
            listOfTask.add(task);
            log.info("task saved to the Linked List: {}", task);
        }
        log.info("saving finished");
    }
}
