package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.DongCode;
import com.ssafy.vue.dto.GugunCode;
import com.ssafy.vue.dto.Heart;
import com.ssafy.vue.dto.Shop;
import com.ssafy.vue.dto.SidoCode;
import com.ssafy.vue.service.CodeService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/house")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CodeService codeService;

    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<SidoCode>> getSidoList() throws Exception {
		logger.debug("retrieveBoard - 호출");		
		return new ResponseEntity<List<SidoCode>>(codeService.selectSido(), HttpStatus.OK);
	}
    
    @ApiOperation(value = "모든 게시글의 정보를 반환한다2.", response = List.class)
	@GetMapping("/gugun/{gugun}")
	public ResponseEntity<List<GugunCode>> getGugunList(@PathVariable("gugun") String sido) throws Exception {
		logger.debug("retrieveBoard2 - 호출");		
		return new ResponseEntity<List<GugunCode>>(codeService.selectGugun(sido), HttpStatus.OK);
	}
    
    @ApiOperation(value = "모든 게시글의 정보를 반환한다3.", response = List.class)
   	@GetMapping("/dong/{dong}")
   	public ResponseEntity<List<DongCode>> getDongList(@PathVariable("dong") String gugun) throws Exception {
    	
   		logger.debug("retrieveBoard3 - 호출");		
   		return new ResponseEntity<List<DongCode>>(codeService.selectDongCode(gugun), HttpStatus.OK);
   	}

  @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Heart room) {
		logger.debug("writeBoard - 호출");
		if (codeService.insertHeart(room)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
  @ApiOperation(value = "관심목록 정보를 반환", response = List.class)
	@GetMapping("/heart/{heart}")
	public ResponseEntity<List<Heart>> getHeartList(@PathVariable("heart") String userid) throws Exception {
		logger.debug("관심목록 정보를 반환 - 호출");		
		return new ResponseEntity<List<Heart>>(codeService.selectHeart(userid), HttpStatus.OK);
	}
  
  @ApiOperation(value = "글번호에 해당하는 관심목록의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/heart/{heartid}")
	public ResponseEntity<String> deleteBoard(@PathVariable("heartid") int heartid) {
		logger.debug("deleteBoard - 호출");
		if (codeService.deleteHeart(heartid)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
  
  @ApiOperation(value = "음식점리뷰 정보를 반환", response = List.class)
 	@GetMapping("/food/{food}")
 	public ResponseEntity<List<Shop>> getShopList(@PathVariable("food") String shop) throws Exception {
 		logger.debug("리뷰 정보를 반환 - 호출");		
 		return new ResponseEntity<List<Shop>>(codeService.selectShop(shop), HttpStatus.OK);
 	}
  
  @ApiOperation(value = "음식점 리뷰 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
 	@PostMapping("/food")
 	public ResponseEntity<String> writeFood(@RequestBody Shop shop) {
 		logger.debug("writeFood - 호출");
 		if (codeService.insertReview(shop)==1) {
 			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
 		}
 		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
 	}
    
//    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = DongCode.class)    
//	@GetMapping("{gugun}")
//	public ResponseEntity<SidoCode> detailBoard(@PathVariable int gugun) {
//		logger.debug("detailBoard - 호출");
//		return new ResponseEntity<SidoCode>(boardService.detailBoard(no), HttpStatus.OK);
//	}

//    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PostMapping
//	public ResponseEntity<String> writeBoard(@RequestBody Sidocode board) {
//		logger.debug("writeBoard - 호출");
//		if (boardService.writeBoard(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
//
//    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PutMapping("{no}")
//	public ResponseEntity<String> updateBoard(@RequestBody Sidocode board) {
//		logger.debug("updateBoard - 호출");
//		logger.debug("" + board);
//		
//		if (boardService.updateBoard(board)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
//
//    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@DeleteMapping("{no}")
//	public ResponseEntity<String> deleteBoard(@PathVariable int no) {
//		logger.debug("deleteBoard - 호출");
//		if (boardService.deleteBoard(no)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
}