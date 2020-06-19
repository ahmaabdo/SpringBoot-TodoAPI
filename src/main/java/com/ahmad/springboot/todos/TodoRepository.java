package com.ahmad.springboot.todos;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<Todo,String > {

    //Mongo will relate how to find the thing you need to find by the camel case and pass the value to parameter
    // example: findByDescriptionAndTimestampAndId(String desc, String timeStamp, String id)
    Todo findByTitle(String title);

    Todo findByTitleAndIdNot(String title, String id);

}
