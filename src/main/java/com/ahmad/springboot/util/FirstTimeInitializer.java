package com.ahmad.springboot.util;

import com.ahmad.springboot.security.AppUser;
import com.ahmad.springboot.security.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * Class will be called once the controller started || Once the application started
 */

@Component
public class FirstTimeInitializer implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if(userService.findAll().isEmpty()){
            logger.info("No users accounts found. Creating some users");

            AppUser user = new AppUser("a7maabdo@gmail.com","pass","ahmad");
            userService.save(user);
        }

    }
}
