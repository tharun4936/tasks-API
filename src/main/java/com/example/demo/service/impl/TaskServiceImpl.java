package com.example.demo.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	
	private TaskRepository taskRepository;
	
	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}
	
	@Override
	public Task getTaskById(long id) {
		
		Optional<Task> task = taskRepository.findById(id);
		if(task.isPresent()) return task.get();
		else throw new ResourceNotFoundException("Task", "id", id);
	}

	@Override
	public List<Task> getTasksByStatus(String status) {
		return taskRepository.findAllByStatus(status);
	}

	@Override
	public List<Task> getTasksByPriority(String priority) {
		return taskRepository.findAllByPriority(priority);	
	}

	@Override
	public Task updateTask(Task task, long id) {
		Task existingTask = taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task", "id", id) );
		existingTask.setTaskName(task.getTaskName());
		existingTask.setDueDate(task.getDueDate());
		existingTask.setStartDate(task.getStartDate());
		existingTask.setPriority(task.getPriority());
		existingTask.setStatus(task.getStatus());
		taskRepository.save(existingTask);
		return existingTask;
	}

	@Override
	public List<Task> getTodaysTasks() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate today = LocalDate.now();
		List<Task> todaysTasks = new ArrayList<Task>();
		for(Task task: taskRepository.findAll()) {
			LocalDate startDate = LocalDate.parse(task.getStartDate(),formatter);
			LocalDate dueDate = LocalDate.parse(task.getDueDate(),formatter);
			if(today.isAfter(startDate) && today.isBefore(dueDate) && task.getStatus().equals("open")) {
				todaysTasks.add(task);
			}
		}
		return todaysTasks;
		
	}

	
	

	
	 
//	TaskId, Task name, Due date, start date, priority, status
	
	
	

}
