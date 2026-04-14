package kr.ac.hywoman.park.exhibition.controller;

import kr.ac.hywoman.park.exhibition.service.ExhibitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    @GetMapping("/main")
    public String main(Model model) {

        model.addAttribute("bannerExhibitions",
                exhibitionService.getBannerExhibitions());

        model.addAttribute("cardExhibitions",
                exhibitionService.getCardExhibitions());

        return "main";
    }
}
