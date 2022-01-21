package com.think.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.think.entity.Members;
import com.think.web.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	@Override
	public List<Members> findAll() {
		return memberRepository.findAll();
	}
	
	@Override
	public List<Members> findByLimit(int n){
		return memberRepository.findByLimit(n);
	}

}
