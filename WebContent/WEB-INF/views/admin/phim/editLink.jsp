<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<link rel="icon" href="images/icon.png" />
<link rel="stylesheet" href="../../front/reset.css" />
<link rel="stylesheet" href="../../front/register.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Pridi:wght@300&display=swap"
	rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="${pageContext.servletContext.contextPath}/">
<title>Cập nhật thông tin phim</title>
</head>

<body>
	<div class="content mid">
		<div class="wraper">
			<!-- header -->
			<div class="header">
				<div class="container space">
					<div class="left">
						<a href="#"> ANIME <span>HAY</span>
						</a>
					</div>

					<div class="right flex">
						<p>DANH MỤC</p>
						<i class="fas fa-align-justify"></i>
					</div>
				</div>
			</div>
			<!-- end header -->

			<!-- menu -->
			<div class="menu">
				<div class="container flex">
					<div class="left">
						<form>
							<input type="text" placeholder="Nhập và Enter" /> <i
								class="fas fa-search"></i>
						</form>
					</div>

					<div class="mid-menu"></div>

					<c:choose>
						<c:when test="${sessionScope.user != null}">
							<div class="right mid">
								<a href="dang-xuat.htm">Đăng xuất</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="right mid">
								<a href="dang-nhap.htm">Đăng Nhập</a>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<!-- end menu -->

			<!-- form login -->
			<div class="login">
				<div class="container">
					<div class="menu">
						<ul>
							<li><a class="active">Cập nhật</a></li>
						</ul>
					</div>
					
					<div style = "color: white; padding-left: 35%;">
							<p>${message}</p>
					</div>

					<div class="form mid">
						<form:form action = "admin/tapPhim-edit.htm?i=${tapPhim.id}" method = "post">
							<div class="name">
								<p>Tên phim: ${tapPhim.phimCT.tenPhim}</p>
								<p>Tập: ${tapPhim.tap}</p>
							</div>

							<div class="pass">
								<p>Link phim</p>
								<input type="url" placeholder="Link phim" name= "link" value = "${tapPhim.link}"/>
								<c:if test="${link_fail != null}">
									<p >${link_fail}</p>
								</c:if>
							</div>
							<div class="btn">
								<button style = "margin-left:30%">Cập nhật</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<!-- end form login -->

			
		</div>
	</div>
</body>

<script src="./js/jquery-3.5.0.min.js"></script>
<script src="./js/script.js"></script>
</html>
