package kr.nadeuli.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import kr.nadeuli.common.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@ToString(exclude = {"blocks", "bankAccounts", "reports", "oriScheMemChatFavs",
    "sellerProducts", "buyerProducts", "tradeReviews", "nadeuliPayHistories", "posts",
    "comments", "nadeuliDeliveries"})
@Table(name="member")
public class Member {

  @Id
  @Column(name = "tag", length = 20, nullable = false)
  private String tag;

  @Column(name = "cellphone", length = 20, nullable = false)
  private String cellphone;

  @Column(name = "nickname", length = 40, nullable = false)
  private String nickname;

  @Column(name = "affinity", nullable = false, columnDefinition = "BIGINT DEFAULT 50")
  private Long affinity;

  @Column(name = "email")
  private String email;

  @Column(name = "dong_ne", nullable = false)
  private String dongNe;

  @Column(name = "picture", nullable = false)
  private String picture;

  @Column(name = "nadeuli_pay_balance", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
  private Long nadeuliPayBalance;

  @Column(name = "is_activate", nullable = false)
  private boolean isActivate;

  @Column(name = "is_nadeuli_delivery", nullable = false)
  private boolean isNadeuliDelivery;

  @Column(name = "role", nullable = false, columnDefinition = "BIGINT DEFAULT 1")
  private Role role;

  @Column(name = "gu", nullable = false)
  private String gu;

  @Column(name = "bank_name")
  private String bankName;

  @Column(name = "bank_account_num")
  private String bankAccountNum;

  @Column(name = "block_reason", length = 1000)
  private String blockReason;

  @Column(name = "block_end")
  private LocalDateTime blockEnd;

  @Column(name = "block_day")
  private Long blockDay;

  //정지
  @OneToMany(mappedBy = "blockMember", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Block> blocks;

  //계좌
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<BankAccount> bankAccounts;

  //신고
  @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Report> reports;

  //오리스케멤챗페브
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<OriScheMemChatFav> oriScheMemChatFavs;

  //상품
  @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Product> sellerProducts;

  @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Product> buyerProducts;

  //거래후기
  @OneToMany(mappedBy = "trader", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<TradeReview> tradeReviews;

  //나드리페이 내역
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<NadeuliPayHistory> nadeuliPayHistories;

  //게시물
  @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Post> posts;

  //댓글
  @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Comment> comments;

  //나드리 부름
  @OneToMany(mappedBy = "deliveryPerson", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<NadeuliDelivery> nadeuliDeliveries;

  @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<NadeuliDelivery> nadeulibuyers;

}
