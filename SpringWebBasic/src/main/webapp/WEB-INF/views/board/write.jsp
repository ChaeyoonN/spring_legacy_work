<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>게시글 등록</h2>
    <form method="post"> 
    <!-- 
    	form태그에 action이 생략되어 있는 경우에는 마지막 요청 url을 재활용합니다.
    	ex) 지금 보고 있는 게시글 요청 url -> /board/write: GET
    		작성 후 등록 버튼 클릭 -> action이 생략되어 있는 상태 -> 마지막 요청인 /board/write: POST
     -->
        <p>
            # 작성자: <input type="text" name="writer"> <br>
            # 제목: <input type="text" name="title"> <br>
            # 내용: <textarea rows="3" name="content"></textarea> <br>
            <input type="submit" value="등록"> 
        </p>
    </form>

</body>
</html>