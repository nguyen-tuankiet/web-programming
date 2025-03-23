<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Web Programming</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-page/auth/auth.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="container" id="container">
  <!-- Đăng ký -->
  <div class="form-container sign-up-container">
    <form action="${pageContext.request.contextPath}/register" method="POST">
      <h1>Tạo tài khoản</h1>
      <div class="social-container">
        <a href="https://www.facebook.com/?locale=vi_VN" class="social"><i class="fab fa-facebook-f"></i></a>
        <a href="${pageContext.request.contextPath}/login-google" class="social"><i class="fab fa-google-plus-g"></i></a>
        <a href="https://www.linkedin.com/" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div>
      <span>hoặc sử dụng email của bạn để đăng ký</span>

      <div class="infield">
        <input type="text" id="fullName" name="fullName" placeholder=" " required />
        <label for="fullName">Tên đầy đủ <span class="required">*</span></label>
      </div>

      <div class="infield">
        <input type="text" id="displayName" name="displayName" placeholder=" " required />
        <label for="displayName">Tên hiển thị <span class="required">*</span></label>
      </div>

      <div class="infield">
        <input type="email" id="email" name="email" placeholder=" " required />
        <label for="email">Email <span class="required">*</span></label>
      </div>

      <div class="infield password">
        <input type="password" id="password" name="password" placeholder=" " required>
        <label for="password">Mật khẩu <span class="required">*</span></label>
        <i class="fas fa-eye toggle-password" data-toggle="#password"></i>
      </div>
      <div id="password-error" style="color: red; display: none;">
        Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm một ký tự viết hoa, một ký tự số và một ký tự đặc biệt.
      </div>

      <div class="infield password">
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder=" " required />
        <label for="confirmPassword">Xác nhận mật khẩu <span class="required">*</span></label>
        <i class="fas fa-eye toggle-password" data-toggle="#confirmPassword"></i>
      </div>
      <div id="confirm-error" style="color: red; display: none;">
        Mật khẩu xác nhận không khớp.
      </div>

      <div class="infield terms">
        <input type="checkbox" id="terms-checkbox" required />
        <label for="terms-checkbox">
          Tôi đồng ý với điều khoản và điều kiện, và xác nhận đã đọc chính sách bảo mật.
        </label>
      </div>

      <button type="submit">Đăng ký</button>
    </form>
  </div>

  <!-- Đăng nhập -->
  <div class="form-container sign-in-container">
    <form action="${pageContext.request.contextPath}/login" method="POST">
      <h1>Đăng nhập</h1>
      <div class="social-container">
        <a href="https://www.facebook.com/?locale=vi_VN" class="social"><i class="fab fa-facebook-f"></i></a>
        <a href="${pageContext.request.contextPath}/login-google" class="social"><i class="fab fa-google-plus-g"></i></a>
        <a href="https://www.linkedin.com/" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div>
      <span>hoặc sử dụng tài khoản của bạn</span>

      <div class="infield">
        <input type="email" id="loginEmail" name="email" placeholder=" " required />
        <label for="loginEmail">Email <span class="required">*</span></label>
      </div>

      <div class="infield password">
        <input type="password" id="loginPassword" name="password" placeholder=" " required>
        <label for="loginPassword">Mật khẩu <span class="required">*</span></label>
        <i class="fas fa-eye toggle-password" data-toggle="#loginPassword"></i>
      </div>

      <div class="remember-forgot-container">
        <div class="infield remember-me">
          <input type="checkbox" id="remember-checkbox" name="remember" />
          <label for="remember-checkbox">Ghi nhớ đăng nhập</label>
        </div>
        <a href="${pageContext.request.contextPath}/auth/forgotpassword.jsp" class="forgot">Quên mật khẩu?</a>
      </div>

      <button type="submit">Đăng nhập</button>
    </form>
  </div>

  <div class="overlay-container" id="overlayCon">
    <div class="overlay">
      <div class="overlay-panel overlay-left">
        <h1>Chào mừng trở lại!</h1>
        <p>
          Để tiếp tục kết nối với chúng tôi, vui lòng đăng nhập bằng thông tin cá nhân của bạn.
        </p>
        <button class="ghost login-button">Đăng nhập</button>
      </div>
      <div class="overlay-panel overlay-right">
        <h1>Xin chào, bạn mới!</h1>
        <p>Nhập thông tin cá nhân và bắt đầu hành trình cùng chúng tôi</p>
        <button class="ghost register-button">Đăng ký</button>
      </div>
    </div>
  </div>
</div>

<main>
  <script src="${pageContext.request.contextPath}/static/style-page/auth/auth.js"></script>
</main>

<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color: red; text-align: center;"><%= request.getAttribute("errorMessage") %></p>
<% } %>

</body>
</html>

