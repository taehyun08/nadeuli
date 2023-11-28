package kr.nadeuli.service.product;

import kr.nadeuli.dto.ProductDTO;
import kr.nadeuli.dto.SearchDTO;

import java.util.List;

public interface ProductService {
    public void addProduct(ProductDTO productDTO) throws Exception;

    public void updateProduct(ProductDTO productDTO) throws Exception;

    public ProductDTO getProduct(long productId) throws Exception;

    public List<ProductDTO> getProductList(SearchDTO searchDTO) throws Exception;

    public void deleteProduct(long productId) throws Exception;

    public void saleCompleted(long productId) throws Exception;

    public boolean updatePremiumTime(long productId) throws Exception;

}
