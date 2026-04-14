package kr.ac.hywoman.park.exhibition.repository;

import kr.ac.hywoman.park.exhibition.domain.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

    List<Exhibition> findByBannerTrue();

    List<Exhibition> findByStatus(String status);
    
    List<Exhibition> findByBannerFalse();
    
    // 전시 제목 기준 중복 방지용
    boolean existsByTitle(String title);

}
