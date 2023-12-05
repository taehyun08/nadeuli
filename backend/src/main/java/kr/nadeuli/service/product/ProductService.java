package kr.nadeuli.service.product;

import kr.nadeuli.dto.ProductDTO;
import kr.nadeuli.dto.SearchDTO;

import java.util.List;

 public interface ProductService {
     Long addProduct(ProductDTO productDTO) throws Exception;

     void updateProduct(ProductDTO productDTO) throws Exception;

     ProductDTO getProduct(long productId) throws Exception;

     List<ProductDTO> getProductList(String gu, SearchDTO searchDTO) throws Exception;

     List<ProductDTO> getMyProductList(String sellerTag, SearchDTO searchDTO) throws Exception;

     void deleteProduct(long productId) throws Exception;

     void saleCompleted(long productId) throws Exception;

     boolean updatePremiumTime(long productId) throws Exception;

}
