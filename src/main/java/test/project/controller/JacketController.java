package test.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import test.project.domain.JacketDTO;
import test.project.service.JacketSrv;

@RestController
@RequestMapping("jackat")
public class JacketController {

	@Autowired
	JacketSrv jacketSrv;
	
	@PostMapping
	public ResponseEntity<Void> addJacket(@RequestBody JacketDTO jacket, @RequestHeader("Authorization") String authToken) {
		jacketSrv.create(jacket, authToken);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{jacketId}")
	public ResponseEntity<Void> updateJacket (
			@PathVariable("jacketId") String jacketId, 
			@RequestBody JacketDTO jacketDTO) {
		
		JacketDTO jacketDTOFromDB = jacketSrv.findByJacketId(jacketId);
		
		if(jacketDTOFromDB != null) {
			jacketDTO.setId(jacketDTOFromDB.getId());
			jacketDTO.setJacketId(jacketId);;
			jacketSrv.update(jacketDTO);;
			return new ResponseEntity<Void>(HttpStatus.OK);			
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/{jacketId}")
	public ResponseEntity<Void> deleteJacket(@PathVariable("jacketId") String jacketId) {
		
		JacketDTO jacketDTO = jacketSrv.findByJacketId(jacketId);
		if(jacketDTO != null) {
			jacketSrv.delete(jacketId);;
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<JacketDTO>> getJackets() {
		List<JacketDTO> jacket = jacketSrv.findAll();
		return new ResponseEntity<List<JacketDTO>>(jacket, HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<JacketDTO>> getJacketByPage(@PageableDefault Pageable pageable) {
				
		return new ResponseEntity<List<JacketDTO>>(jacketSrv.findAllJackets(pageable), HttpStatus.OK);
	}
	
	@GetMapping("{jacketId}")
	public ResponseEntity<JacketDTO> getJacketById(@PathVariable("jacketId") String jacketId) {
		JacketDTO jacket = jacketSrv.findByJacketId(jacketId);
		return new ResponseEntity<JacketDTO>(jacket, HttpStatus.OK);
	}
	
	@GetMapping("/bySize")
	public ResponseEntity<List<JacketDTO>> getJacketBySize(@RequestParam("size") String size) {
		List<JacketDTO> jackets = jacketSrv.findBySize(size);
		return new ResponseEntity<List<JacketDTO>>(jackets, HttpStatus.OK);
	}
	@PostMapping("image/{jacketId}")
	public ResponseEntity<Void> uploadImage(
			@PathVariable("jacketId") String jacketId,
			@RequestParam("image") MultipartFile file) {
		
		jacketSrv.uploadImage(file, jacketId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
