package kr.nadeuli.service.trade;

import kr.nadeuli.entity.TradeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeScheduleRepository extends JpaRepository<TradeSchedule, Long> {

}
