package com.ssafy.vue.service;

import com.ssafy.vue.dto.Member;

public interface MemberService {
	
	public Member login(Member memberDto) throws Exception;
	public int regist(Member memberDto) throws Exception;
	public int delete(String id) throws Exception;
	public int update(Member member) throws Exception;
}
