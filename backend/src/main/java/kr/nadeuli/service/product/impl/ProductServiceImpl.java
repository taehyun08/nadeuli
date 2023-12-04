package kr.nadeuli.service.product.impl;

import kr.nadeuli.dto.ProductDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.Product;
import kr.nadeuli.mapper.ProductMapper;
import kr.nadeuli.scheduler.PremiumTimeScheduler;
import kr.nadeuli.service.product.ProductRepository;
import kr.nadeuli.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PremiumTimeScheduler premiumTimeScheduler;

    @Override
    public void addProduct(ProductDTO productDTO) throws Exception {
        Product product = productMapper.productDTOToProduct(productDTO);
        log.info(product);
        productRepository.save(product);
        if(productDTO.isPremium()){
            premiumTimeScheduler.startPremiumTimeScheduler(product.getProductId());
        }
    }

    @Override
    public void updateProduct(ProductDTO productDTO) throws Exception {
        Product product = productMapper.productDTOToProduct(productDTO);
        log.info(product);
        productRepository.save(product);
        if(productDTO.isPremium()){
            premiumTimeScheduler.startPremiumTimeScheduler(product.getProductId());
        }
    }

    @Override
    public ProductDTO getProduct(long productId) throws Exception {
        return productRepository.findById(productId).map(productMapper::productToProductDTO).orElse(null);
    }

    // 수정필요
    @Override
    public List<ProductDTO> getProductList(String tag, SearchDTO searchDTO) throws Exception {
        Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
        Pageable pageable = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getPageSize(), sort);
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
    public List<ProductDTO> getMyProductList(String tag, SearchDTO searchDTO) throws Exception {
        Sort sort = Sort.by(Sort.Direction.DESC, "regDate");
        Pageable pageable = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getPageSize(), sort);
        Page<Product> productPage;
        if(!searchDTO.isBuyer()){
            productPage = productRepository.findBySellerAndIsSold(Member.builder().tag(tag).build(), searchDTO.isSold(), pageable);
        }else{
            productPage = productRepository.findByBuyer(Member.builder().tag(tag).build(), pageable);
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
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null){
            throw new NullPointerException();
        }
        product.setSold(true);
        productRepository.save(product);
        log.info(product);
    }

    // false면 프리미엄이 종료, true면 프리미엄 지속
    @Override
    public boolean updatePremiumTime(long productId) throws Exception {
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null){
            throw new NullPointerException();
        }
        long premiumTime = product.getPremiumTime();
        if(product.getPremiumTime() == 0){
            productRepository.save(Product.builder()
                                     .productId(productId)
                                     .isPremium(false)
                                          .build());
            return false;
        }else{
            productRepository.save(Product.builder()
                                          .productId(productId)
                                          .premiumTime(premiumTime-1)
                                          .build());
            return true;
        }
    }
}
