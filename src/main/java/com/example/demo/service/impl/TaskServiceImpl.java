package com.example.demo.service.impl;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public void remove(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public void addOrUpdate(Task task) {
        taskRepository.save(task);
    }

}
