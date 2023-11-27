package kr.nadeuli.common;

import java.time.Duration;
import java.time.LocalDateTime;

public class CalculateTimeAgo {

    public static long calculateTimeDifferenceInMinutes(LocalDateTime pastTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(pastTime, currentTime);
        return duration.toMinutes();
    }

}