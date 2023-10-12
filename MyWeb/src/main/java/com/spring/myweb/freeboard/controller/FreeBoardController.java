package com.spring.myweb.freeboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.myweb.freeboard.dto.FreeContentDTO;
import com.spring.myweb.freeboard.dto.FreeModifyRequestDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.service.IFreeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class FreeBoardController {
	
	private final IFreeBoardService service;
	
	//목록 확인
	@GetMapping("/freeList")
	public void freeList(Model model) {
		System.out.println("/freeboard/freeList: GET!");
		
		model.addAttribute("boardList", service.getList()); //void이므로 freeList.jsp로 전달
	}
	
	//글쓰기 페이지를 열어주는 메서드
	@GetMapping("/freeRegist")
	public void regist() {
		
	}
	
	//글 등록 처리
	@PostMapping("/freeRegist")
	public String regist(FreeRegistRequestDTO dto) {
		service.regist(dto);
		return "redirect:/freeboard/freeList";
	}
	
	//글 상세 보기
	@GetMapping("/content") //"3번 글 상세 보기 요청을 넣으면 "컨트롤러는 DB에서 가지고 온 글 객체를 
	//"model에 담아 freeDetail.jsp로 이동시킬 것이다."
	public String getContent(@RequestParam("bno") int bno, Model model) {
		System.out.println("/freeboard/content: GET!");
		FreeContentDTO dto = service.getContent(bno);
		model.addAttribute("board", dto);
		
		return "freeboard/freeDetail"; // 폴더명/파일명
	}
	
	//글 수정 페이지 이동 요청
	@PostMapping("/modPage")
	public String modPage(@ModelAttribute("board") FreeModifyRequestDTO dto) {
		System.out.println("/freeboard/modPage: POST!");
		return "freeboard/freeModify";
	}
	
	//글 수정
	@PostMapping("/modify")
	public String modify(FreeModifyRequestDTO dto) {
		System.out.println("/freeboard/modify: POST!");
		service.update(dto);
		
		return "redirect:/freeboard/content?bno=" + dto.getBno();//파라미터변수명(bno)은 리퀘스트파람변수명과 같아야
	}
	
	//글 삭제
	@PostMapping("/delete") 
	public String delete(int bno) {
		System.out.println("/freeboard/delete: GET!");
		service.delete(bno);
		
		return "redirect:/freeboard/freeList";
	}
	
}