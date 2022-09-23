package com.example.productivity.main.domain

import com.example.productivity.main.model.StatusEnum
import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDateTime

@Canonical
@Document(collection = "task")

class Task {

    @Id
    String _id;

    String title;
    String description;
    StatusEnum status;
    LocalDateTime createdAt;
    LocalDateTime abandonedAt;
    String ip;
    String user_id;

    public updateStatus(StatusEnum novoStatus) {
        if(this.status == novoStatus) {
            return
        }

        if (novoStatus == StatusEnum.abandoned) {
            this.abandonedAt = LocalDateTime.now();
        }

        if (this.status == StatusEnum.abandoned) {
            this.abandonedAt = null;
        }

        this.status = novoStatus;
    }
}
