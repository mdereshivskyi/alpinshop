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

import test.project.domain.EquipmentDTO;
import test.project.service.EquipmentSrv;

@RestController
@RequestMapping("equipment")
public class EquipmentController {

	@Autowired
	EquipmentSrv equipmentSrv;
	
	@PostMapping
	public ResponseEntity<Void> addEquipment(@RequestBody EquipmentDTO equipment, @RequestHeader("Authorization") String authToken) {
		equipmentSrv.create(equipment, authToken);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{equipmentId}")
	public ResponseEntity<Void> updateEquipment (
			@PathVariable("equipmentId") String equipmentId, 
			@RequestBody EquipmentDTO equipmentDTO) {
		
		EquipmentDTO equipmentDTOFromDB = equipmentSrv.findByEquipmentId(equipmentId);
		
		if(equipmentDTOFromDB != null) {
			equipmentDTO.setId(equipmentDTOFromDB.getId());
			equipmentDTO.setEquipmentId(equipmentId);;
			equipmentSrv.update(equipmentDTO);;
			return new ResponseEntity<Void>(HttpStatus.OK);			
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/{equipmentId}")
	public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId) {
		
		EquipmentDTO equipmentDTO = equipmentSrv.findByEquipmentId(equipmentId);
		if(equipmentDTO != null) {
			equipmentSrv.delete(equipmentId);;
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<EquipmentDTO>> getEquipments() {
		List<EquipmentDTO> equipment = equipmentSrv.findAll();
		return new ResponseEntity<List<EquipmentDTO>>(equipment, HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	public ResponseEntity<List<EquipmentDTO>> getEquipmentByPage(@PageableDefault Pageable pageable) {
				
		return new ResponseEntity<List<EquipmentDTO>>(equipmentSrv.findAllEquipments(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/byType")
	public ResponseEntity<List<EquipmentDTO>> getEquipmentByType(@RequestParam("type") String type) {
		List<EquipmentDTO> equipment = equipmentSrv.findByType(type);
		return new ResponseEntity<List<EquipmentDTO>>(equipment, HttpStatus.OK);
	}
	
	@GetMapping("{equipmentId}")
	public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable("equipmentId") String equipmentId) {
		EquipmentDTO equipment = equipmentSrv.findByEquipmentId(equipmentId);
		return new ResponseEntity<EquipmentDTO>(equipment, HttpStatus.OK);
	}
	
	@PostMapping("image/{equipmentId}")
	public ResponseEntity<Void> uploadImage(
			@PathVariable("equipmentId") String equipmentId,
			@RequestParam("image") MultipartFile file) {
		
		equipmentSrv.uploadImage(file, equipmentId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
}
