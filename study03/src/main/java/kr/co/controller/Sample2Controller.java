package kr.co.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.vo.ProductVO;

//ajax 처리 전용 컨트롤러 (백그라운드 실행)
//스프링 4.0 버전 이상부터 사용가능
@RestController
public class Sample2Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Sample2Controller.class);
	
	//@ResponseBody json형식으로 데이터를 리턴
	@ResponseBody
	@RequestMapping("sample/doF")
	public ProductVO doF() {
		logger.info("doF");
		
		return new ProductVO("스마트폰", 90000);
	}

}
