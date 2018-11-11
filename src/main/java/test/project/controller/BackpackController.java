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

import test.project.domain.BackpackDTO;
import test.project.service.BackpackSrv;

@RestController
@RequestMapping("backpack")
public class BackpackController {

	@Autowired
	BackpackSrv backpackSrv;
	
	@PostMapping
	public ResponseEntity<Void> addBackpack(@RequestBody BackpackDTO backpack, @RequestHeader("Authorization") String authToken) {
		backpackSrv.create(backpack, authToken);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{backpackId}")
	public ResponseEntity<Void> updateBackpack (
			@PathVariable("backpackId") String backpackId, 
			@RequestBody BackpackDTO backpackDTO) {
		
		BackpackDTO backpackDTOFromDB = backpackSrv.findByBackpackId(backpackId);
		
		if(backpackDTOFromDB != null) {
			backpackDTO.setId(backpackDTOFromDB.getId());
			backpackDTO.setBackpackId(backpackId);;
			backpackSrv.update(backpackDTO);;
			return new ResponseEntity<Void>(HttpStatus.OK);			
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/{backpackId}")
	public ResponseEntity<Void> deleteBackpack(@PathVariable("backpackId") String backpackId) {
		
		BackpackDTO backpackDTO = backpackSrv.findByBackpackId(backpackId);
		if(backpackDTO != null) {
			backpackSrv.delete(backpackId);;
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<BackpackDTO>> getBackpacks() {
		List<BackpackDTO> backpaks = backpackSrv.findAll();
		return new ResponseEntity<List<BackpackDTO>>(backpaks, HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<BackpackDTO>> getBootsByPage(@PageableDefault Pageable pageable) {
				
		return new ResponseEntity<List<BackpackDTO>>(backpackSrv.findAllBackpacks(pageable), HttpStatus.OK);
	}
	
	@GetMapping("{backpackId}")
	public ResponseEntity<BackpackDTO> getBackpackById(@PathVariable("backpackId") String backpackId) {
		BackpackDTO backpack = backpackSrv.findByBackpackId(backpackId);
		return new ResponseEntity<BackpackDTO>(backpack, HttpStatus.OK);
	}
	
	@GetMapping("/byVolume")
	public ResponseEntity<List<BackpackDTO>> getBackpackByVolume(@RequestParam("volume") Long volume) {
		List<BackpackDTO> backpacks = backpackSrv.findByVolume(volume);
		return new ResponseEntity<List<BackpackDTO>>(backpacks, HttpStatus.OK);
	}
	
	@PostMapping("image/{backpackId}")
	public ResponseEntity<Void> uploadImage(
			@PathVariable("backpackId") String backpackId,
			@RequestParam("image") MultipartFile file) {
		
		backpackSrv.uploadImage(file, backpackId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
