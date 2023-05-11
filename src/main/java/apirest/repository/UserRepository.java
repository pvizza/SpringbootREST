package apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import apirest.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
  
}
