package com.ssafy.vue.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dao.CodeDAO;
import com.ssafy.vue.dto.DongCode;
import com.ssafy.vue.dto.GugunCode;
import com.ssafy.vue.dto.Heart;
import com.ssafy.vue.dto.Shop;
import com.ssafy.vue.dto.SidoCode;

@Service
public class CodeServiceImpl implements CodeService {
	
    @Autowired
	private CodeDAO codeDao;

//    @Override
//	public List<Sidocode> retrieveBoard() {
//		return boardDao.selectBoard();
//	}
//    
//  	@Override
//	public boolean writeBoard(Sidocode board) {
//		return boardDao.insertBoard(board) == 1;
//	}
//
//	@Override
//	public Sidocode detailBoard(int no) {
//		return boardDao.selectBoardByNo(no);
//	}
//
//	@Override
//	@Transactional
//	public boolean updateBoard(Sidocode board) {
//		return boardDao.updateBoard(board) == 1;
//	}
//
//	@Override
//	@Transactional
//	public boolean deleteBoard(int no) {
//		return boardDao.deleteBoard(no) == 1;
//	}

	
	@Override
	public List<SidoCode> selectSido() {
		// TODO Auto-generated method stub
		return codeDao.selectSido();
	}

	@Override
	public List<GugunCode> selectGugun(String sido) {
		// TODO Auto-generated method stub
		List<GugunCode> list = codeDao.selectGugun(sido);
		List<GugunCode> result = new ArrayList<GugunCode>();
		
		for(GugunCode i : list) {
			if(i.getSidoCode().equals(sido))
				result.add(i);
		}
		return result;
	}

	@Override
	public List<DongCode> selectDongCode(String gugun) {
		// TODO Auto-generated method stub
		List<DongCode> list = codeDao.selectDongCode(gugun);
		List<DongCode> result = new ArrayList<>();
		for(DongCode i : list) {
			if(i.getCode().equals(gugun))
				result.add(i);
		}
		return result;
	}

	@Override
	@Transactional
	public boolean insertHeart(Heart room) {
		// TODO Auto-generated method stub
		return codeDao.insertHeart(room)==1;
	}
	
	@Override
	public List<Heart> selectHeart(String userid) {
		// TODO Auto-generated method stub
		return codeDao.selectHeart(userid);
	}

	@Override
	@Transactional
	public int deleteHeart(int heartid) {
		return codeDao.deleteHeart(heartid);
	}
	
	@Override
	public List<Shop> selectShop(String shop) {
		// TODO Auto-generated method stub
		return codeDao.selectShop(shop);
	}

	@Override
	@Transactional
	public int insertReview(Shop shop) {
		// TODO Auto-generated method stub
		return codeDao.insertReview(shop);
	}
	
}