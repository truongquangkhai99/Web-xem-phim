<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<link rel="icon" href="images/icon.png" />
<link rel="stylesheet" href="front/reset.css" />
<link rel="stylesheet" href="front/register.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Pridi:wght@300&display=swap"
	rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="${pageContext.servletContext.contextPath}/">
<title>Login</title>
<style>
#p_1 {
	display: inline-block;
}
</style>
</head>

<body>
	<div class="content mid">
		<div class="wraper">
			<!-- header -->
			<div class="header">
				<div class="container space">
					<div class="left">
						<a href="index.htm"> ANIME <span>HAY</span>
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
							<li><a href="dang-nhap.htm" class="active">đăng nhập</a></li>
							<li><a href="dang-ky.htm">đăng ký</a></li>
						</ul>
					</div>
					
					<div style = "color: white; padding-left: 35%;">
							<p>${message}</p>
					</div>
					<form class="mid" action="quen-mat-khau.htm" method="post">
						<div class="email">
							<p>Email</p>
							<input type="email" name = "email" value = "${email}" placeholder="Nhập Email chuẩn xác" />
							<c:if test="${email_fail != null}">
								<p class="err">${email_fail}</p>
							</c:if>
						</div>

						<div class="btn">
							<button>Tìm lại mật khẩu</button>
						</div>
					</form>
				</div>
			</div>
			<!-- end form login -->

			<div class="footer">
				<div class="container space">
					<div class="left">
						<h3>liên kết phim</h3>
						<ul>
							<li><a href="#">abcb</a></li>
							<li><a href="#">abcb</a></li>
							<li><a href="#">abcb</a></li>
						</ul>
					</div>

					<div class="right">
						<h3>liên hệ</h3>
						<ul>
							<li><a href="#"> <i class="far fa-envelope"></i>
									minhthuan140299@gmail.com
							</a></li>
							<li><a href="https://www.facebook.com/su.thuan.16"> <i
									class="fab fa-facebook-square"></i>
									https://www.facebook.com/su.thuan.16
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- end footer -->
		</div>
	</div>
</body>

<script src="./js/jquery-3.5.0.min.js"></script>
<script src="./js/script.js"></script>
</html>
