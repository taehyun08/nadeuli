package kr.nadeuli.controller;

import kr.nadeuli.dto.ProductDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductRestController {
    private final ProductService productService;

    @Value("${pageSize}")
    private int pageSize;

    // tag 받는 부분 확인 필요
    @GetMapping("/home/{currentPage}/{keyword}")
    public List<ProductDTO> getProductList(String tag, @PathVariable SearchDTO searchDTO) throws Exception {
        System.out.println("1231");
        searchDTO.setPageSize(pageSize);
        return productService.getProductList(tag, searchDTO);
    }

    @GetMapping("/getProduct/{productId}")
    public ProductDTO getProduct(Long productId) throws Exception {
        return productService.getProduct(productId);
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestBody ProductDTO productDTO) throws Exception {
        productService.updateProduct(productDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/addProduct")
    public String addProduct(@RequestBody ProductDTO productDTO) throws Exception {
        productService.addProduct(productDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/getMyProductList")
    public List<ProductDTO> getMyProductList(String tag, SearchDTO searchDTO) throws Exception {
        return productService.getMyProductList(tag, searchDTO);
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(Long productId) throws Exception {
        productService.deleteProduct(productId);
        return "{\"success\": true}";
    }

    @PostMapping("/saleCompleted")
    public String saleCompleted(Long productId) throws Exception {
        productService.saleCompleted(productId);
        return "{\"success\": true}";
    }

}
