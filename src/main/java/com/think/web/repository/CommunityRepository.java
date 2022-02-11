package com.think.web.repository;

import com.think.web.dto.CommunityListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CommunityRepository extends JpaRepository<CommunityListResponseDto, Long> {
    List<CommunityListResponseDto> findByTitle(String title);
    Page<CommunityListResponseDto> findByTitleContainingOrContentContainingOrWdateContainingOrderByIdDesc(String title, String content, String wdate, Pageable pageable);
}
