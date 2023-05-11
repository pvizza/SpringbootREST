package apirest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import apirest.model.UserModel;
import apirest.repository.UserRepository;

class MockDataLoad {
  // This line of code is creating a logger object named `log` using the `LoggerFactory` class from the
  // SLF4J (Simple Logging Facade for Java) library. The `LoggerFactory.getLogger(MockDataLoad.class)`
  // method is used to get a logger instance for the `MockDataLoad` class. The `private static final`
  // keywords indicate that the `log` variable is a private, static, and final field, meaning it can
  // only be accessed within the `MockDataLoad` class, it is a class-level variable that can be
  // accessed without creating an instance of the class, and its value cannot be changed once it is
  // initialized. This logger object can be used to log messages and errors throughout the
  // `MockDataLoad` class.
  private static final Logger log = LoggerFactory.getLogger(MockDataLoad.class);
    
   /**
    * This is a method that initializes a database with two preloaded user models named Messi and
    * Ronaldo.
    * 
    * @param repository The repository parameter is an instance of the UserRepository interface, which
    * is used to perform CRUD (Create, Read, Update, Delete) operations on the User entity in the
    * database. It is injected into the method using Spring's dependency injection mechanism.
    * @return A CommandLineRunner object is being returned.
    */
    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
  
      return args -> {
        log.info("Preloading " + repository.save(new UserModel("Messi", "player")));
        log.info("Preloading " + repository.save(new UserModel("Ronaldo", "player")));
      };
  }

}
