package com.think.web.dto;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
public class CommunityListResponseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @NotNull
    private String title; //게시글 제목

    @NotNull
    private String content; //게시글 본문

    @NotNull
    private String wdate; //글 작성 시간
}
