package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	private TaskService taskService;

	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}
	
	@PostMapping("/create")  
	public ResponseEntity<Task> saveTask(@RequestBody Task task){
//		System.out.println();
		return new ResponseEntity<Task>(taskService.saveTask(task), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/findAll")
	public List<Task> getAllTasks(){
		return taskService.getAllTasks();
	}
	
	@GetMapping("/searchById/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("id") long TaskId){
		return new ResponseEntity<Task>(taskService.getTaskById(TaskId),HttpStatus.OK );
	}
	
	@GetMapping("/searchByStatus/{status}")
	public List<Task> getTasksByStatus(@PathVariable("status") String taskStatus){
		return taskService.getTasksByStatus(taskStatus);
	}
	
	@GetMapping("/searchByPriority/{priority}")
	public List<Task> getTasksByPriority(@PathVariable("priority") String taskStatus){
		return taskService.getTasksByPriority(taskStatus);
	}
	
	@GetMapping("/todaysTasks")
	public List<Task> getTodaysTasks(){
		return taskService.getTodaysTasks();
	}
	
	@PutMapping("/updateTask/{id}")
	public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable("id") long id){
		return new ResponseEntity<Task>(taskService.updateTask(task, id), HttpStatus.OK);
	}
}
