package kr.co.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.vo.ProductVO;

//컨트롤러 bean으로 로딩 (현재 클래스를 컨트롤러로 메모리에 로딩시켜준다)
@Controller
public class SampleController {
	
	//로그 객체 생성
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	//URL pattern
	@RequestMapping("sample/doA")
	public String doA(Model model) {
		
		//public void : 리턴타입이 없는 void일 경우, mapping된 URL pattern 이름과 동일한 jsp로 포워드 한다.
		
		logger.info("doA");
		
		//model.addAttribute(key, value);
		//map과 같이 key, value로 구성되어 있다.
		model.addAttribute("message", "홈페이지 방문을 환영합니다.");
		
		//리턴타입이 없s는 void일 경우 doA.jsp로 포워드된다.
		//리턴타입을 String으로 했으므로 doB로 지정하여 포워드한다.
		return "sample/doA";
	}
	
	@RequestMapping("sample/doB")
	public void doB() {
		logger.info("doB");
	}
	
	//ModelAndView : model 데이터 저장소, View화면
	//데이터와 포워드할 페이지의 정보
	//forward : 주소변경X, 화면전화, 대량의 데이터 전달
	//redirect : 주소변경O, 화면전화, 소량의 데이터 전달(GET방식만 가능)
	
	//URL pattern
	@RequestMapping("sample/doC")
	public ModelAndView doC() {
		logger.info("ModelAndView");
		
		//메서드가 종료된 후에 doC,jsp로 포워드
		//맵에 객체를 저장
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", new ProductVO("연필", 1000));
		
		return new ModelAndView("sample/doC", "map", map);
	}
	
	@RequestMapping("sample/doD")
	public String doD() {
		logger.info("doD");
		
		//redirect의 경우, return type을 String으로 설정
		//doE.jsp로 리다이렉트
		
		//return "sample/doE";
		return "redirect:/sample/doE";
	}
	
	@RequestMapping("sample/doE")
	public void doE() {
		logger.info("doE");
		
		//doE.jsp 포워드
	}

}
