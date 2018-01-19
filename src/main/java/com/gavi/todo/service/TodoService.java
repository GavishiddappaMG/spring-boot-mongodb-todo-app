package com.gavi.todo.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gavi.todo.dto.TodoDto;
import com.gavi.todo.models.Todo;

public interface TodoService {

	List<Todo> getAllTodos();
	
	ResponseEntity<Todo> getTodobyId(String id);
	
	String addToDo(TodoDto dto);
	
	ResponseEntity<Todo> update(Todo todo, String id);
	
	ResponseEntity<Todo> deleteTodoById(String id);
}
