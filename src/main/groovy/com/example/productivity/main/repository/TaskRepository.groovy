package com.example.productivity.main.repository

import com.example.productivity.main.domain.Task
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

public interface TaskRepository extends MongoRepository<Task, String> {

    @Query("{name: ?0}")
    List<Task> findTaskByName(String name);

    @Query("{ip: ?0}")
    List<Task> findTaskByIp(String ip);

    @Query("{_id: ?0}")
    Task findOne(String id);


}
