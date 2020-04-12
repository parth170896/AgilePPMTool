package io.agile.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.agile.ppmtool.domain.User;
import io.agile.ppmtool.services.UserService;
import io.agile.ppmtool.services.ValidationErrorService;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ValidationErrorService errorService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
			
		ResponseEntity<?> errorMap=errorService.mapValidationService(result);
		if(errorMap!=null) {
			return errorMap;
		}
		User newUser=userService.saveUser(user);
		
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
}
