<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<link rel="icon" href="images/icon.png" />
<link rel="stylesheet" href="../../front/reset.css" />
<link rel="stylesheet" href="../../front/admin.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous" />

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Pridi:wght@300&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="${pageContext.servletContext.contextPath}/">
<title>Quản lý - ${phim.tenPhim}</title>

<style>
	.info{
		color: white;
	    background-color: #3e3e3e;
	}
</style>

</head>


<body>
	<div class="content mid">
		<div class="wraper">
			<!-- header -->
			<div class="header">
				<div class="thuancute space">
					<div class="left">
						<a href="index.htm"> ANIME <span>HAY</span>
						</a>
					</div>

					<div class="right flex">
						<p style="transform: translateY(8px);">Hi Admin!</p>
					</div>
				</div>
			</div>
			<!-- end header -->

			<!-- menu -->
			<div class="menu">
				<div class="container flex">
					<div class="left">
						<form action = "search.htm" method = "post">
							<input type="text" placeholder="Nhập và Enter" /> <i
								class="fas fa-search"></i>
						</form>
					</div>

					<div class="mid-menu"></div>

					<div class="right mid"></div>
				</div>
			</div>
			<!-- end menu -->
			
			<div class = "info">
				<div style = "padding: 0 0 5px 30px; background-color: #5a5454;">
					<h4>THÔNG TIN PHIM</h4>
					<p>Tên: ${phim.tenPhim}</p>
					<p>Năm phát hành: ${phim.namPH}</p>
					<p>Số tập: ${phim.soTap}</p>
					<p>Tình trạng: 
						<c:choose>
							<c:when test="${phim.trangThai == 1}">Đang tiến hành</c:when>
							<c:when test="${phim.trangThai == 2}">Tạm hoãn</c:when>
							<c:otherwise>Đã hoàn thành</c:otherwise>
						</c:choose>
					</p>
				</div>
			</div>
			
			<h3 style = "background-color: #3e3e3e03; color:white;">Danh sách tập phim</h3>
			<div style=" background-color: #3e3e3e03;margin-top: 20px;">
				<div class="w3-bar" style="display:inline-block;background-color: #f0f8ff00; float: right;">
					<a href = "admin/phim.htm?addLink&p=${phim.maPhim}">
						<button class="w3-button w3-red"> Thêm tập mới</button>
					</a>
					
					<a href = "admin/phim.htm?delete&p=${phim.maPhim}">
						<buttuon class= "w3-button w3-red"> Xoá</buttuon>
					</a>
					<a href = "admin/phim.htm?edit&p=${phim.maPhim}">
						<buttuon class= "w3-button w3-red"> Chỉnh sửa</buttuon>
					</a>
				</div>
				<!-- table admin -->
				<div class="table mid">
					
					<div class="thuancute">
						<table class="table table-dark">
							<thead>
								<tr>
									<th scope="col">Tập</th>
									<th scope="col">Ngày thêm</th>
									<th scope="col">Quản lý</th>
								</tr>
							</thead>
	
							<tbody>
								<c:forEach var = "t" items="${tapPhim}" varStatus = "status">
									<tr>
										<th scope="row">${t.tap}</th>
										<td><fmt:formatDate value="${t.ngay}"
											pattern="dd-MM-yyyy" /></td>
										<td>
											<a href = "admin/tapPhim/${t.id}.htm?edit" style = "color:white;">Chỉnh sửa</a> | 
											<a href = "admin/tapPhim/${t.id}.htm?delete" style = "color:white;">Xoá</a>
										</td>
									</tr>
								</c:forEach>	
							</tbody>
						</table>
					</div>
				</div>
				<!-- end table admin -->
			</div>
		</div>
	</div>
</body>

<script src="./js/jquery-3.5.0.min.js"></script>
<script src="./js/script.js"></script>
</html>
