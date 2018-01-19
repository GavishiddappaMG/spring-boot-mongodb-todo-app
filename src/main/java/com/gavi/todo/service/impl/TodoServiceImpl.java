package com.gavi.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gavi.todo.dto.TodoDto;
import com.gavi.todo.models.Todo;
import com.gavi.todo.repositories.TodoRepository;
import com.gavi.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoRepository todoRepository;
	
	

	@Override
	public String addToDo(TodoDto dto) {
		Todo todo = new Todo();
		todo.setTitle(dto.getTitle());
		todo.setDescription(dto.getDescription());
		todoRepository.save(todo);
		return "Success";
	}

	@Override
	public ResponseEntity<Todo> update(Todo dto, String id) {
		Todo todo = todoRepository.findOne(id);
		if(todo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		todo.setTitle(todo.getTitle());
		todo.setDescription(dto.getDescription());
        todo.setCompleted(todo.getCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
	}

	@Override
	public List<Todo> getAllTodos() {
		// Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		List<Todo> todos = todoRepository.findAll(sortByCreatedAtDesc);
		return todos;
	}

	@Override
	public ResponseEntity<Todo> getTodobyId(String id) {
		Todo todo = todoRepository.findOne(id);
		if(todo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
        }
	}

	@Override
	public ResponseEntity<Todo> deleteTodoById(String id) {
		Todo todo = todoRepository.findOne(id);
		if(todo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		todoRepository.delete(todo);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
