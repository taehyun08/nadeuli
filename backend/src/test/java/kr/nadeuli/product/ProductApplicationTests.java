package kr.nadeuli.product;

import kr.nadeuli.dto.ProductDTO;
import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.Product;
import kr.nadeuli.service.product.ProductService;
import kr.nadeuli.service.product.impl.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductApplicationTests {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testAddProduct() throws Exception{
        List<String> imageList = new ArrayList<>();
        imageList.add("당근사진.jpg");
        imageList.add("무슨사진.png");
        imageList.add("사진사진.jpg");
        ProductDTO productDTO = ProductDTO.builder()
                .productId(123L)
                .title("상품제목목")
                .price(12340L)
                .isPremium(true)
                .premiumTime(3L)
                .content("싸게 팔아요")
                .images(imageList)
                .video("난비디오.avi")
                .viewNum(0L)
                .tradingLocation("나는거래장소")
                .gu("강남구")
                .buyer(Member.builder().tag("WVU3").build())
                .build();
        System.out.println(productDTO);

        productService.addProduct(productDTO);
    }
}
