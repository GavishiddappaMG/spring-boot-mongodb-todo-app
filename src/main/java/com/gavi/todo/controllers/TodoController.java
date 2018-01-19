package com.gavi.todo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gavi.todo.dto.TodoDto;
import com.gavi.todo.models.Todo;
import com.gavi.todo.repositories.TodoRepository;
import com.gavi.todo.service.TodoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/todo")
@CrossOrigin("*")
@Api(value="todo_app")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;
    
    @Autowired
    TodoService service;

    
    @ApiOperation(value = "Get Todo List", notes = "This api is used to get all todos", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
        @ApiResponse(code = 401, message = "UnAuthorized") })
    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }

    @ApiOperation(value = "Post New Todo", notes = "This api is used to post new todo", httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
        @ApiResponse(code = 401, message = "UnAuthorized") })
    @PostMapping("/todos")
    public String createTodo(@Valid @RequestBody TodoDto todo) {
        return service.addToDo(todo);
    }

    @ApiOperation(value = "Get Todo by Id", notes = "This api is used to get Todo by Id", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
        @ApiResponse(code = 401, message = "UnAuthorized") })
    @GetMapping(value="/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") String id) {
        return service.getTodobyId(id);
    }

    @ApiOperation(value = "Update Todo", notes = "This api is used to update todo", httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
        @ApiResponse(code = 401, message = "UnAuthorized") })
    @PutMapping(value="/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id,
                                           @Valid @RequestBody Todo todo) {
        return service.update(todo, id);
    }

    @ApiOperation(value = "Delete Todo", notes = "This api is used to delete Todo", httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server error"),
        @ApiResponse(code = 401, message = "UnAuthorized") })
    @DeleteMapping(value="/todos/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable("id") String id) {
        return service.deleteTodoById(id);
    }
}