package com.example.queue.controller;

import com.example.queue.model.Task;
import com.example.queue.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.LinkedList;
import java.util.Random;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class MainControllerTest {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TaskService taskService;

    @Test
    void sandboxPrintMain() throws Exception {
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/main"));
        final MvcResult mvcResult = resultActions.andDo(MockMvcResultHandlers.print()).andReturn();
        final var s = mvcResult.getResponse().getContentAsString();
        assertEquals("hello world!", s);
    }

    @Test
    void post() throws Exception {
        LinkedList<Task> list = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            Task task = Task.builder().name("name "+i).mlsec(Math.abs(new Random().nextLong())).build();
            list.add(task);

            String body = MAPPER.writeValueAsString(task);

            final ResultActions resultActions = mockMvc
                    .perform(MockMvcRequestBuilders.post("/getTask")
                            .content(body)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                    );
        }

        log.info("1: "+list);
        log.info("2: "+taskService.getListOfTask());

        assertEquals(list, taskService.getListOfTask());
    }
}