package com.spring.myweb.freeboard.service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.border.TitledBorder;

import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.FreeContentDTO;
import com.spring.myweb.freeboard.dto.FreeListResponseDTO;
import com.spring.myweb.freeboard.dto.FreeModifyRequestDTO;
import com.spring.myweb.freeboard.dto.FreeRegistRequestDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FreeBoardService implements IFreeBoardService {
	
	private final IFreeBoardMapper mapper;
	

	@Override
	public void regist(FreeRegistRequestDTO dto) {
		mapper.regist(FreeBoard.builder()
						.title(dto.getTitle())
						.content(dto.getContent())
						.writer(dto.getWriter())
						.build());

	}

	@Override
	public List<FreeListResponseDTO> getList() {
		List<FreeListResponseDTO> dtoList = new ArrayList<>();
		List<FreeBoard> list = mapper.getList();
		
		for(FreeBoard board : list) {
			dtoList.add(new FreeListResponseDTO(board));
		}
		
		return dtoList;
	}

	@Override
	public FreeContentDTO getContent(int bno) {
		FreeBoard board = mapper.getContent(bno);
		
		FreeContentDTO dto = new FreeContentDTO(board);
		
		return dto;
		
	}

	@Override
	public void update(FreeModifyRequestDTO dto) {
		
//		mapper.update(mapper.getContent(dto.getBno()));
		mapper.update(
						FreeBoard.builder()
						.bno(dto.getBno())
						.title(dto.getTitle())
						.content(dto.getContent())
						.build()
						);

	}

	@Override
	public void delete(int bno) {
		mapper.delete(bno);

	}

}