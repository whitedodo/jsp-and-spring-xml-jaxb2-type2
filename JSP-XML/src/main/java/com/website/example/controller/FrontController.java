package com.website.example.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.website.example.vo.BoardListVO;
import com.website.example.vo.BoardVO;


public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/xml;charset=utf-8");
		 
		String result = setXmlData();
		PrintWriter out = res.getWriter();
		
		out.println(result);
		
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}
	
	public String setXmlData(){ 
		
		JAXBContext jc = null;
		Marshaller marshaller = null;
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		BoardListVO boardListVO = new BoardListVO();
		
		OutputStream os = new ByteArrayOutputStream();

		BoardVO vo = new BoardVO();
		
		try {
			jc = JAXBContext.newInstance(BoardListVO.class); 
			
		} catch (JAXBException e) {
			e.printStackTrace(); 
		}

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

		boardListVO.setBoardList(boardList);
		
		try {
			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// marshaller.marshal(boardListVO, System.out);
			marshaller.marshal(boardListVO, os);
			
		} catch (JAXBException e) {
			e.printStackTrace(); 
			
		}
		
		// System.out.println("XML출력:" + os.toString());
		
		return os.toString();
		
	}

}
