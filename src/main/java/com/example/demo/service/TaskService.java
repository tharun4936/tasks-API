package com.example.demo.service;

import java.util.List;


import com.example.demo.model.Task;

public interface TaskService {
	Task saveTask(Task task); 							//create task
	List<Task> getAllTasks(); 							//get all the tasks
	Task getTaskById(long id);							//get tasks by id
	List<Task> getTasksByStatus(String status);			//get tasks by status
	List<Task> getTasksByPriority(String priority);		//get tasks by priority
	Task updateTask(Task task, long id);				//update task
	List<Task> getTodaysTasks();						//get todays task
//	TaskId, Task name, Due date, start date, priority, status
	
	
}
