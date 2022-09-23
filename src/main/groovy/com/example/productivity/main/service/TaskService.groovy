package com.example.productivity.main.service

import com.example.productivity.main.domain.Task
import com.example.productivity.main.model.EditTaskDTO
import com.example.productivity.main.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskService {

    private final TaskRepository taskRepository

    @Autowired
    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    Task editTask(String id, EditTaskDTO editedTask) {
        def taskToEdit = taskRepository.findOne(id);

        taskToEdit.title = editedTask.title;
        taskToEdit.description = editedTask.description;
        taskToEdit.updateStatus(editedTask.status);

        return this.taskRepository.save(taskToEdit);
    }
}
