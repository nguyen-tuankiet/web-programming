<%--
  Created by IntelliJ IDEA.
  User: VSiJs
  Date: 12/27/2024
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Slideshow đơn giản</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-slideshow/topRefrigerator.css">
  <script src="${pageContext.request.contextPath}/static/style-component/style-slideshow/topRefrigerator.js"></script>
</head>
<body>

<div class="slideshow-container">
  <div class="list-wrap">
    <div class="slide fade">
      <img src="${pageContext.request.contextPath}/static/image/img_slideshow/slide1.jpg" alt="Slide 1">
    </div>
    <div class="slide fade">
      <img src="${pageContext.request.contextPath}/static/image/img_slideshow/slide2.jpg" alt="Slide 2">
    </div>
    <div class="slide fade">
      <img src="${pageContext.request.contextPath}/static/image/img_slideshow/slide3.jpg" alt="Slide 3">
    </div>
    <div class="slide fade">
      <img src="${pageContext.request.contextPath}/static/image/img_slideshow/slide4.jpg" alt="Slide 4">
    </div>
  </div>

  <button class="prev" onclick="changeSlide(-1)">❮</button>
  <button class="next" onclick="changeSlide(1)">❯</button>

  <div class="navigation-container">
    <div class="dot-navigation">
      <span class="dot" onclick="setSlide(0)"></span>
      <span class="dot" onclick="setSlide(1)"></span>
      <span class="dot" onclick="setSlide(2)"></span>
      <span class="dot" onclick="setSlide(3)"></span>
    </div>
    <!-- Nút Pause/Play -->
    <button class="pause-button" onclick="togglePlayPause()">
      <div class="pause-icon"></div>
    </button>
  </div>
</div>

</body>
</html>