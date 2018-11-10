package test.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import test.project.config.jwt.JWTTokenProvider;
import test.project.domain.JacketDTO;
import test.project.entity.JacketEnt;
import test.project.entity.UserEnt;
import test.project.repository.JacketRepos;
import test.project.repository.UserRepos;
import test.project.service.JacketSrv;
import test.project.service.cloudinary.CloudinarySrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;

@Service
public class JacketSrvImpl implements JacketSrv{

	@Autowired
	private JacketRepos jacketRepos;
	
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
	public void create(JacketDTO dto, String authToken) {
		dto.setJacketId(stringUtils.generate());
		
		String username = jwtTokenProvider.getUsername(authToken.substring(7, authToken.length()));				
		UserEnt user = userRepos.findByUsername(username);
		
		JacketEnt jacket = modelMapper.map(dto, JacketEnt.class);
		jacket.setUser(user);
		
		jacketRepos.save(jacket);
		
	}

	@Override
	public void update(JacketDTO dto) {
		jacketRepos.save(modelMapper.map(dto, JacketEnt.class));
		
	}

	@Override
	public void delete(String jacketId) {
		JacketEnt jacket = jacketRepos.findByJacketId(jacketId);
		jacketRepos.delete(jacket);
		
	}

	@Override
	public List<JacketDTO> findAll() {
		return modelMapper.mapAll(jacketRepos.findAll(), JacketDTO.class);
	}

	@Override
	public List<JacketDTO> findAllJackets(Pageable pageable) {
		Page<JacketEnt> jacketsPage = 
				jacketRepos.findAll(
						PageRequest.of(pageable.getPageNumber(), 
								       pageable.getPageSize(),
								       pageable.getSort())
						);
		List<JacketEnt> jacketsEnt = jacketsPage.getContent();
		List<JacketDTO> jacketsDTOs =
				modelMapper.mapAll(jacketsEnt, JacketDTO.class);
		return jacketsDTOs;
	}

	@Override
	public JacketDTO findByJacketId(String jacketId) {
		return modelMapper.map(jacketRepos.findByJacketId(jacketId), JacketDTO.class);
	}

	@Override
	public void uploadImage(MultipartFile file, String jacketId) {
		String imageUrl = cloudinarySrv.uploadFile(file, "jackets");
		JacketEnt jacketEnt = jacketRepos.findByJacketId(jacketId);
		jacketEnt.setImageUrl(imageUrl);
		jacketRepos.save(jacketEnt);
		
	}
	
}
