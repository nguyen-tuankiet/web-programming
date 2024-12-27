<%--
  Created by IntelliJ IDEA.
  User: kiet
  Date: 12/27/2024
  Time: 12:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cài Đặt Tài Khoản với Thông Báo và Tài Khoản Kết Nối</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/account_settings/account_settings.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>

</head>
<body>

<div class="container">
    <!-- Navbar -->
    <div class="navbar">
        <a href="#basic-information">
            <i class="fas fa-user"></i>
            Thông tin cơ bản
        </a>
        <a href="#Password">
            <i class="fas fa-key"></i>
            Mật khẩu
        </a>
        <a href="#notifications">
            <i class="fas fa-bell"></i>
            Thông báo
        </a>
        <a href="#connected-accounts">
            <i class="fas fa-sitemap"></i>
            Tài khoản kết nối
        </a>
        <a href="#social-accounts">
            <i class="fab fa-instagram"></i>
            Tài khoản mạng xã hội
        </a>
        <!--        <a href="#delete-account">-->
        <!--            <i class="fas fa-trash-alt"></i>-->
        <!--            Xóa tài khoản-->
        <!--        </a>-->
    </div>

    <!-- Main Content -->
    <div class="slideshow-container">
        <!-- Basic Information Section -->
        <div class="content basic-information" id="basic-information">
            <h3>Thông tin cơ bản</h3>
            <form>
                <!-- Họ và Tên -->
                <label for="full-name">
                    <span>Họ và Tên</span>
                    <input type="text" id="full-name" value="Nguyễn Văn A" />
                </label>

                <!-- Email -->
                <label for="email">
                    <span>Email</span>
                    <input type="email" id="email" value="nguyenvana@example.com" />
                </label>

                <!-- Số Điện Thoại -->
                <label for="phone">
                    <span>Số điện thoại (Tuỳ chọn)</span>
                    <input type="tel" id="phone" value="+84 912345678" />
                </label>

                <!-- Phòng Ban -->
                <label for="department">
                    <span>Phòng ban</span>
                    <input type="text" id="department" placeholder="Phòng ban của bạn" />
                </label>

                <!-- Địa Điểm - Custom Select với Icon Cờ -->
                <label for="location">
                    <span>Địa điểm</span>
                    <div class="location-select-container">
                        <div class="selected-option" id="selected-option">
                            <img src="https://flagcdn.com/w40/vn.png" alt="Vietnam Flag" class="flag-icon">
                            Việt Nam
                        </div>
                        <div class="options-container" id="options-container">
                            <div class="option" data-value="vn">
                                <img src="https://flagcdn.com/w40/vn.png" alt="Vietnam Flag" class="flag-icon">
                                Việt Nam
                            </div>
                            <div class="option" data-value="us">
                                <img src="https://flagcdn.com/w40/us.png" alt="USA Flag" class="flag-icon">
                                Hoa Kỳ
                            </div>
                            <div class="option" data-value="jp">
                                <img src="https://flagcdn.com/w40/jp.png" alt="Japan Flag" class="flag-icon">
                                Nhật Bản
                            </div>
                            <div class="option" data-value="kr">
                                <img src="https://flagcdn.com/w40/kr.png" alt="Korea Flag" class="flag-icon">
                                Hàn Quốc
                            </div>
                            <div class="option" data-value="fr">
                                <img src="https://flagcdn.com/w40/fr.png" alt="France Flag" class="flag-icon">
                                Pháp
                            </div>
                        </div>
                    </div>
                    <!-- Hidden Input -->
                    <input type="hidden" id="location" value="vn">
                </label>

                <!-- Thành Phố -->
                <label for="city">
                    <span>Thành phố</span>
                    <input type="text" id="city" value="Hà Nội" />
                </label>

                <!-- Khu Vực -->
                <label for="state">
                    <span>Khu vực</span>
                    <input type="text" id="state" placeholder="Quận/Huyện của bạn" />
                </label>

                <!-- Địa Chỉ 1 -->
                <label for="address-line-1">
                    <span>Địa chỉ 1</span>
                    <input type="text" id="address-line-1" value="123 Đường Lê Lợi, Quận Hai Bà Trưng" />
                </label>

                <!-- Địa Chỉ 2 -->
                <label for="address-line-2">
                    <span>Địa chỉ 2 (Tuỳ chọn)</span>
                    <input type="text" id="address-line-2" placeholder="Địa chỉ thêm của bạn (nếu có)" />
                </label>

                <!-- Mã Bưu Chính -->
                <label for="zip-code">
                    <span>Mã bưu chính</span>
                    <input type="text" id="zip-code" value="100000" />
                </label>

                <!-- Nút Lưu Thay Đổi -->
                <button class="save_btn" type="submit">Lưu thay đổi</button>
            </form>
        </div>




        <!--        &lt;!&ndash; Profile Cover Section &ndash;&gt;-->
        <!--        <div class="content profile-cover" id="ProfileCover">-->
        <!--            <div class="profile-cover-header">-->
        <!--                <img src="../../../resource/image/icon-user-settings/img2.jpg" alt="Ảnh bìa"-->
        <!--                     class="profile-cover-header-image">-->
        <!--                <button class="profile-cover-upload-header-btn">-->
        <!--                    <i class="fas fa-camera"></i> Tải lên ảnh bìa-->
        <!--                </button>-->
        <!--            </div>-->
        <!--            <div class="profile-photo-container">-->
        <!--                <div class="profile-photo">-->
        <!--                    <img src="../../../resource/image/medium%20(1).png" alt="Ảnh đại diện" class="profile-photo-img">-->
        <!--                    <button class="profile-photo-edit-btn">-->
        <!--                        <i class="fas fa-pencil-alt"></i>-->
        <!--                    </button>-->
        <!--                </div>-->
        <!--                <div class="profile-photo-visibility">-->
        <!--                    <label for="profile-photo-visibility-select">Ai có thể xem ảnh đại diện của bạn?</label>-->
        <!--                    <select id="profile-photo-visibility-select" class="profile-photo-visibility-select">-->
        <!--                        <option value="anyone">Mọi người</option>-->
        <!--                        <option value="friends">Bạn bè</option>-->
        <!--                        <option value="only-me">Chỉ mình tôi</option>-->
        <!--                    </select>-->
        <!--                </div>-->
        <!--            </div>-->
        <!--        </div>-->

        <!-- Password Section -->
        <div class="content password" id="Password">
            <div id="header" class="mid_align">
                <span>Đổi mật khẩu</span>
            </div>

            <div id="body" class="mid_align col">
                <form class="form" action="">
                    <label class="item">
                        <span>Mật khẩu hiện tại<span class="asterisk">*</span></span>
                        <div class="input">
                            <input id="current_pass" type="password" required>
                            <i class="toggle_pass fa-regular fa-eye"></i>
                        </div>
                    </label>

                    <label class="item">
                        <span>Mật khẩu mới<span class="asterisk">*</span></span>
                        <div class="input">
                            <input id="new_pass" type="password" required>
                            <i class="toggle_pass fa-regular fa-eye"></i>
                        </div>
                    </label>

                    <label class="item">
                        <span>Nhập lại mật khẩu<span class="asterisk">*</span></span>
                        <div class="input">
                            <input id="confirm_pass" type="password" required>
                            <i class="toggle_pass fa-regular fa-eye"></i>
                        </div>
                    </label>

                    <div class="password-requirements">
                        <strong>Yêu cầu mật khẩu:</strong>
                        <p>Đảm bảo các tiêu chí sau:</p>
                        <ul>
                            <li>Ít nhất 8 ký tự—càng dài càng tốt</li>
                            <li>Ít nhất một ký tự viết thường</li>
                            <li>Ít nhất một ký tự viết hoa</li>
                            <li>Ít nhất một số, ký hiệu hoặc khoảng trắng</li>
                        </ul>
                    </div>

                    <button class="save_btn" type="submit">Lưu thay đổi</button>
                </form>
            </div>
        </div>

        <!-- Notifications Section -->
        <div class="content notifications" id="notifications">
            <div class="card-header">
                <h3>Thông báo</h3>
            </div>
            <div class="alert">
                Chúng tôi cần quyền từ trình duyệt của bạn để hiển thị thông báo. <a href="#" class="alert-link">Yêu cầu
                quyền</a>
            </div>

            <form>
                <div class="table-responsive">
                    <table class="table table-thead-bordered table-nowrap table-align-middle">
                        <thead class="thead-light">
                        <tr>
                            <th>Loại</th>
                            <th class="text-center">
                                <div class="mb-1">
                                    <img class="avatar avatar-xs"
                                         src="../../../resource/image/icon-user-settings/oc-email-at.svg"
                                         alt="Biểu tượng Email">
                                </div>
                                Email
                            </th>
                            <th class="text-center">
                                <div class="mb-1">
                                    <img class="avatar avatar-xs"
                                         src="../../../resource/image/icon-user-settings/oc-globe.svg"
                                         alt="Biểu tượng Trình duyệt">
                                </div>
                                Trình duyệt
                            </th>
                            <th class="text-center">
                                <div class="mb-1">
                                    <img class="avatar avatar-xs"
                                         src="../../../resource/image/icon-user-settings/oc-phone.svg"
                                         alt="Biểu tượng Ứng dụng">
                                </div>
                                Ứng dụng
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Mới dành cho bạn</td>
                            <td class="text-center"><label>
                                <input type="checkbox">
                            </label></td>
                            <td class="text-center"><label>
                                <input type="checkbox">
                            </label></td>
                            <td class="text-center"><label>
                                <input type="checkbox">
                            </label></td>
                        </tr>
                        <tr>
                            <td>Hoạt động tài khoản</td>
                            <td class="text-center"><label>
                                <input type="checkbox">
                            </label></td>
                            <td class="text-center"><label>
                                <input type="checkbox">
                            </label></td>
                            <td class="text-center"><label>
                                <input type="checkbox">
                            </label></td>
                        </tr>
                        <tr>
                            <td>Trình duyệt mới được sử dụng để đăng nhập</td>
                            <td class="text-center"><label>
                                <input type="checkbox" checked>
                            </label></td>
                            <td class="text-center"><label>
                                <input type="checkbox" checked>
                            </label></td>
                            <td class="text-center"><label>
                                <input type="checkbox" checked>
                            </label></td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="actions">
                    <label>Khi nào chúng tôi nên gửi thông báo cho bạn?</label>
                    <label>
                        <select class="form-select">
                            <option>Luôn luôn</option>
                            <option>Chỉ trong ngày làm việc</option>
                        </select>
                    </label>
                    <button class="btn btn-primary">Lưu thay đổi</button>
                </div>
            </form>
        </div>

        <!-- Connected Accounts Section -->
        <div class="content connected-accounts" id="connected-accounts">
            <h3>Tài khoản kết nối</h3>
            <div class="account-info">
                <img class="avatar-xs" src="../../../resource/image/icon-user-settings/google-icon.svg"
                     alt="Biểu tượng Google">
                <div class="account-details">
                    <h4>Google</h4>
                    <p>Lịch và danh bạ</p>
                </div>
                <div class="switch">
                    <div class="slider"></div>
                </div>
            </div>

            <div class="account-info">
                <img class="avatar-xs" src="../../../resource/image/icon-user-settings/spec-icon.svg"
                     alt="Biểu tượng Spec">
                <div class="account-details">
                    <h4>Spec</h4>
                    <p>Quản lý dự án</p>
                </div>
                <div class="switch">
                    <div class="slider"></div>
                </div>

            </div>

            <div class="account-info">
                <img class="avatar-xs" src="../../../resource/image/icon-user-settings/slack-icon.svg"
                     alt="Biểu tượng Slack">
                <div class="account-details">
                    <h4>Slack</h4>
                    <p>Liên lạc</p>
                </div>
                <div class="switch">
                    <div class="slider"></div>
                </div>
            </div>

            <div class="account-info">
                <img class="avatar-xs" src="../../../resource/image/icon-user-settings/mailchimp-icon.svg"
                     alt="Biểu tượng Mailchimp">
                <div class="account-details">
                    <h4>Mailchimp</h4>
                    <p>Dịch vụ tiếp thị email</p>
                </div>
                <div class="switch">
                    <div class="slider"></div>
                </div>
            </div>

        </div>
        <!-- Social Accounts Section -->
        <div class="content social-accounts" id="social-accounts">
            <h3>Tài khoản mạng xã hội</h3>
            <p>Các tính năng tích hợp từ các tài khoản này giúp bạn dễ dàng kết nối với bạn bè và chia sẻ cập nhật.</p>

            <div class="account-info">
                <div class="account-details">
                    <i class="fab fa-twitter account-icon"></i>
                    <div>
                        <strong>Twitter</strong>
                        <p>Chưa kết nối</p>
                    </div>
                </div>
                <button class="btn btn-connect">Kết nối</button>
            </div>

            <div class="account-info">
                <div class="account-details">
                    <i class="fab fa-facebook account-icon"></i>
                    <div>
                        <strong>Facebook</strong>
                        <p>Chưa kết nối</p>
                    </div>
                </div>
                <button class="btn btn-connect">Kết nối</button>
            </div>

            <div class="account-info">
                <div class="account-details">
                    <i class="fab fa-slack account-icon"></i>
                    <div>
                        <strong>Slack</strong>
                        <p>Chưa kết nối</p>
                    </div>
                </div>
                <button class="btn btn-connect">Kết nối</button>
            </div>

            <div class="account-info">
                <div class="account-details">
                    <i class="fab fa-linkedin account-icon"></i>
                    <div>
                        <strong>LinkedIn</strong>
                        <p>Chưa kết nối</p>
                    </div>
                </div>
                <button class="btn btn-connect">Kết nối</button>
            </div>

            <div class="account-info">
                <div class="account-details">
                    <i class="fab fa-google account-icon"></i>
                    <div>
                        <strong>Google</strong>
                        <p>Chưa kết nối</p>
                    </div>
                </div>
                <button class="btn btn-connect">Kết nối</button>
            </div>
        </div>

                Delete Accounts
                <div class="content delete-account" id="delete-account">
                    <p>Khi bạn xóa tài khoản, bạn sẽ mất quyền truy cập vào tất cả dịch vụ và chúng tôi sẽ xóa vĩnh viễn dữ liệu
                        cá nhân của bạn. Bạn có thể hủy việc xóa trong 14 ngày.</p>

                    <div class="delete-confirmation">
                        <label>
                            <input type="checkbox">
                            Xác nhận rằng tôi muốn xóa tài khoản của mình.
                        </label>
                    </div>

                    <div class="delete-actions">
                        <button class="btn learn-more">Tìm hiểu thêm</button>
                        <button class="btn delete">Xóa</button>
                    </div>
                </div>

    </div>
</div>

</body>    <script src="${pageContext.request.contextPath}/static/style-component/account_settings/account_settings.js"></script>
</html>
