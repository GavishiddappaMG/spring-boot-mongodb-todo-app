package com.gavi.todo.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gavi.todo.models.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {

}