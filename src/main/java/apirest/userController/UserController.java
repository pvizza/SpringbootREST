package apirest.userController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import apirest.repository.UserRepository;
import apirest.model.UserModel;

@RestController
public class UserController {
  private final UserRepository repository;

  public UserController(UserRepository repository) {
    this.repository = repository; 
  }

   @GetMapping("/users")
  List<UserModel> all() {
    return repository.findAll();
  }

  @PostMapping("/users")
  public UserModel createUsers(@RequestBody UserModel newUsers) {
    return repository.save(newUsers);
  }

  @PutMapping("/users/{id}")
  public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel newUsers) {
      return repository.findById(id)
      .map(user -> {
        user.setName(newUsers.getName());
        user.setRole(newUsers.getRole());
        return repository.save(user);
      })
      .orElseGet(() -> {
        newUsers.setId(id);
        return repository.save(newUsers);
      });
  }

   @DeleteMapping("/users/{id}")
   void deleteUsers(@PathVariable Long id) {
      repository.deleteById(id);
    }
 
}