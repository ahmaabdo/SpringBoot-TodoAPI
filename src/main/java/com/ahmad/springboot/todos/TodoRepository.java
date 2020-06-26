package com.ahmad.springboot.todos;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<Todo,String > {

    //Mongo will relate how to find the thing you need to find by the camel case and pass the value to parameter
    // example: findByDescriptionAndTimestampAndId(String desc, String timeStamp, String id)
    Todo findByTitle(String title);

    Todo findByTitleAndIdNot(String title, String id);

    List<Todo> findByUserId(String userId);

    Todo findByIdAndUserId(String userId, String id);

    boolean existsByUserIdAndId(String userId, String id);
}
