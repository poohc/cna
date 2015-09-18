<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Coco&Ahsho</title>
		<meta charset="UTF-8" />
		<link rel="stylesheet" type="text/css" href="/assets/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/slideshow.css">
		<link rel="stylesheet" type="text/css" href="/assets/css/example.css">
	</head>
	<body>
		<div id="container">
			<div id="slideshow" class="fullscreen">
				<!-- Below are the images in the gallery -->
				<c:forEach items="${imgUrlList}" var="imgUrlList" varStatus="loop">
					<c:choose>
						<c:when test="${loop.index eq 0}">
							<div id="img-${loop.count}" data-img-id="${loop.count}" class="img-wrapper active" style="background-image: url('${imgUrlList}')"></div>
						</c:when>
						<c:otherwise>
							<div id="img-${loop.count}" data-img-id="${loop.count}" class="img-wrapper" style="background-image: url('${imgUrlList}')"></div>								
						</c:otherwise>						
					</c:choose>
				</c:forEach>
				<!-- Below are the thumbnails of above images -->
			    <div class="thumbs-container bottom">
			    	<div id="prev-btn" class="prev">
			    		<i class="fa fa-chevron-left fa-3x"></i>
			    	</div>
					<ul class="thumbs">
						<c:forEach items="${imgThumbList}" var="imgThumbList" varStatus="loop">
							<c:choose>
								<c:when test="${loop.index eq 0}">
									<li data-thumb-id="${loop.count}" class="thumb active" style="background-image: url('${imgThumbList}')"></li>
								</c:when>
								<c:otherwise>
									<li data-thumb-id="${loop.count}" class="thumb" style="background-image: url('${imgThumbList}')"></li>								
								</c:otherwise>						
							</c:choose>
						</c:forEach>
					</ul>

			    	<div id="next-btn" class="next">
			    		<i class="fa fa-chevron-right fa-3x"></i>
			    	</div>
			    </div>
			</div>
		</div>

		<!-- Including Scripts -->
		<script src="/assets/js/jquery-1.11.1.min.js"></script>
		<script src="/assets/js/gallery.js"></script>        
	</body>
</html>