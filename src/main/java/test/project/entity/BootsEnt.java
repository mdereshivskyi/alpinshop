package test.project.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "boots")
public class BootsEnt extends BaseEnt{

	@Column(nullable = false, unique = true)
	private String bootsId;
	
	@Column(nullable = false)
	private float size;
	
	@Column(nullable = false)
	private String color;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	private String imageUrl;
	
	@Column(columnDefinition = "DECIMAL(9,2)",nullable = false)
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEnt user;
}
