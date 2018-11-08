package test.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.project.domain.CategoryDTO;
import test.project.service.CategorySrv;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategorySrv categorySrv;
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CategoryDTO category){
		categorySrv.create(category);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<CategoryDTO> category = categorySrv.getAll();
		return new ResponseEntity<List<CategoryDTO>>(category, HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable("categoryId") Long id){
		CategoryDTO category = categorySrv.findById(id);
		return new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
	}
}
