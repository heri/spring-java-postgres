package hello.controller;

import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import hello.model.User;
import hello.repository.DbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public final class HelloController {

  @Autowired
  private DbRepository dbRepository;

  @RequestMapping("/webhook")
  @ResponseBody
  USer webhook(@RequestParam String query) {
    var user = new User(query.get("id"), query.get("firstName"), query.get("lastName"));
    dbRepository.updateUser(user);
    return user;
  }

  @RequestMapping("/users")
  @ModelAttribute("users")
  List<User> users() {
    var users = dbRepository.users();

    return users;
  }

}
