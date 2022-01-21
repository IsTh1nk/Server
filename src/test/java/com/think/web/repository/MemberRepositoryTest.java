package com.think.web.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.think.entity.Members;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void 모든_멤버_조회() {
		List<Members> memberList = memberRepository.findAll();
		
		for(Members member : memberList) {
			System.out.println(member.toString());
		}
	}
	
	@Test
	public void 특정_멤버_조회() {
	}
	
}
