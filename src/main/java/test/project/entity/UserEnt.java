package test.project.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import test.project.entity.enums.UserRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user")
public class UserEnt extends BaseEnt{

	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@Enumerated(EnumType.ORDINAL)
	private UserRole role;
	
	private String emailVerificationToken;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	private Boolean emailVerificationStatus;
}
