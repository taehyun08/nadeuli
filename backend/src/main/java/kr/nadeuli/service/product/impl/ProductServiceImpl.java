package kr.nadeuli.service.product.impl;

import kr.nadeuli.dto.ProductDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.entity.Product;
import kr.nadeuli.mapper.ProductMapper;
import kr.nadeuli.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void addProduct(ProductDTO productDTO) throws Exception {
        Product product = productMapper.productDTOToProduct(productDTO);
        log.info(product);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) throws Exception {
        Product product = productMapper.productDTOToProduct(productDTO);
        log.info(product);
        productRepository.save(product);
    }

    @Override
    public ProductDTO getProduct(long productId) throws Exception {
        return productRepository.findById(productId).map(productMapper::productToProductDTO).orElse(null);
    }

    @Override
    public List<ProductDTO> getProductList(SearchDTO searchDTO) throws Exception {
        Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
        Pageable pageable = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getPageUnit(), sort);
        Page<Product> productPage;
        if(searchDTO.getSearchKeyword() == null || searchDTO.getSearchKeyword().isEmpty()){
            productPage = productRepository.findAll(pageable);
        }else{
            productPage = productRepository.findByTitleContainingOrContentContaining(searchDTO.getSearchKeyword(), searchDTO.getSearchKeyword(), pageable);
        }
        log.info(productPage);
        return productPage.map(productMapper::productToProductDTO).toList();
    }

    @Override
    public void deleteProduct(long productId) throws Exception {
        log.info(productId);
        productRepository.deleteById(productId);
    }

    @Override
    public void saleCompleted(long productId) throws Exception {
        Product product = Product.builder()
                                 .productId(productId)
                                 .isSold(true)
                                 .build();
        productRepository.save(product);
        log.info(product);
    }
}
