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

import test.project.domain.PantsDTO;
import test.project.service.PantsSrv;

@RestController
@RequestMapping("pants")
public class PantsController {

	@Autowired
	PantsSrv pantsSrv;
	
	@PostMapping
	public ResponseEntity<Void> addPants(@RequestBody PantsDTO pants, @RequestHeader("Authorization") String authToken) {
		pantsSrv.create(pants, authToken);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{pantsId}")
	public ResponseEntity<Void> updatePants (
			@PathVariable("pantsId") String pantsId, 
			@RequestBody PantsDTO pantsDTO) {
		
		PantsDTO pantsDTOFromDB = pantsSrv.findByPantsId(pantsId);
		
		if(pantsDTOFromDB != null) {
			pantsDTO.setId(pantsDTOFromDB.getId());
			pantsDTO.setPantsId(pantsId);;
			pantsSrv.update(pantsDTO);;
			return new ResponseEntity<Void>(HttpStatus.OK);			
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/{pantsId}")
	public ResponseEntity<Void> deletePants(@PathVariable("pantsId") String pantsId) {
		
		PantsDTO pantsDTO = pantsSrv.findByPantsId(pantsId);
		if(pantsDTO != null) {
			pantsSrv.delete(pantsId);;
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<PantsDTO>> getPants() {
		List<PantsDTO> pants = pantsSrv.findAll();
		return new ResponseEntity<List<PantsDTO>>(pants, HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<PantsDTO>> getPantsByPage(@PageableDefault Pageable pageable) {
				
		return new ResponseEntity<List<PantsDTO>>(pantsSrv.findAllPants(pageable), HttpStatus.OK);
	}
	
	@GetMapping("{pantsId}")
	public ResponseEntity<PantsDTO> getPantsById(@PathVariable("pantsId") String pantsId) {
		PantsDTO pants = pantsSrv.findByPantsId(pantsId);
		return new ResponseEntity<PantsDTO>(pants, HttpStatus.OK);
	}
	
	@GetMapping("/bySize")
	public ResponseEntity<List<PantsDTO>> getPantsBySize(@RequestParam("size") String size){
		List<PantsDTO> pants = pantsSrv.findBySize(size);
		return new ResponseEntity<List<PantsDTO>>(pants, HttpStatus.OK);
	}
	
	@PostMapping("image/{pantsId}")
	public ResponseEntity<Void> uploadImage(
			@PathVariable("pantsId") String pantsId,
			@RequestParam("image") MultipartFile file) {
		
		pantsSrv.uploadImage(file, pantsId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
