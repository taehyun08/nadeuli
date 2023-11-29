package kr.nadeuli.nadeuli_pay;

import kr.nadeuli.category.TradeType;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.service.nadeuli_pay.NadeuliPayHistoryRepository;
import kr.nadeuli.service.nadeuli_pay.NadeuliPayService;
import kr.nadeuli.service.product.ProductRepository;
import kr.nadeuli.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class NadeuliPayApplicationTests {
    @Autowired
    private NadeuliPayService nadeuliPayService;

    @Autowired
    private NadeuliPayHistoryRepository nadeuliPayHistoryRepository;

    @Value("${pageSize}")
    private int pageSize;

    @Test
    @Transactional
    public void testGetNadeuliPayList(){
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setCurrentPage(0);
        searchDTO.setPageSize(pageSize);
        TradeType tradeType = TradeType.CHARGE;
        String tag = "WVU3";
//        System.out.println(nadeuliPayService.getNadeuliPayList(tag, tradeType, searchDTO));
        System.out.println(nadeuliPayService.getNadeuliPayList(tag, null, searchDTO));
    }

//    @Test
//    public void testNadeuliPayPay(){
//        nadeuliPayService.nadeuliPayPay()
//    }
//
//    @Test
//    public void testNadeuliPayCharge(){
//        nadeuliPayService.nadeuliPayCharge();
//    }
//
//    @Test
//    public void testNadeuliPayWithdraw(){
//        nadeuliPayService.nadeuliPayWithdraw();
//    }
}
