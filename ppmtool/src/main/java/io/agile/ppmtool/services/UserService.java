package io.agile.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.agile.ppmtool.domain.User;
import io.agile.ppmtool.exceptions.UserAlreadyExistException;
import io.agile.ppmtool.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser(User newUser) {
		try {
			newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			newUser.setUsername(newUser.getUsername());
			return userRepository.save(newUser);
		}
		catch(Exception exc) {
			throw new UserAlreadyExistException("Username: "+newUser.getUsername()+" already exists");
		}
	}
}
