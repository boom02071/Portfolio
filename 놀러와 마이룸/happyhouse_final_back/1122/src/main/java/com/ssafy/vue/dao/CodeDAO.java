package com.ssafy.vue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.DongCode;
import com.ssafy.vue.dto.GugunCode;
import com.ssafy.vue.dto.Heart;
import com.ssafy.vue.dto.Shop;
import com.ssafy.vue.dto.SidoCode;
@Mapper
public interface CodeDAO {
	public List<SidoCode> selectSido();
	public List<GugunCode> selectGugun(String sido);
	public List<DongCode> selectDongCode(String gugun);
	public int insertHeart(Heart room);
	public List<Heart> selectHeart(String userid);
	public int deleteHeart(int heartid);
	public List<Shop> selectShop(String shop);
	public int insertReview(Shop shop);
}