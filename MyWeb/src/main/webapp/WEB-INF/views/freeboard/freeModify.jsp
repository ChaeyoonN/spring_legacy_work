<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../include/header.jsp" %>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>수정하기</p>
                        </div>
                        
                        <form action="${pageContext.request.contextPath}/freeboard/modify" method="post" name="updateForm">   
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name="bno" value="${board.bno}" readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name='writer' value="${board.writer}" readonly>
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name='title' value="${board.title}">
                            </div>

                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='content'>${board.content}</textarea>
                            </div>

                            <button id="list-btn" type="button" class="btn btn-dark">목록</button>    
                            <button id="update-btn" type="button" class="btn btn-primary">변경</button>
                            <button id="del-btn" type="button" class="btn btn-info">삭제</button>
                    </form>
                                    
                </div>
            </div>
        </div>
        </section>
        
       	<%@ include file="../include/footer.jsp" %>

        <script>
            //목록 이동 처리
            document.getElementById('list-btn').onclick = function () {
                location.href = '${pageContext.request.contextPath}/freeboard/freeList';
            }

            //폼 태그는 메서드 없이 form 태그의 name으로 요소를 바로 취득할 수 있습니다.
            const $form = document.updateForm;
            //수정 버튼 이벤트
            document.getElementById('update-btn').onclick = function () {
                //form 내부의 요소를 지목할 땐 name 속성으로 바로 지목이 가능합니다. 폼태그.이름.값
                if($form.title.value === ''){
                    alert('제목은 필수 항목입니다.');
                    return;
                } else if($form.content.value === ''){
                    alert('내용을 뭐라도 작성해 주세요!');
                    return;
                }

                //문제가 없다면 form을 submit
                $form.submit();
            }

            //삭제 버튼 이벤트 처리
            document.getElementById('del-btn').onclick = () => {
                if(confirm('정말 삭제하시겠습니까?')){ //확인(누르면 true 리턴)과 취소(누르면 false 리턴)가 있는 알림창
                    $form.setAttribute('action', 'myweb/freeboard/delete');//폼태그의 속성 바꾸기: setAttribute(바꾸고자하는속성이름, 내용)
                    // jQuery로는 attr이라는 메서드 쓴다.

                    $form.submit();
                }
            }
            

        </script>
      