package com.tasks.tasks.repository;

import com.tasks.tasks.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository()
public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
