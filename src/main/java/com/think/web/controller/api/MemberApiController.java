package com.think.web.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.think.entity.Members;
import com.think.web.service.MemberService;

@RestController
public class MemberApiController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/api/members")
	public List<Members> members(HttpServletRequest request){
		String num = request.getParameter("num");
		if(num.equals("all")) {
			return memberService.findAll();
		}
		
		return memberService.findByLimit(Integer.parseInt(num));
	}
}
