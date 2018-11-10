package test.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import test.project.config.jwt.JWTTokenProvider;
import test.project.domain.BackpackDTO;
import test.project.entity.BackpackEnt;
import test.project.entity.UserEnt;
import test.project.repository.BackpackRepos;
import test.project.repository.UserRepos;
import test.project.service.BackpackSrv;
import test.project.service.cloudinary.CloudinarySrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;

@Service
public class BackpackSrvImpl implements BackpackSrv{
	
	@Autowired
	private BackpackRepos backpackRepos;
	
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
	public void create(BackpackDTO dto, String authToken) {
		dto.setBackpackId(stringUtils.generate());
		
		String username = jwtTokenProvider.getUsername(authToken.substring(7, authToken.length()));
		UserEnt user = userRepos.findByUsername(username);
		
		BackpackEnt backpack = modelMapper.map(dto, BackpackEnt.class);
		backpack.setUser(user);
		
		backpackRepos.save(backpack);	
		
	}

	@Override
	public void update(BackpackDTO dto) {
		backpackRepos.save(modelMapper.map(dto, BackpackEnt.class));
		
	}

	@Override
	public void delete(String backpackId) {
		BackpackEnt backpack = backpackRepos.findByBackpackId(backpackId);
		backpackRepos.delete(backpack);
		
	}

	@Override
	public List<BackpackDTO> findAll() {
		return modelMapper.mapAll(backpackRepos.findAll(), BackpackDTO.class);
	}

	@Override
	public List<BackpackDTO> findAllBackpacks(Pageable pageable) {
		Page<BackpackEnt> backpackPage = 
				backpackRepos.findAll(
						PageRequest.of(pageable.getPageNumber(), 
								       pageable.getPageSize(),
								       pageable.getSort())
						);
		List<BackpackEnt> backpacksEnt = backpackPage.getContent();
		List<BackpackDTO> backpacksDTOs =
				modelMapper.mapAll(backpacksEnt, BackpackDTO.class);
		return backpacksDTOs;
	}

	@Override
	public List<BackpackDTO> findByVolume(Long volume) {
		return modelMapper.mapAll(backpackRepos.findByVolume(volume), BackpackDTO.class);
	}

	@Override
	public void uploadImage(MultipartFile file, String backpackId) {
		String imageUrl = cloudinarySrv.uploadFile(file, "backpack");
		BackpackEnt backpackEnt = backpackRepos.findByBackpackId(backpackId);
		backpackEnt.setImageUrl(imageUrl);
		backpackRepos.save(backpackEnt);
		
	}

	@Override
	public BackpackDTO findByBackpackId(String backpackId) {
		return modelMapper.map(backpackRepos.findByBackpackId(backpackId), BackpackDTO.class);
	}

}
