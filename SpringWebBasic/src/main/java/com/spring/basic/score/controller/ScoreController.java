package com.spring.basic.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.basic.score.dto.ScoreListResponseDTO;
import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.service.ScoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor //:final 필드가 존재한다면 그것만을 초기화해주는 생성자.
public class ScoreController {
	
	private final ScoreService service;
	
	//만약에 클래스의 생성자가 단 1개라면
	//자동으로 @Autowired를 작성해 줌.
	
//	@Autowired 
	//등록된 빈들 중 ScoreService타입을 생성자 매개변수에 주입
//	public ScoreController(ScoreService scoreService) {
//		service = scoreService;
		//위에 선언한 서비스객체 = 매개변수로받은객체;
//	}
	

	//1. 성적 등록 화면 띄우기 + 정보 목록 조회
	@GetMapping("/list")
	public String list(Model model) {
		List<ScoreListResponseDTO> dtoList = service.getList();
		model.addAttribute("sList", dtoList);
		return "score/score-list";
	}
	
	//2. 성적 정보 등록 처리 요청.
	@PostMapping("/register")
	public String register(ScoreRequestDTO dto) {
		//객체로받자-->용도에 따라 각각 클래스 만들자
		//단순 입력 데이터 읽기
		System.out.println("/score/register: POST! - " + dto);
		
		//서비스한테 일 시켜야지
		service.insertScore(dto);
		
		/*
		 등록 요청이 완료되었다면, 목록을 불러오는 로직을 여기다 작성하는 것이 아닌, 
		 갱신된 목록을 불러오는 요청이 다시금 들어올 수 있도록 유도를 하자 -> sendRedirect()
		 
		 "redirect:[URL]"을 작성하면 내가 지정한 URL로 자동 재 요청이 일어나면서
		 미리 준비해 둔 로직을 수행할 수 있다.
		 점수 등록 완료 -> 목록을 달라는 요청으로 유도 -> 목록 응답.
		 */
		return "redirect:/score/list"; 
	}
	
	//3. 성적 정보 상세 조회 요청
	@GetMapping("/detail")
	public String detail(@RequestParam("stuNum") int stuNum, Model model) {
		System.out.println("/score/detail: GET!");
		
		retrieve(stuNum, model);
		
		return "score/score-detail";
	}
	
	//4. 성적 정보 삭제 요청
	@GetMapping("/remove")
	public String remove(int stuNum) {
		System.out.println("/score/remove: GET!");
		System.out.println("요청과 함께 넘어온 번호: "+stuNum);
		
		service.delete(stuNum);
		
		return "redirect:/score/list";
	}
	
	//5. 수정 화면 열어주기
	@GetMapping("/modify")
	public String modify(int stuNum, Model model) {
		retrieve(stuNum, model);
		
		return "score/score-modify";
	}
	
	//상세보기, 수정화면 공통 로직을 메서드화
	private void retrieve(int stuNum, Model model) {
		Score score = service.retrieve(stuNum);
		model.addAttribute("s", score);
	}
	//6. 수정완료 요청
	@PostMapping("/modify")
	public String modify(int stuNum, ScoreRequestDTO dto) {
		//학번과 수정입력한 점수들 매개변수로 받기
		
		System.out.println("/score/modify: POST!");
		
		service.modify(stuNum, dto);
		
		return "redirect:/score/detail?stuNum="+stuNum;
	}
	
}
