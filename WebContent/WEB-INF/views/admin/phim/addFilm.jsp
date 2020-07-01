<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<link rel="icon" href="images/icon.png" />
<link rel="stylesheet" href="../front/reset.css" />
<link rel="stylesheet" href="../front/register.css" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Pridi:wght@300&display=swap"
	rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="${pageContext.servletContext.contextPath}/">
<title>Thêm phim mới</title>
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
							<li><a class="active">Thêm phim mới</a></li>
						</ul>
					</div>
					
					<div style = "color: white; padding-left: 35%;">
							<p>${message}</p>
					</div>

					<div class="form mid">
						<form:form action = "admin/phim-newFilm.htm" enctype = "multipart/form-data" method = "post">
							<div class="name">
								<p>Tên Phim</p>
								<input type="text" placeholder="Tên Phim"name="tenPhim"/>
								<c:if test="${ten_fail != null}">
									<p >${ten_fail}</p>
								</c:if>
							</div>

							<div class="pass">
								<p>Nội dung</p>
								<input type="text" placeholder="Nội Dung" name= "noiDung"/>
								<c:if test="${noidung_fail != null}">
									<p >${noidung_fail}</p>
								</c:if>
							</div>

							<div class="pass">
								<p>Năm Phát Hành</p>
								<input type="number" placeholder="Năm Phát Hành" name = "namPH"/>
								<c:if test="${namPH_fail != null}">
									<p >${namPH_fail}</p>
								</c:if>
							</div>

							<div class="pass">
								<p>Số Tập</p>
								<input type="number" min = "0" placeholder="Số Tập" name = "soTap"/>
								<c:if test="${tap_fail != null}">
									<p >${tap_fail}</p>
								</c:if>
							</div>

							<div class="pass">
								<p>Hình Ảnh 1 (Hình lớn)</p>
								<input type="file" placeholder="Hình Ảnh 1" name = "hinh1"/>
								<c:if test="${hinh1_fail != null}">
									<p >${hinh1_fail}</p>
								</c:if>
							</div>

							<div class="pass">
								<p>Hình Ảnh 2 (Hình nhỏ)</p>
								<input type="file" placeholder="Hình Ảnh 2" name = "hinh2" />
								<c:if test="${hinh2_fail != null}">
									<p >${hinh2_fail}</p>
								</c:if>
							</div>
							
							<div class="pass">
								<p>Thể loại: </p>
								<c:forEach var = "c" items="${category}">
									<input type="checkbox" id="${c.maLoai}" name="${c.maLoai}" value="${c.maLoai}">
								    <label for="${c.maLoai}"> ${c.tenLoai}</label><br>
								</c:forEach>
								
							    
							</div>
							

							<div class="btn">
								<button style = "margin-left:30%">Thêm phim</button>
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
