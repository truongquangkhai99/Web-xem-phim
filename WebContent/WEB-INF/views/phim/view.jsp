<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
    <link rel="stylesheet" href="../../front/reset.css" />
    <link rel="stylesheet" href="../../front/demo.css" />
    <link rel="stylesheet" href="../../front/view.css" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" />
    <link href="https://fonts.googleapis.com/css2?family=Pridi:wght@300&display=swap" rel="stylesheet" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<base href="${pageContext.servletContext.contextPath}/">
	<title>${title}</title>
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

			<div class="video">
				<div class="container">
					<iframe width="98.4%" height="480"
						src="${phim.link}" frameborder="0"
						allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
						allowfullscreen></iframe>

					<h3 style = "text-transform: capitalize;">${phim.phimCT.tenPhim}</h3>

					<div class="chap flex">
						<p>Tập:</p>
						<ul>
							<c:forEach var = "p" items= "${listP}">
							<c:choose>
								<c:when test="${phim.id == p.id}">
									<li class="mid active"><a href="phim/tap/${p.id}.htm">${p.tap}</a></li>
								</c:when>
								<c:otherwise>
									<li class="mid"><a href="phim/tap/${p.id}.htm">${p.tap}</a></li>
								</c:otherwise>
							</c:choose>
								
								
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>

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
