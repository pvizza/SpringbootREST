package apirest.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class UserModel {
  
  private @Id @GeneratedValue @Column(name = "id") Long id;
  private String name;
  private String role;
  private String other;

  public UserModel() {}

  public UserModel(String name, String role) {
    this.name = name;
    this.role = role;
    this.other = null;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getRole() {
    return this.role;
  }

  public String getOther() {
    return this.other;
  }

  public void setOther(String other) {
    this.other = other;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof UserModel))
      return false;
    UserModel usermodel = (UserModel) o;
    return Objects.equals(this.id, usermodel.id) && Objects.equals(this.name, usermodel.name) && Objects.equals(this.role, usermodel.role);
  }
}
