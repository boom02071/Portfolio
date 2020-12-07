package com.ssafy.vue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dao.MemberDAO;
import com.ssafy.vue.dto.Member;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	public MemberDAO memberdao;
	
	@Override
	public Member login(Member member) throws Exception {
		// TODO Auto-generated method stub
		Member selmem = memberdao.selectMember(member);
		System.out.println(selmem);
		return selmem;
	}

	@Transactional
	@Override
	public int regist(Member member) throws Exception {
		return memberdao.insertMember(member);
	}

	@Transactional
	@Override
	public int delete(String id) throws Exception {
		return memberdao.deleteMember(id);
	}

	@Transactional
	@Override
	public int update(Member member) throws Exception {
		return memberdao.updateMember(member);
	}
	
	

}
