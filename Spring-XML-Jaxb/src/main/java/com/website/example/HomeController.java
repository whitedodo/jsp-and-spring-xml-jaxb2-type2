package com.website.example;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.website.example.vo.BoardListVO;
import com.website.example.vo.BoardVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/dataTransform", produces="application/xml")
	@ResponseBody
	public BoardListVO dataTransform(Locale locale, Model model) {
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		BoardVO vo = new BoardVO();
		vo.setId(1);
		vo.setTitle("야야야1");
		vo.setSearchCondition("하후");
		vo.setWriter("홍길동");
		vo.setRegDate(java.sql.Date.valueOf("2010-02-01"));
		
		boardList.add(vo);
		
		vo = new BoardVO();
		vo.setId(2);
		vo.setTitle("야야야2");
		vo.setSearchCondition("하후");
		vo.setWriter("홍길동");
		vo.setRegDate(java.sql.Date.valueOf("2010-03-01"));
		boardList.add(vo);
		
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardList(boardList);
		
		System.out.println("가동중");
		
		return boardListVO;
	}
	
}
