package test.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import test.project.entity.UserEnt;
import test.project.entity.enums.UserRole;
import test.project.repository.UserRepos;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "test.project.repository")
public class TestProjectApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TestProjectApplication.class, args);
	}
	
	@Autowired
	private UserRepos userRepos;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		if(userRepos.count() == 0) {
			UserEnt userEnt = new UserEnt();
			userEnt.setFirstName("Admin");
			userEnt.setLastName("Admin");
			userEnt.setUsername("admin");
			userEnt.setPhoneNumber("0968404150");
			userEnt.setEmail("for.rider.shop@gmail.com");
			userEnt.setRole(UserRole.ROLE_ADMIN);
			userEnt.setPassword(passwordEncoder.encode("admin"));
			
			userRepos.save(userEnt);
		}
		
	}
	
}
