package com.logistransport.config;

import com.logistransport.model.Role;
import com.logistransport.model.User;
import com.logistransport.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

	private final UserRepository userRepository;

	DataInitializer(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public CommandLineRunner createUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		
		return args -> {
			
			if (userRepository.findByEmail("admin").isEmpty()) {
				
				User admin = new User("admin",
						passwordEncoder.encode("admin123"),
						Role.ADMIN);
				
				userRepository.save(admin);
			}
			
           if (userRepository.findByEmail("admin2@gmail.com").isEmpty()) {
				
				User admin2 = new User("admin2@gmail.com",
						passwordEncoder.encode("admin123"),
						Role.ADMIN);
				
				userRepository.save(admin2);
			}     
		
		
		if (userRepository.findByEmail("customer@gmail.com").isEmpty()) {
			
			User customer = new User("customer@gmail.com",
					passwordEncoder.encode("customer123"), 
					Role.CUSTOMER);
			
			userRepository.save(customer);
		}
		};
	}
} 
