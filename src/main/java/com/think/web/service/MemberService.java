package com.think.web.service;

import java.util.List;

import com.think.entity.Members;

public interface MemberService {
	
	public List<Members> findAll();
	public List<Members> findByLimit(int n);
}
