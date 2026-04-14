package kr.ac.hywoman.park.exhibition.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;          // 전시명
    private String summary;        // 한 줄 소개
    private String description;    // 상세 설명

    private LocalDate startDate;
    private LocalDate endDate;

    private String venueName;      // 전시장명
    private String address;

    private String status;         // ONGOING, UPCOMING, CLOSING
    private String thumbnailUrl;
    
    private String externalUrl;   // 전시 공식 사이트 / 예매 페이지


    private boolean banner;        // 메인 배너 여부
}
