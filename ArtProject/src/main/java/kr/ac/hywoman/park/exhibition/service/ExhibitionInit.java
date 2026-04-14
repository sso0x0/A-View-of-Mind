package kr.ac.hywoman.park.exhibition.service;

import kr.ac.hywoman.park.exhibition.domain.Exhibition;
import kr.ac.hywoman.park.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class ExhibitionInit {

    private final ExhibitionRepository exhibitionRepository;

    private void saveIfNotExists(Exhibition exhibition) {
        if (!exhibitionRepository.existsByTitle(exhibition.getTitle())) {
            exhibitionRepository.save(exhibition);
        }
    }

    @Bean
    CommandLineRunner initExhibitions() {
        return args -> {

            /* ===============================
               배너 전시
            =============================== */

            saveIfNotExists(Exhibition.builder()
                    .title("이불: 나의 위대한 서사")
                    .summary("한국 현대미술을 대표하는 이불 작가의 대규모 서베이")
                    .description("1990년대 이후 이불의 주요 작품 세계를 총망라한 전시")
                    .startDate(LocalDate.of(2025, 9, 4))
                    .endDate(LocalDate.of(2026, 1, 4))
                    .venueName("리움미술관")
                    .address("서울 용산구")
                    .status("ONGOING")
                    .banner(true)
                    .thumbnailUrl("https://images.unsplash.com/photo-1549880338-65ddcdfd017b")
                    .externalUrl("https://www.leeumhoam.org")
                    .build());

            saveIfNotExists(Exhibition.builder()
                    .title("올해의 작가상 2025")
                    .summary("동시대 한국미술을 대표하는 작가전")
                    .description("국립현대미술관이 선정한 올해의 작가상 후보 전시")
                    .startDate(LocalDate.of(2025, 8, 29))
                    .endDate(LocalDate.of(2026, 2, 1))
                    .venueName("국립현대미술관 서울")
                    .address("서울 종로구")
                    .status("ONGOING")
                    .banner(true)
                    .thumbnailUrl("https://images.unsplash.com/photo-1529107386315-e1a2ed48a620")
                    .externalUrl("https://www.mmca.go.kr")
                    .build());

            saveIfNotExists(Exhibition.builder()
                    .title("장 미셸 바스키아")
                    .summary("과거와 미래를 잇는 상징적 기호들")
                    .description("바스키아 회고전")
                    .startDate(LocalDate.of(2025, 9, 23))
                    .endDate(LocalDate.of(2026, 1, 31))
                    .venueName("DDP 전시1관")
                    .address("서울 중구")
                    .status("ONGOING")
                    .banner(true)
                    .thumbnailUrl("https://images.unsplash.com/photo-1536924940846-227afb31e2a5")
                    .externalUrl("https://ddp.or.kr")
                    .build());

            /* ===============================
               진행 중 전시
            =============================== */

            saveIfNotExists(Exhibition.builder()
                    .title("전국광: 쌓는 친구, 허무는 친구")
                    .summary("한국 추상조각의 실험적 여정")
                    .description("조형 언어의 실험")
                    .startDate(LocalDate.of(2025, 9, 24))
                    .endDate(LocalDate.of(2026, 2, 22))
                    .venueName("서울시립 남서울미술관")
                    .address("서울 관악구")
                    .status("ONGOING")
                    .banner(false)
                    .thumbnailUrl("https://images.unsplash.com/photo-1581090700227-1e37b190418e")
                    .externalUrl("https://sema.seoul.go.kr")
                    .build());

            saveIfNotExists(Exhibition.builder()
                    .title("타이틀 매치")
                    .summary("미디어와 퍼포먼스의 충돌")
                    .description("서울시립미술관 연례 기획전")
                    .startDate(LocalDate.of(2025, 10, 1))
                    .endDate(LocalDate.of(2026, 1, 12))
                    .venueName("서울시립미술관 서소문본관")
                    .address("서울 중구")
                    .status("ONGOING")
                    .banner(false)
                    .thumbnailUrl("https://images.unsplash.com/photo-1518770660439-4636190af475")
                    .externalUrl("https://sema.seoul.go.kr")
                    .build());

            saveIfNotExists(Exhibition.builder()
                    .title("히무로 유리: 오늘의 기쁨")
                    .summary("일상 속 감각을 담은 설치미술")
                    .description("그라운드시소 개인전")
                    .startDate(LocalDate.of(2025, 10, 3))
                    .endDate(LocalDate.of(2026, 3, 29))
                    .venueName("그라운드시소 한남")
                    .address("서울 용산구")
                    .status("ONGOING")
                    .banner(false)
                    .thumbnailUrl("https://images.unsplash.com/photo-1500530855697-b586d89ba3ee")
                    .externalUrl("https://groundseesaw.co.kr")
                    .build());

            /* ===============================
               진행 예정 전시
            =============================== */

            saveIfNotExists(Exhibition.builder()
                    .title("소멸의 시학")
                    .summary("삭는 미술에 대하여")
                    .description("시간성과 물질")
                    .startDate(LocalDate.of(2026, 1, 30))
                    .endDate(LocalDate.of(2026, 5, 3))
                    .venueName("국립현대미술관 서울")
                    .address("서울 종로구")
                    .status("UPCOMING")
                    .banner(false)
                    .thumbnailUrl("https://images.unsplash.com/photo-1517694712202-14dd9538aa97")
                    .externalUrl("https://www.mmca.go.kr")
                    .build());

            saveIfNotExists(Exhibition.builder()
                    .title("룸 포 원더")
                    .summary("상상의 문을 열다")
                    .description("몰입형 체험 전시")
                    .startDate(LocalDate.of(2025, 12, 19))
                    .endDate(LocalDate.of(2026, 6, 7))
                    .venueName("그라운드시소 센트럴")
                    .address("서울 중구")
                    .status("UPCOMING")
                    .banner(false)
                    .thumbnailUrl("https://images.unsplash.com/photo-1492724441997-5dc865305da7")
                    .externalUrl("https://groundseesaw.co.kr")
                    .build());

            saveIfNotExists(Exhibition.builder()
                    .title("MMCA 영상미디어 특별전")
                    .summary("동시대 영상 예술")
                    .description("영상 매체 집중 조명")
                    .startDate(LocalDate.of(2025, 11, 26))
                    .endDate(LocalDate.of(2026, 1, 10))
                    .venueName("국립현대미술관 서울")
                    .address("서울 종로구")
                    .status("UPCOMING")
                    .banner(false)
                    .thumbnailUrl("https://images.unsplash.com/photo-1518779578993-ec3579fee39f")
                    .externalUrl("https://www.mmca.go.kr")
                    .build());

            /* ===============================
               마감 임박 전시
            =============================== */

            saveIfNotExists(Exhibition.builder()
                    .title("서울미디어시티비엔날레 사전전")
                    .summary("미디어와 도시")
                    .description("비엔날레 프리뷰")
                    .startDate(LocalDate.of(2025, 8, 1))
                    .endDate(LocalDate.of(2025, 9, 30))
                    .venueName("서울시립미술관")
                    .address("서울 중구")
                    .status("CLOSING")
                    .banner(false)
                    .thumbnailUrl("https://images.unsplash.com/photo-1495567720989-cebdbdd97913")
                    .externalUrl("https://sema.seoul.go.kr")
                    .build());

            saveIfNotExists(Exhibition.builder()
                    .title("사진의 자리")
                    .summary("동시대 사진 예술")
                    .description("사진 매체 탐구")
                    .startDate(LocalDate.of(2025, 7, 15))
                    .endDate(LocalDate.of(2025, 9, 20))
                    .venueName("서울시립 북서울미술관")
                    .address("서울 노원구")
                    .status("CLOSING")
                    .banner(false)
                    .thumbnailUrl("https://images.unsplash.com/photo-1481349518771-20055b2a7b24")
                    .externalUrl("https://sema.seoul.go.kr")
                    .build());

            saveIfNotExists(Exhibition.builder()
                    .title("도시의 기록")
                    .summary("서울의 변화와 기억")
                    .description("도시 아카이브")
                    .startDate(LocalDate.of(2025, 6, 1))
                    .endDate(LocalDate.of(2025, 9, 15))
                    .venueName("서울역사박물관")
                    .address("서울 종로구")
                    .status("CLOSING")
                    .banner(false)
                    .thumbnailUrl("https://images.unsplash.com/photo-1500534314209-a25ddb2bd429")
                    .externalUrl("https://museum.seoul.go.kr")
                    .build());
        };
    }
}
