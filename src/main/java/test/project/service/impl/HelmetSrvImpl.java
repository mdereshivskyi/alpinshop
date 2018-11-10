package test.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import test.project.config.jwt.JWTTokenProvider;
import test.project.domain.HelmetDTO;
import test.project.entity.HelmetEnt;
import test.project.entity.UserEnt;
import test.project.repository.HelmetRepos;
import test.project.repository.UserRepos;
import test.project.service.HelmetSrv;
import test.project.service.cloudinary.CloudinarySrv;
import test.project.service.utils.ObjectMapperUtils;
import test.project.service.utils.StringUtils;

@Service
public class HelmetSrvImpl implements HelmetSrv{

	@Autowired
	private HelmetRepos helmetRepos;
	
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
	public void create(HelmetDTO dto, String authToken) {
		dto.setHelmetId(stringUtils.generate());
		
		String username = jwtTokenProvider.getUsername(authToken.substring(7, authToken.length()));				
		UserEnt user = userRepos.findByUsername(username);
		
		HelmetEnt helmet = modelMapper.map(dto, HelmetEnt.class);
		helmet.setUser(user);
		
		helmetRepos.save(helmet);
		
	}

	@Override
	public void update(HelmetDTO dto) {
		helmetRepos.save(modelMapper.map(dto, HelmetEnt.class));
		
	}

	@Override
	public void delete(String helmetId) {
		HelmetEnt helmet = helmetRepos.findByHelmetId(helmetId);
		helmetRepos.delete(helmet);
		
	}

	@Override
	public List<HelmetDTO> findAll() {
		return modelMapper.mapAll(helmetRepos.findAll(), HelmetDTO.class);
	}

	@Override
	public List<HelmetDTO> findAllHelmets(Pageable pageable) {
		Page<HelmetEnt> helmetsPage = 
				helmetRepos.findAll(
						PageRequest.of(pageable.getPageNumber(), 
								       pageable.getPageSize(),
								       pageable.getSort())
						);
		List<HelmetEnt> helmetsEnt = helmetsPage.getContent();
		List<HelmetDTO> helmetsDTOs =
				modelMapper.mapAll(helmetsEnt, HelmetDTO.class);
		return helmetsDTOs;
	}

	@Override
	public HelmetDTO findByHelmetId(String helmetId) {
		return modelMapper.map(helmetRepos.findByHelmetId(helmetId), HelmetDTO.class);
	}

	@Override
	public void uploadImage(MultipartFile file, String helmetId) {
		String imageUrl = cloudinarySrv.uploadFile(file, "helmets");
		HelmetEnt helmetEnt = helmetRepos.findByHelmetId(helmetId);
		helmetEnt.setImageUrl(imageUrl);
		helmetRepos.save(helmetEnt);
		
	}

}
