package kr.nadeuli.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlockDTO {

  private Long id;
  private String blockReason;
  private LocalDateTime blockEnd;
  private Long blockDay;

}
