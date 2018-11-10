package test.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import test.project.config.jwt.JWTTokenProvider;
import test.project.domain.EquipmentDTO;
import test.project.entity.EquipmentEnt;
import test.project.entity.UserEnt;
import test.project.repository.EquipmentRepos;
import test.project.repository.UserRepos;
import test.project.service.EquipmentSrv;
import test.project.service.cloudinary.CloudinarySrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;
@Service
public class EquipmentSrvImpl implements EquipmentSrv{

	@Autowired
	private EquipmentRepos equipmentRepos;
	
	@Autowired
	private UserRepos userRepos;
	
	@Autowired
	private ObjectMapperUtils modelMapper;
	
	@Autowired
	private StringUtils stringUtils;
	
	@Autowired
	private CloudinarySrv cloudinarySrv;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;


	@Override
	public void create(EquipmentDTO dto, String authToken) {
		dto.setEquipmentId(stringUtils.generate());
		
		String username = jwtTokenProvider.getUsername(authToken.substring(7, authToken.length()));
		UserEnt user = userRepos.findByUsername(username);
		
		EquipmentEnt equipment = modelMapper.map(dto, EquipmentEnt.class);
		equipment.setUser(user);
		
		equipmentRepos.save(equipment);	
		
	}

	@Override
	public void update(EquipmentDTO dto) {
		equipmentRepos.save(modelMapper.map(dto, EquipmentEnt.class));
		
	}

	@Override
	public void delete(String equipmentId) {
		EquipmentEnt equipment = equipmentRepos.findByEquipmentId(equipmentId);
		equipmentRepos.delete(equipment);
		
	}

	@Override
	public List<EquipmentDTO> findAll() {
		return modelMapper.mapAll(equipmentRepos.findAll(), EquipmentDTO.class);
	}

	@Override
	public List<EquipmentDTO> findAllEquipments(Pageable pageable) {
		Page<EquipmentEnt> equipmentPage = 
				equipmentRepos.findAll(
						PageRequest.of(pageable.getPageNumber(), 
								       pageable.getPageSize(),
								       pageable.getSort())
						);
		List<EquipmentEnt> equipmentsEnt = equipmentPage.getContent();
		List<EquipmentDTO> equipmentsDTOs =
				modelMapper.mapAll(equipmentsEnt, EquipmentDTO.class);
		return equipmentsDTOs;
	}

	@Override
	public List<EquipmentDTO> findByType(String type) {
		return modelMapper.mapAll(equipmentRepos.findByType(type), EquipmentDTO.class);
	}

	@Override
	public void uploadImage(MultipartFile file, String equipmentId) {
		String imageUrl = cloudinarySrv.uploadFile(file, "equipment");
		EquipmentEnt equipmentEnt = equipmentRepos.findByEquipmentId(equipmentId);
		equipmentEnt.setImageUrl(imageUrl);
		equipmentRepos.save(equipmentEnt);
		
	}

	@Override
	public EquipmentDTO findByEquipmentId(String equipmentId) {
		return modelMapper.map(equipmentRepos.findByEquipmentId(equipmentId), EquipmentDTO.class);
	}
	

}
