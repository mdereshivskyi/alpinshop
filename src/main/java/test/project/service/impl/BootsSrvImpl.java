package test.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import test.project.config.jwt.JWTTokenProvider;
import test.project.domain.BootsDTO;
import test.project.entity.BootsEnt;
import test.project.entity.UserEnt;
import test.project.repository.BootsRepos;
import test.project.repository.UserRepos;
import test.project.service.BootsSrv;
import test.project.service.cloudinary.CloudinarySrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;

@Service
public class BootsSrvImpl implements BootsSrv{

	@Autowired
	private BootsRepos bootsRepos;
	
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
	public void create(BootsDTO dto, String authToken) {
		dto.setBootsId(stringUtils.generate());
		
		String username = jwtTokenProvider.getUsername(authToken.substring(7, authToken.length()));
		UserEnt user = userRepos.findByUsername(username);
		
		BootsEnt boots = modelMapper.map(dto, BootsEnt.class);
		boots.setUser(user);
		
		bootsRepos.save(boots);	
		
	}

	@Override
	public void update(BootsDTO dto) {
		bootsRepos.save(modelMapper.map(dto, BootsEnt.class));
		
	}

	@Override
	public void delete(String bootsId) {
		BootsEnt boots = bootsRepos.findByBootsId(bootsId);
		bootsRepos.delete(boots);
	}

	@Override
	public List<BootsDTO> findAll() {
		return modelMapper.mapAll(bootsRepos.findAll(), BootsDTO.class);
	}

	@Override
	public List<BootsDTO> findAllBoots(Pageable pageable) {
		Page<BootsEnt> bootsPage = 
				bootsRepos.findAll(
						PageRequest.of(pageable.getPageNumber(), 
								       pageable.getPageSize(),
								       pageable.getSort())
						);
		List<BootsEnt> bootsEntities = bootsPage.getContent();
		List<BootsDTO> bootsDTOs =
				modelMapper.mapAll(bootsEntities, BootsDTO.class);
		return bootsDTOs;
	}

	@Override
	public List<BootsDTO> findBySize(float size) {
		return modelMapper.mapAll(bootsRepos.findBySize(size), BootsDTO.class);
	}

	@Override
	public void uploadImage(MultipartFile file, String bootsId) {
		String imageUrl = cloudinarySrv.uploadFile(file, "boots");
		BootsEnt bootsEnt = bootsRepos.findByBootsId(bootsId);
		bootsEnt.setImageUrl(imageUrl);
		bootsRepos.save(bootsEnt);
		
	}

	@Override
	public BootsDTO findByBootsId(String bootsId) {
		return modelMapper.map(bootsRepos.findByBootsId(bootsId), BootsDTO.class);
	}

}
