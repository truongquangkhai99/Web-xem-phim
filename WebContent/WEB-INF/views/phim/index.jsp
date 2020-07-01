<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="../front/reset.css" />
<link rel="stylesheet" href="../front/detail_phim.css" />
<link href="https://fonts.googleapis.com/css2?family=Pridi:wght@300&display=swap" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
      integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
      crossorigin="anonymous"
    />
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

			<!-- film -->
			<div class="film">
				<div class="container">
					<div class="name mid">
						<h3>
							<a href="phim/${phim.maPhim}.htm">${phim.tenPhim}</a>
						</h3>
					</div>

					<div class="avatar space">
						<div class="sub-avatar">
							<img src="images/${phim.hinh2}" alt="" />
						</div>

						<div class="main-avatar">
							<img src="images/${phim.hinh}" alt="" />
						</div>
					</div>

					<div class="menu">
						<ul class="flex">
							<li></i><a class="play" href="phim/tap/${tap1.id}.htm"><i
									class="fas fa-play-circle"></i> Xem Phim</a></li>
							<li><a class="login"
								href="subscribe/phim.htm?q=${phim.maPhim}"><i
									class="fas fa-plus-square"></i> Theo dõi</a></li>
						</ul>
					</div>

					<div class="infor">
						<div class="left">
							<h4>Thông Tin</h4>
							<div class="new flex">
								<p>Tập mới:</p>
								<ul class="flex">
									<c:forEach var="p" items="${tapMoi}">
										<li><a href="phim/tap/${p.id}.htm">${p.tap}</a></li>
									</c:forEach>
								</ul>
							</div>

							<div class="year">
								<p>Năm phát hành: ${phim.namPH}</p>
							</div>

							<div class="category">
								<p>Thể loại:</p>
								<ul>
									<c:forEach var="c" items="${loaiPhim}">
										<li><a href="phim.htm?c=${c.maLoai}">${c.tenLoai}</a></li>
									</c:forEach>

								</ul>
							</div>

							<div class="time">
								<p>
									Thời lượng:
									<c:choose>
										<c:when test="${phim.soTap != 0}"> ${phim.soTap} tập</c:when>
										<c:otherwise> ??</c:otherwise>
									</c:choose>
								</p>
							</div>
							<div class="year">
								<p>
									Tình trạng:
									<c:choose>
										<c:when test="${phim.trangThai == 1}">Đang tiến hành</c:when>
										<c:when test="${phim.trangThai == 2}">Tạm hoãn</c:when>
										<c:otherwise>Đã hoàn thành</c:otherwise>
									</c:choose>
								</p>
							</div>
						</div>

						<div class="right">
							<h4>Nội Dung</h4>

							<p>${phim.noiDung}</p>
						</div>
					</div>
				</div>
			</div>
			<!-- end film -->

			<!-- comments -->
			<div class="comments">
				<div class="container">
					<div class="add-cmt">
						<div class="count-cmt">
							<p>count cmt</p>
						</div>

						<div class="add">
							<a href="#">
								<img src="https://picsum.photos/id/1012/367/267"
									alt="">
							</a>
							<input type="text" name="comment" id="comment" placeholder="Thêm bình luận"  onchange="addViaAjax()"/>
							<input type="submit" value="Bình luận" onchange="addViaAjax()">
						</div>


					</div>

					<div class="cmts">
						<c:forEach var="c" items="${comments}" varStatus="status">
							<div class="cmt flex">

								<c:if test="${c.recomment == 0}">
									<a href="#"><img
										src="https://picsum.photos/id/1006/367/267" alt=""></a>
									<div class="inf">
										<p class="user">${c.emailBL.name}</p>
										<p class="text">${c.noiDung}</p>
										<p class="rep" id="reply">Phản hồi</p>
									</div>
									<div class="reply">
										<div class="reply-area">
											<c:forEach var="rep" items="${comments}"
												end="${status.index}">
												<c:if test="${rep.recomment == c.maBL}">
													<div class="reply-item flex">
														<a href="#"><img
															src="https://picsum.photos/id/1012/367/267" alt=""></a>
														<div class="inf">
															<p class="user">${rep.emailBL.name}</p>
															<p class="text">${rep.noiDung}</p>
															<p class="rep" id="reply">Phản hồi</p>
														</div>
													</div>
												</c:if>

											</c:forEach>
										</div>
									</div>
								</c:if>



							</div>
						</c:forEach>

					</div>
				</div>
			</div>
			<!-- end comments -->

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
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
	<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		function addViaAjax() {
			console.log("called")
			var noiDung = $("#comment").val();
			var maPhim = '@Model.phim.maPhim';

			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "user/comment.htm",
				data : {
					noiDung : noiDung,
					maPhim : maPhim
				},
				dataType : 'json',
				success : function(data) {
					console.log("SUCCESS: ", data);

				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});

		}
	</script>
</body>


<script src="./js/jquery-3.5.0.min.js"></script>
<script src="./js/script.js"></script>
</html>
