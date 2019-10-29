package hello.model;

import org.springframework.data.annotation.Id;

@Document
public final class User {

  @Id
  public String id;
  @Field("firstName")
  public String firstName;
  @Field("lastName")
  public String lastName;

  public User(String id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}