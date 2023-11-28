package kr.nadeuli.service.trade;

import kr.nadeuli.entity.TradeReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeReviewRepository extends JpaRepository<TradeReview, Long> {
}
