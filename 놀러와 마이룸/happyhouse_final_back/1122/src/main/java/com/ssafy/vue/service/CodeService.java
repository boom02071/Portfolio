package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.DongCode;
import com.ssafy.vue.dto.GugunCode;
import com.ssafy.vue.dto.Heart;
import com.ssafy.vue.dto.Shop;
import com.ssafy.vue.dto.SidoCode;

public interface CodeService {
	public List<SidoCode> selectSido();
	public List<GugunCode> selectGugun(String sido);
	public List<DongCode> selectDongCode(String gugun);
	public boolean insertHeart(Heart room);
	public List<Heart> selectHeart(String userid);
	public int deleteHeart(int heartid);
	public List<Shop> selectShop(String shop);
	public int insertReview(Shop shop);
}
