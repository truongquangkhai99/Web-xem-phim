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
	<title>Register</title>
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

					<div class="right mid">
						<a href="dang-nhap.htm">Đăng Nhập</a>
					</div>
				</div>
			</div>
			<!-- end menu -->

			<!-- form login -->
			<div class="login">
				<div class="container">
					<div class="menu">
						<ul>
							<li><a href="dang-nhap.htm">đăng nhập</a></li>
							<li><a href="dang-ky.htm" class="active">đăng ký</a></li>
						</ul>
					</div>

					<form class="mid" action = "dang-ky.htm" method = "post">
						<div class="name">
							<p>Họ Tên</p>
							<input type="text"  name = "name" value = "${name}"/>
							<p>${name_fail}</p>
						</div>

						<div class="pass">
							<p>Mật Khẩu</p>
							<input type="password" name = "password" placeholder="Mật khẩu lớn hơn 6 kí tự" />
							<p>${pass_fail}</p>
						</div>

						<div class="re-pass">
							<p>Nhập lại</p>
							<input type="password" name = "nhapLai" placeholder="Nhập lại mật khẩu" />
							<p>${NL_fail}</p>
						</div>

						<div class="email">
							<p>Email</p>
							<input type="email" name = "email" value = "${email}" placeholder="Nhập Email chuẩn xác" />
							<p>${email_fail}</p>
						</div>

						<div class="btn">
							<button>đăng ký</button>
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
