package it.ats.springboot.provaMySQL.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class MainController {

	Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/{id}") 
	public @ResponseBody User getUserById(@PathVariable Integer id) {
		return userService.getById(id);
	}
	
	@GetMapping("/user")
	public @ResponseBody Iterable<User> getUserByName(@RequestParam String name){
		return userService.getByName(name);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers(){
		return userService.getAll();
	}
	
	@PostMapping("/user")
	public @ResponseBody String addNewUser(@RequestBody User newUser) {
		if(userService.add(newUser)) {
			logger.info("New user saved");
			return "New user saved";
		}

		logger.warn("User already exist");
		return "User already exists";
	}
	
	@PostMapping("/users")
	public @ResponseBody String addNewUsers(@RequestBody Iterable<User> newUsers) {
		userService.add(newUsers);
		return "New users saved";
	}
	
	@PutMapping("/user")
	public @ResponseBody String updateUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String email) {
		if (userService.update(id, name, email)){
			logger.info("User updated");
			return "User updated";
		}

		logger.warn("User doesn't exist");
		return "User doesn't exist";
	}
	
	@PutMapping("/user/{id}")
	public @ResponseBody String updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
		if (userService.update(id, updatedUser)) {
			logger.info("User updated");
			return "User updated";
		}

		logger.warn("user doesn't exist");
		return "User doesn't exist";
	}
	
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable Integer id) {
		if(userService.delete(id)) {
			logger.info("User deleted");
			return "User deleted";
		}

		logger.warn("User doesn't exist");
		return "User doesn't exist";
	}
	
	@DeleteMapping("/user")
	public @ResponseBody String deleteByName(@RequestParam String name) {
		if(userService.deleteByName(name)) {
			logger.info("User(s) deleted");
			return "User(s) deleted";
		}

		logger.warn("User(s) not found");
		return "User(s) not found";
	}
}
