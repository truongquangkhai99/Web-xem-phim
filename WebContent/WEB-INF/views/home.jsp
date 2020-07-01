<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<link rel="icon" href="images/icon.png" />
<link rel="stylesheet" href="front/reset.css">
<link rel="stylesheet" href="front/demo.css">
<link rel="stylesheet" href="front/list.css">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" />
<link href="https://fonts.googleapis.com/css2?family=Pridi:wght@300&display=swap" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="${pageContext.servletContext.contextPath}/">
<title>Anime</title>
<style>
.collapsible {
  background-color: #777;
  color: white;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
}

.active, .collapsible:hover {
  background-color: #555;
}

.content2 {
  display: none;
  overflow: hidden;
  background-color: #f1f1f1;
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

					<div class="right flex" id="list">
		              <p>DANH MỤC</p>
		              <i class="fas fa-align-justify"></i>
		            </div>
				</div>
			</div>
			<!-- end header -->

			<!-- DANH MUC -->

			<div class="list">
				<div class="container">
					<ul>
						<li class="collapsible">
							Anime
						</li>
						<div class="content2">
							
							<li>2</li>
							<li>3</li>
							<li>4</li>
						</div>
						<c:if test="${sessionScope.user.isAdmin}">
							<li class="collapsible">
								Quản lý
							</li>
							<div class="content2">
								<li>
									<a href = "admin/phim.htm" style="background-color: #212121; color: white;">
									Danh sách phim</a>
								</li>
								<li>
									<a href = "admin/category.htm" style="background-color: #212121; color: white;">
									Thể loại
									</a>
								</li>
								<li>
									<a href = "admin/user.htm" style="background-color: #212121; color: white;">
									Thành viên</a>
								</li>
								<li>
									<a href = "admin/record.htm" style="background-color: #212121; color: white;">
									Nhật ký hệ thống
									</a></li>
								</li>
							</div>
						</c:if>
						
						<li>
							<c:choose>
								<c:when test="${sessionScope.user != null}">
									<a href="dang-xuat.htm" style="background-color: #212121; color: white;">Đăng xuất</a>
								</c:when>
								<c:otherwise>
									<a href="dang-nhap.htm" style="background-color: #212121; color: white;">Đăng nhập</a>
								</c:otherwise>
							</c:choose>
						</li>
						
					</ul>
				</div>
			</div>

			<!-- DANH MUC -->

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

			<!-- slider -->
			<div class="slider">
				<div class="owl-carousel" id="landing">
					<c:forEach var="s" items="${slide}">
						<div class="items">
							<a href="phim/tap/${s.id}.htm"> <img
								src="images/${s.phimCT.hinh}" alt="${s.phimCT.tenPhim}"
								style="height: 200px;">
								<p class="year">Tập ${s.tap}</p>

								<div class="footer space">
									<h3 style="font-size: 16px;">${s.phimCT.tenPhim}</h3>
									<p>${s.phimCT.namPH}</p>
								</div>
							</a>
						</div>
					</c:forEach>

				</div>
			</div>
			<!-- end silder -->

			<!-- new films -->
			<div class="new-films">
				<div class="container">
					<h3>
						<i class="fas fa-star-half-alt"></i> PHIM MỚI CẬP NHẬT
					</h3>

					<div class="films space">
						<c:forEach var="p" items="${phim}">
							<div class="item">
								<a href="phim/${p.maPhim}.htm"> <img src="images/${p.hinh2}"
									alt="${p.tenPhim}">

									<p class="count mid">/??</p>
									<h4>${p.tenPhim}</h4>
								</a>
							</div>
						</c:forEach>



					</div>
				</div>
			</div>
			<!-- end new films -->

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
	<script>
		var coll = document.getElementsByClassName("collapsible");
		var i;
		
		for (i = 0; i < coll.length; i++) {
		  coll[i].addEventListener("click", function() {
		    this.classList.toggle("active");
		    var content2 = this.nextElementSibling;
		    if (content2.style.display === "block") {
		      content2.style.display = "none";
		    } else {
		      content2.style.display = "block";
		    }
		  });
		}
	</script>

</body>

<script src="./js/jquery-3.5.0.min.js"></script>
<script src="./js/owl.carousel.js"></script>
<script src="./js/owl.autoplay.js"></script>
<script src="./js/script.js"></script>

<link rel="stylesheet" href="./css/owl.carousel.css" />

</html>