package kr.nadeuli.service.product;

import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
    Page<Product> findBySellerAndIsSold(Member seller, boolean isSold, Pageable pageable);
    Page<Product> findByBuyer(Member buyer, Pageable pageable);
}
