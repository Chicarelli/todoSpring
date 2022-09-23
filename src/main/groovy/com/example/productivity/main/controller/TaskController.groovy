package com.example.productivity.main.controller

import com.example.productivity.main.domain.Task
import com.example.productivity.main.model.AddTaskDTO
import com.example.productivity.main.model.EditTaskDTO
import com.example.productivity.main.model.StatusEnum
import com.example.productivity.main.repository.TaskRepository
import com.example.productivity.main.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import java.time.LocalDateTime

@RestController
@RequestMapping("/task")
class TaskController {

    private final TaskRepository taskRepository
    private final TaskService taskService;

    @Autowired
    TaskController(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }


    @PostMapping()
    public Task addTask(@RequestBody AddTaskDTO task, HttpServletRequest request) {
        def createdTask = this.taskRepository.save(
                new Task(
                        title: task.title,
                        description: task.description,
                        ip: request.getRemoteAddr(),
                        createdAt: LocalDateTime.now(),
                        status: StatusEnum.pending,
                )
        )

        return createdTask;
    }

    @GetMapping()
    List<Task> getTasks(HttpServletRequest request) {
        return this.taskRepository.findTaskByIp(request.getRemoteAddr());
    }

    @PutMapping("/{id}")
    public Task editTask(@PathVariable String id, @RequestBody EditTaskDTO task) {
        return this.taskService.editTask(id, task)
    }


}
