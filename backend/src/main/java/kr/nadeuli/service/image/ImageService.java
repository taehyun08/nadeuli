package kr.nadeuli.service.image;


import kr.nadeuli.dto.ImageDTO;
import kr.nadeuli.dto.SearchDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ImageService {

    void addImage(ImageDTO imageDTO) throws Exception;

    ImageDTO getImage(long imageId) throws Exception;

    List<ImageDTO> getImagesList(long ImageId, SearchDTO searchDTO) throws Exception;

    void updateImages(ImageDTO imageDTO) throws Exception;

    void deleteImage(long imageId) throws Exception;

}
