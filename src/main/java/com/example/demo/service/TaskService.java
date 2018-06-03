package com.example.demo.service;

import com.example.demo.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task getById(Long id);

    void remove(Long id);

    void addOrUpdate(Task task);

}
