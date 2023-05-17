package apirest.userController;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public ResponseEntity<Object> createUsers(@RequestBody Map<String, Object> request) {
  String name = (String) request.get("name");
  String role = (String) request.get("role");

  if (name == null || role == null || name.isEmpty() || role.isEmpty()) {
    String message = "Name and role are required fields.";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  // Check for extra parameters
  boolean hasExtraParameter = request.keySet().stream()
      .anyMatch(parameter -> !parameter.equals("name") && !parameter.equals("role"));

  if (hasExtraParameter) {
    String message = "Only 'name' and 'role' parameters are allowed.";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  UserModel newUser = new UserModel();
  newUser.setName(name);
  newUser.setRole(role);

  UserModel savedUser = repository.save(newUser);
  return ResponseEntity.ok(savedUser);
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