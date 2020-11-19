package kr.co.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Controller 어노테이션
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//시작 페이지 mapping 변경
	@RequestMapping(value = "/")
	public String main(Model model) {
		logger.info("main");
		
		//model : 데이터를 담는 그릇 역활, map 구조로 저장된다
		//model.addAttribute("변수명", 값);
		model.addAttribute("msg", "홈페이지 방문을 환영합니다.");
		
		//main.jsp 포워딩
		return "main";
	}
	
	//URL 맵핑
	//기본, 루트 페이지 => home 메서드 호출
	@RequestMapping(value = "home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//모델(서블릿의 request 객체를 데체한 것)
		model.addAttribute("serverTime", formattedDate);
		
		// home.jsp로 포워딩
		// => servlet-context.xml
		// <beans:property name="prefix" value="/WEB-INF/views/" />
		// <beans:property name="suffix" value=".jsp" />
		//디렉토리(접투어)와 jsp(접미어)확장자를 제외하고 이름만 작성하도록 세팅
		return "home";
	}
	
}
