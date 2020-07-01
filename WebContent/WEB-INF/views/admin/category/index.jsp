<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<link rel="icon" href="images/icon.png" />
<link rel="stylesheet" href="../front/reset.css" />
<link rel="stylesheet" href="../front/admin.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous" />

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Pridi:wght@300&display=swap"
	rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="${pageContext.servletContext.contextPath}/">
<title>Admin</title>
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
			
			<c:if test="${message != null}">
				<div class="alert success">
					<span class="closebtn">&times;</span>  
					${message}
				</div>
			</c:if>
						
			
			<div style=" background-color: #3e3e3e03;margin-top: 20px;">
				<div class="login">
					<div class="container">
						<h4 style="color:white;">THÊM LOẠI MỚI</h4>
						<form action = "admin/cate-new.htm" method = "post">
							<div class="name">
								<p>Tên loại</p>
								<input type="text"  name = "tenLoai" value = "${name}"/>
								<p>${ten_fail}</p>
							</div>
	
							<div class="btn">
								<button>Thêm</button>
							</div>
						</form>
					</div>
				</div>

				<!-- table admin -->
				<div class="table mid">
					<div class="thuancute">
						<table class="table table-dark">
							<thead>
								<tr>
									<th scope="col">STT</th>
									<th scope="col">Ten</th>
									<th scope="col">Quan ly</th>
								</tr>
							</thead>
	
							<tbody>
								<c:forEach var = "c" items="${cate}" varStatus = "status">
									<tr>
										<th scope="row">${status.index + 1}</th>
										<td>${c.tenLoai}</td>
										<td>
											<a href = "admin/cate/${c.maLoai}.htm?delete" style = "color:white;">Xoá</a>
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
	
	<script>
		var close = document.getElementsByClassName("closebtn");
		var i;
		
		for (i = 0; i < close.length; i++) {
		  close[i].onclick = function(){
		    var div = this.parentElement;
		    div.style.opacity = "0";
		    setTimeout(function(){ div.style.display = "none"; }, 600);
		  }
		}
	</script>
	
</body>

<script src="./js/jquery-3.5.0.min.js"></script>
<script src="./js/script.js"></script>
</html>
