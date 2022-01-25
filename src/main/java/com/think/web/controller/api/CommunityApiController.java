package com.think.web.controller.api;

import com.think.web.dto.CommunityListResponseDto;
import com.think.web.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommunityApiController {
    @Autowired
    private CommunityRepository repository;


    @GetMapping("/board")
    List<CommunityListResponseDto> all(@RequestParam(required = false) String title) {
        if(StringUtils.isEmpty(title)){
            return repository.findAll();
        }else{
            return repository.findByTitle(title);
        }

    }


    @PostMapping("/board")
    CommunityListResponseDto newBoard(@RequestBody CommunityListResponseDto newBoard) {
        return repository.save(newBoard);
    }


    @GetMapping("/board/{id}")
    CommunityListResponseDto one(@PathVariable Long id) {

        return repository.findById(id).orElse(null);
    }

    @PutMapping("/board/{id}")
    CommunityListResponseDto replaceBoard(@RequestBody CommunityListResponseDto newBoard, @PathVariable Long id) {

        return repository.findById(id)
                .map(Board -> {
                    Board.setTitle(newBoard.getTitle());
                    Board.setContent(newBoard.getContent());
                    return repository.save(Board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/board/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
