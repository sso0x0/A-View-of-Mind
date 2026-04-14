package kr.ac.hywoman.park.exhibition.service;

import kr.ac.hywoman.park.exhibition.domain.Exhibition;
import kr.ac.hywoman.park.exhibition.repository.ExhibitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;

    // 메인 배너 전시
    public List<Exhibition> getBannerExhibitions() {
        List<Exhibition> list = exhibitionRepository.findByBannerTrue();
        System.out.println("배너 전시 개수 = " + list.size());
        return list;
    }

    // 진행 중 전시
    public List<Exhibition> getOngoingExhibitions() {
        List<Exhibition> list = exhibitionRepository.findByStatus("ONGOING");
        System.out.println("ONGOING 전시 개수 = " + list.size());
        return list;
    }

    // 카드 전시 (배너 X)
    public List<Exhibition> getCardExhibitions() {
        List<Exhibition> list = exhibitionRepository.findByBannerFalse();
        System.out.println("카드 전시 개수 = " + list.size());
        return list;
    }
}
