package test.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import test.project.config.jwt.JWTTokenProvider;
import test.project.domain.PantsDTO;
import test.project.entity.PantsEnt;
import test.project.entity.UserEnt;
import test.project.repository.PantsRepos;
import test.project.repository.UserRepos;
import test.project.service.PantsSrv;
import test.project.service.cloudinary.CloudinarySrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;

@Service
public class PantsSrvImpl implements PantsSrv{

	@Autowired
	private PantsRepos pantsRepos;
	
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
	public void create(PantsDTO dto, String authToken) {
		dto.setPantsId(stringUtils.generate());
		
		String username = jwtTokenProvider.getUsername(authToken.substring(7, authToken.length()));				
		UserEnt user = userRepos.findByUsername(username);
		
		PantsEnt pants = modelMapper.map(dto, PantsEnt.class);
		pants.setUser(user);
		
		pantsRepos.save(pants);
		
	}

	@Override
	public void update(PantsDTO dto) {
		pantsRepos.save(modelMapper.map(dto, PantsEnt.class));
		
	}

	@Override
	public void delete(String pantsId) {
		PantsEnt pants = pantsRepos.findByPantsId(pantsId);
		pantsRepos.delete(pants);
		
	}

	@Override
	public List<PantsDTO> findAll() {
		return modelMapper.mapAll(pantsRepos.findAll(), PantsDTO.class);
	}

	@Override
	public List<PantsDTO> findAllPants(Pageable pageable) {
		Page<PantsEnt> pantsPage = 
				pantsRepos.findAll(
						PageRequest.of(pageable.getPageNumber(), 
								       pageable.getPageSize(),
								       pageable.getSort())
						);
		List<PantsEnt> pantsEnt = pantsPage.getContent();
		List<PantsDTO> pantsDTOs =
				modelMapper.mapAll(pantsEnt, PantsDTO.class);
		return pantsDTOs;
	}

	@Override
	public PantsDTO findByPantsId(String pantsId) {
		return modelMapper.map(pantsRepos.findByPantsId(pantsId), PantsDTO.class);
	}

	@Override
	public void uploadImage(MultipartFile file, String pantsId) {
		String imageUrl = cloudinarySrv.uploadFile(file, "pants");
		PantsEnt pantsEnt = pantsRepos.findByPantsId(pantsId);
		pantsEnt.setImageUrl(imageUrl);
		pantsRepos.save(pantsEnt);
		
	}
	
}
