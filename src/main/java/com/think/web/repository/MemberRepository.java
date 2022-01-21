package com.think.web.repository;

import java.util.List;

import com.think.entity.Members;

public interface MemberRepository {
	public int save(Members member);
	public List<Members> findAll();
	public List<Members> findByLimit(int n);
	public Members findById(int memberId);
}
