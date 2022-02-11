package com.think.web.controller;

import com.think.web.dto.CommunityListResponseDto;
import com.think.web.dto.FileDto;
import com.think.web.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityRepository communityRepository;

    @GetMapping("/community")
    public String community(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false, defaultValue="") String searchText) {
        Page<CommunityListResponseDto> boards = communityRepository.findByTitleContainingOrContentContainingOrWdateContainingOrderByIdDesc(searchText, searchText, searchText,pageable);
        int startPage = Math.max(1,boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",boards);
        model.addAttribute("NowPage",boards.getPageable().getPageNumber());

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

    @GetMapping("/text")
    public String text() {
        return "community/text";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile[] uploadfile, Model model) throws IllegalStateException, IOException{
        List<FileDto> list = new ArrayList<>();
        for(MultipartFile file : uploadfile){
            FileDto dto = new FileDto(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());

            list.add(dto);

            File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
            file.transferTo(newFileName);
        }
        model.addAttribute("files", list);
        return "community/result";
    }

    @Value("${spring.servlet.multipart.location}")
    String filePath;

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@ModelAttribute FileDto dto) throws IOException{
        Path path = Paths.get(filePath + "/" + dto.getUuid() + "_" + dto.getFileName());
        String contentType = Files.probeContentType(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(dto.getFileName(), StandardCharsets.UTF_8).build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
