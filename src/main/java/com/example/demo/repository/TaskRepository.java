package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	public List<Task> findAllByPriority(String priority);
	public List<Task> findAllByStatus(String status);
}
