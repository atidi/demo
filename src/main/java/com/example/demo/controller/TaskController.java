package com.example.demo.controller;

import com.example.demo.Util.Util;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.Arrays;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping(value = {"/","/home"})
    public String listTasks(Model model, Principal principal)
    {
        User user = userService.findByEmail(principal.getName());
        if(Util.isAdmin(user.getRoles())){
            logger.info("User logged in Admin");
            model.addAttribute("isAdmin",true);
            model.addAttribute("tasks",taskService.findAll());
        }else {
            model.addAttribute("isAdmin",false);
            model.addAttribute("tasks",user.getTasks());
            logger.info("User logged in User");
        }

        return "tasks";
    }



    @RequestMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") Long id,Model model)
    {
        Task task = taskService.getById(id);
        if(task == null){
            throw new EntityNotFoundException("id =" + id);
        }
        model.addAttribute("task",task);
        model.addAttribute("statuses", Arrays.asList(Task.Status.values()));
        model.addAttribute("users",userService.findAll());
        return "edit";
    }

    @RequestMapping("/admin/update/")
    public String update(Task task)
    {
        taskService.addOrUpdate(task);
        return "redirect:/";
    }

    @RequestMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        taskService.remove(id);
        return "redirect:/";
    }

    @RequestMapping("/admin/add/")
    public String add(Model model)
    {
        model.addAttribute("task",new Task());
        model.addAttribute("users",userService.findAll());
        model.addAttribute("statuses", Arrays.asList(Task.Status.values()));
        return "edit";
    }
}
