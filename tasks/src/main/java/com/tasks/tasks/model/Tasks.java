package com.tasks.tasks.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "tasks")
public class Tasks {

    TaskStatus codicao = TaskStatus.Fazer;
    TasksPriority priority;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String title;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return codicao;
    }

    public void setStatus(TaskStatus status) {
        this.codicao = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public TasksPriority getPriority() {
        return priority;
    }

    public void setPriority(TasksPriority priority) {
        this.priority = priority;
    }
}
