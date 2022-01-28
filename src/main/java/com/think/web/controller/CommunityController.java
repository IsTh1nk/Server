package com.think.web.controller;

import com.think.web.dto.CommunityListResponseDto;
import com.think.web.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;



@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityRepository communityRepository;

    @GetMapping("/community")
    public String community(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false, defaultValue="") String searchText) {
        Page<CommunityListResponseDto> boards = communityRepository.findByTitleContainingOrContentContainingOrWdateContaining(searchText, searchText, searchText,pageable);
        int startPage = Math.max(1,boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",boards);


        return "community/community";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
        if(id == null){
            model.addAttribute("board", new CommunityListResponseDto());
        }else{
            CommunityListResponseDto board = communityRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return "community/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@Valid CommunityListResponseDto communityListResponseDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "community/form";
        }
        communityRepository.save(communityListResponseDto);
        return "redirect:/community/community";
    }
}
