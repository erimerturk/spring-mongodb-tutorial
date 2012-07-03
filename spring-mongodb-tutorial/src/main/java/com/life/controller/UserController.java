package com.life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.life.domain.Auth;
import com.life.domain.User;
import com.life.service.UserService;
import com.life.service.auth.impl.AuthServiceImpl;

@Controller
@RequestMapping("/users")
public class UserController {
   
   @Autowired
   private UserService userService;
   
   @Autowired
   private AuthServiceImpl authService;
   
   @RequestMapping(value = "/records", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
   public @ResponseBody List<User> getUsers()
   {
      return userService.readAll();
   }
   
   @RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
   public @ResponseBody User createUser(@RequestBody User user)
   {
      Preconditions.checkNotNull(user, "User cant be null");
      User checkedUser = userService.findUserByUserName(user.getUsername());
      Preconditions.checkArgument(checkedUser == null, user.getUsername() + " has already token.");
      userService.create(user);
      return userService.findUserByUserName(user.getUsername());
   }
   
   @RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
   public @ResponseBody Auth login(@RequestParam String username, @RequestParam String password)
   {
      Preconditions.checkNotNull(username, "UserName cant be null");
      Preconditions.checkNotNull(password, "password cant be null");
      Auth auth = userService.login(username, password);
      return auth;
   }
   
   @RequestMapping(value = "/logout/{autId}", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
   public @ResponseBody String logout(@PathVariable(value = "autId") String autId)
   {
      authService.logout(autId);
      return "OK";
   }
}
