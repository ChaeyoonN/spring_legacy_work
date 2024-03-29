package com.spring.myweb.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.spring.myweb.freeboard.dto.response.FreeListResponseDTO;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponseDTO {
	//필드
	private String userName;
	private String userPhone1;
	private String userPhone2;
	private String userEmail1;
	private String userEmail2;
	private String addrBasic;
	private String addrDetail;
	private String addrZipNum;
	
	private List<FreeListResponseDTO> userBoardList;
	
	//메서드
	public static UserInfoResponseDTO toDTO(User user) {
		
		List<FreeListResponseDTO> list = new ArrayList<>();
		
		for(FreeBoard board : user.getUserBoardList()) {
			//FreeBoard로 이루어진 userBoardList에 대한 반복문
			list.add(new FreeListResponseDTO(board));
			//FreeListResponseDTO에 넣어서 FreeListResponseDTO로 이뤄진 리스트(list)에 넣기
		}
		
		return UserInfoResponseDTO.builder()
				.userName(user.getUserName())
				.userPhone1(user.getUserPhone1())
				.userPhone2(user.getUserPhone2())
				.userEmail1(user.getUserEmail1())
				.userEmail2(user.getUserEmail2())
				.addrBasic(user.getAddrBasic())
				.addrDetail(user.getAddrDetail())
				.addrZipNum(user.getAddrZipNum())
				.userBoardList(list) // FreeListResponseDTO로 이뤄진 리스트(list)를 
				.build(); 			// userBoardList로 세팅.
				
		
		
	}//toDTO메서드의 끝
	
}
