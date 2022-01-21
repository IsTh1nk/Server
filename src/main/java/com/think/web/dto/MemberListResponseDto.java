package com.think.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
/* data transfer object */
public class MemberListResponseDto {
	private final long id;
	private final String profileName;
	private final String profileIntro;
	private final String profileImage;
	private final String role;
}
