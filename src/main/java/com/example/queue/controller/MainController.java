package com.example.queue.controller;

import com.example.queue.model.Task;
import com.example.queue.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class MainController {
    final private TaskService taskService;

    @GetMapping("/main")
    public String sandboxPrintMain() {
        log.info("we are in main method of Main Controller, hello world");
        return "hello world!";
    }

    @PostMapping("/task")
    public Task post (@RequestBody Task task){
        taskService.saveToQueue(task);
        return task;
    }

    @PostMapping("/task/list")
    public List<Task> post (@RequestBody List<Task> list){
        taskService.saveToQueue(list);
        return list;
    }

}
