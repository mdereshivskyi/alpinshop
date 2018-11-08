package test.project.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
