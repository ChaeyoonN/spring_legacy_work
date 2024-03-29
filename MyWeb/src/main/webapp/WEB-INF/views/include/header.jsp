<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta
      http-equiv="X-UA-Compatible"
      content="IE=edge"
    />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1"
    />
    <title>인덱스를 만들어 보자</title>

    <link
      href="${pageContext.request.contextPath }/css/bootstrap.css"
      rel="stylesheet"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!--개인 디자인 추가-->
    <link
      href="${pageContext.request.contextPath }/css/style.css"
      rel="stylesheet"
    />
    <script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
  </head>
  <body>
    <header>
      <div class="container-fluid">
        <div class="row">
          <div class="container">
            <div class="navbar">
              <div class="navbar-header">
                <!--data-toggle 같은 것들은 내부적으로 지원하는 반응형 API기능이므로 지우면 안됩니다-->
                <button
                  type="button"
                  class="navbar-toggle"
                  data-toggle="collapse"
                  data-target="#myNavbar"
                >
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <a
                  class="navbar-brand"
                  href="#"
                  ><img
                    width="30"
                    src="${pageContext.request.contextPath }/img/logo.svg"
                    alt="Brand"
                /></a>
              </div>

              <div
                class="collapse navbar-collapse"
                id="myNavbar"
              >
                <ul class="nav navbar-nav">
                  <li class="active"><a href="<c:url value='/' />">Main</a></li>
                  <li>
                    <a
                      href="${pageContext.request.contextPath}/freeboard/freeList"
                      >자유게시판</a
                    >
                  </li>
                  <li>
                    <a
                      href="${pageContext.request.contextPath}/snsboard/snsList"
                      >SNS</a
                    >
                  </li>
                  <!-- 
                                <li><a href="#">Form</a></li>
                                <li><a href="#">Board</a></li>
 				-->
                </ul>
                <ul class="nav navbar-nav navbar-right">
                  <li class="dropdown">
                    <a
                      class="dropdown-toggle"
                      data-toggle="dropdown"
                      href="#"
                      >로그인 <span class="caret"></span
                    ></a>
                    <ul class="dropdown-menu">
                      <c:if test="${sessionScope.login == null}">
                        <li>
                          <a
                            href="${pageContext.request.contextPath}/user/userJoin"
                            ><span class="glyphicon glyphicon-user"></span
                            >Join</a
                          >
                        </li>
                        <li>
                          <a
                            href="${pageContext.request.contextPath}/user/userLogin"
                            ><span class="glyphicon glyphicon-log-in"></span
                            >Login</a
                          >
                        </li>
                      </c:if>
                      <c:if test="${sessionScope.login != null}">
                        <li>
                          <a
                            href="${pageContext.request.contextPath}/user/userMypage"
                            ><span class="glyphicon glyphicon-user"></span
                            >MyPage</a
                          >
                        </li>
                        <li>
                          <a
                            id="logout"
                            href="#"
                            ><span class="glyphicon glyphicon-log-out"></span
                            >Logout</a
                          >
                        </li>
                      </c:if>
                    </ul>
                  </li>
                </ul>

                <form
                  class="navbar-form navbar-right"
                  action=""
                >
                  <div class="input-group">
                    <input
                      type="text"
                      class="form-control"
                      placeholder="Search"
                    />
                    <div class="input-group-btn">
                      <button
                        class="btn btn-primary"
                        type="submit"
                      >
                        검색
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
    <script>
      document.querySelector('.dropdown').addEventListener('click', (e) => {
        //addEventListener()의 매개변수: type, func

        //1. 이벤트가 발생한 target이 a태그가 아니라면 이벤트 강제 종료!
        if (!e.target.matches('a#logout')) return;

        console.log('로그아웃a태그에만 이벤트 발생!');

        let f = document.createElement('form');

        f.setAttribute('method', 'post');
        f.setAttribute(
          'action',
          '${pageContext.request.contextPath}/user/logout'
        );
        document.body.appendChild(f);
        f.submit();
      });
    </script>
  </body>
</html>
