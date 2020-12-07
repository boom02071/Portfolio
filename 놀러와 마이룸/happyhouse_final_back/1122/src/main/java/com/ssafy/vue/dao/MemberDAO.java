package com.ssafy.vue.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Member;

@Mapper
public interface MemberDAO {
	public Member selectMember(Member member);
	public int insertMember(Member member);
	public int deleteMember(String userid);
	public int updateMember(Member member);
}
