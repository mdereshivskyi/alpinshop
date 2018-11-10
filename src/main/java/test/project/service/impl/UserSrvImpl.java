package test.project.service.impl;

import static test.project.constants.ErrorMessage.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import test.project.config.jwt.JWTTokenProvider;
import test.project.domain.UserDTO;
import test.project.domain.mail.Mail;
import test.project.entity.UserEnt;
import test.project.entity.enums.UserRole;
import test.project.exceptions.UserServiceException;
import test.project.repository.UserRepos;
import test.project.service.EmailSrv;
import test.project.service.UserSrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;

@Service
public class UserSrvImpl implements UserSrv{

	@Autowired
	private UserRepos userRepos;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ObjectMapperUtils objectMapper;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private EmailSrv emailSrv;
	
	@Autowired
	private StringUtils stringUtils;


	@Override
	public void save(UserDTO dto) {
		String verifyToken = stringUtils.generate(100);
		System.out.println(dto.getEmail());

		if (userRepos.existsByUsername(dto.getUsername())) {
			throw new UserServiceException(RECORD_ALREADY_EXISTS);
		} else {
			dto.setRole(UserRole.ROLE_USER);
			System.out.println("Password:" + dto.getPassword());
			dto.setPassword(passwordEncoder.encode(dto.getPassword()));
			System.out.println("Password2:" + dto.getPassword());
			
			UserEnt userEnt = objectMapper.map(dto, UserEnt.class);
			userEnt.setEmailVerificationToken(verifyToken);
			userEnt.setEmailVerificationStatus(Boolean.FALSE);
			
			userRepos.save(userEnt);
			sendEmail(dto.getEmail(), verifyToken);
			
		}
	
	}
	
	private void sendEmail(String email, String verifyToken) {
		String verifyUrl = getHostName() + "verify?token=" + verifyToken;
		
		Mail mail = new Mail();
		mail.setTo(email);
		mail.setSubject("You are successfully registered");
		mail.setContent("Please verify your account follow the link " + verifyUrl);
		
		emailSrv.sendMessage(mail);
		
	}
	
	private String getHostName() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
	}

	@Override
	public UserDTO findById(Long id) {
		return objectMapper.map(userRepos.findById(id), UserDTO.class);
	}

	@Override
	public List<UserDTO> findAll() {
		return objectMapper.mapAll(userRepos.findAll(), UserDTO.class);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepos.existsByUsername(username);
	}

	@Override
	public UserDTO findByUsername(String username) {
		return objectMapper.map(userRepos.findByUsername(username), UserDTO.class);
	}

	@Override
	public String signin(String username, String password) {
		System.out.println(">>> " + username);
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		System.out.println(">>> " + username);
		return jwtTokenProvider.createToken(username, userRepos.findByUsername(username).getRole());
	}

	@Override
	public void verifyAccount(String verifyToken) {
		UserEnt userEnt = userRepos.findByEmailVerificationToken(verifyToken);
		if(userEnt != null) {
			userEnt.setEmailVerificationToken(null);
			userEnt.setEmailVerificationStatus(Boolean.TRUE);
			userRepos.save(userEnt);
		}
	}

	@Override
	public boolean existsByEmail(String email) {		
		return userRepos.existsByEmail(email);
	}

}
