package test.project.service.cloudinary;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinarySrv {
	
	String uploadFile(MultipartFile file, String folder);
	
}
