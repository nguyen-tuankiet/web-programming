<%--
  Created by IntelliJ IDEA.
  User: kiet
  Date: 12/27/2024
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style-component/style-admin/Dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

<div class="wrap">

    <div class="title ">
        <h2>Tổng quan tháng 11</h2>
        <!--        <span>Here's what's happening with your store today.</span>-->
    </div>

    <div class="report row mid_align">


        <div class="report_item col">
            <span class="title row">Tổng Doanh Thu
                 <div class="status increase row ">
                    <i class="fa-solid fa-arrow-trend-up"></i>
                    <span>12.2%</span>
                </div>
            </span>
            <span class="value">720,700,000 <span class="f16">VND</span></span>

        </div>

        <!--        <div class="rec_vertical"></div>-->

        <!------------------------------------------------------------------------------------------------------>

        <div class="report_item col">
            <span class="title row">Tổng Lượt Truy Cập
                <div class="status decrease ">
                <i class="fa-solid fa-arrow-trend-down"></i>
                <span>8.2%</span>
            </div>
            </span>
            <span class="value">320   </span>

        </div>

        <!--        <div class="rec_vertical"></div>-->

        <!-------------------------------------------------------------------------------------------------->


        <div class="report_item col">
            <span class="title row ">
                Số Lượng Đơn Hàng
                 <div class="status increase ">
                    <i class="fa-solid fa-arrow-trend-up"></i>
                    <span>4.5%</span>
                </div>

            </span>
            <span class="value">57 </span>

        </div>
        <!--        <div class="rec_vertical"></div>-->

        <!------------------------------------------------------------------------------------------------------------->


        <div class="report_item col">
            <span class="title">
                Khách Hàng mới
                 <div class="status increase ">
                    <i class="fa-solid fa-arrow-trend-up"></i>
                    <span>10.0%</span>
                 </div>

            </span>
            <span class="value">231 </span>

        </div>

        <!------------------------------------------------------------------------------------------------------->
    </div>

    <div id="table_product">

        <table id="table">


            <thead>

            <tr>
                <td>Top</td>
                <td class="product_col">Sản Phẩm</td>
                <td> Lượt Mua</td>
                <td>Còn Lại</td>
                <td>Số Lượt Xem</td>
                <td>Đánh Giá</td>
                <td>Xu Hướng</td>
            </tr>

            </thead>


            <tbody>

            <tr class="product_item">
                <td class="rank top1">
                    <i class="fa-solid fa-medal fa-xl" style="color: #FFD43B;"></i></td>
                <td class="product">
                    <img class="product_image" src="${pageContext.request.contextPath}/static/image/tulanh2.jpg" height="467" width="700"/>
                    <span class="product_name">Tủ Lạnh 4 Cửa RF48A4000B4</span>
                </td>
                <td class="sold">20</td>
                <td class="remaining ">200</td>
                <td class="view">5034</td>
                <td class="rating">
                    4.8
                    <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                </td>

                <td class=" wrap_trend_up ">
                    <i class="fa-solid fa-arrow-trend-up"></i>
                    <span>4.5%</span>
                </td>

            </tr>

            <!---------------------------------------------------------------------->

            <tr class="product_item">
                <td class=" rank top2">
                    <i class="fa-solid fa-medal fa-xl" style="color: #b3b2ad;"></i>
                </td>
                <td class="product">
                    <img class="product_image" src=""${pageContext.request.contextPath}/static/image/tulanh2.jpg" height="467" width="700"/>
                    <span class="product_name">Tủ Lạnh 4 Cửa RF48A4000B4</span>
                </td>
                <td class="sold">43</td>
                <td class="remaining">147</td>
                <td class="view">4934</td>
                <td class="rating">
                    4.3
                    <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                </td>
                <td class=" wrap_trend_down ">
                    <i class="fa-solid fa-arrow-trend-down"></i>
                    <span>0.9%</span>
                </td>
            </tr>

            <!-------------------------------------------------------------------------------------->
            <tr class="product_item">
                <td class=" rank top3">
                    <i class="fa-solid fa-medal fa-xl" style="color: #a86b00;"></i>
                </td>
                <td class="product">
                    <img class="product_image" src=""${pageContext.request.contextPath}/static/image/tulanh2.jpg" height="467" width="700"/>
                    <span class="product_name">Tủ Lạnh 4 Cửa RF48A4000B4</span>
                </td>
                <td class="sold">12</td>
                <td class="remaining">120</td>
                <td class="view">3431</td>
                <td class="rating x">
                    4.4
                    <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                </td>

                <td class=" wrap_trend_up ">
                    <i class="fa-solid fa-arrow-trend-up"></i>
                    <span>3.5%</span>
                </td>
            </tr>

            <!-------------------------------------------------------------->

            <tr class="product_item">
                <td class="rank">4</td>
                <td class="product">
                    <img class="product_image" src=""${pageContext.request.contextPath}/static/image/tulanh2.jpg" height="467" width="700"/>
                    <span class="product_name">Tủ Lạnh 4 Cửa RF48A4000B4</span>
                </td>
                <td class="sold">23</td>
                <td class="remaining">247</td>
                <td class="view">2131</td>
                <td class="rating ">
                    3.9
                    <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                </td>


                <td class=" wrap_trend_down ">
                    <i class="fa-solid fa-arrow-trend-down"></i>
                    <span>1.5%</span>
                </td>
            </tr>

            <!-- ------------------------------------------------------------------------------------------->


            <tr class="product_item">
                <td class="rank">5</td>
                <td class="product">
                    <img class="product_image" src=""${pageContext.request.contextPath}/static/image/tulanh2.jpg" height="467" width="700"/>
                    <span class="product_name">Tủ Lạnh 4 Cửa RF48A4000B4</span>
                </td>
                <td class="sold">23</td>
                <td class="remaining">247</td>
                <td class="view">2131</td>
                <td class="rating ">
                    4.1
                    <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                </td>


                <td class=" wrap_trend_down ">
                    <i class="fa-solid fa-arrow-trend-down"></i>
                    <span>8.5%</span>
                </td>
            </tr>


            <!-- ------------------------------------------------------------------------------------------->


            <tr class="product_item">
                <td class="rank">6</td>
                <td class="product">
                    <img class="product_image" src=""${pageContext.request.contextPath}/static/image/tulanh2.jpg" height="467" width="700"/>
                    <span class="product_name">Tủ Lạnh 4 Cửa RF48A4000B4</span>
                </td>
                <td class="sold">23</td>
                <td class="remaining">247</td>
                <td class="view">2131</td>
                <td class="rating ">
                    4.6
                    <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                </td>


                <td class=" wrap_trend_up ">
                    <i class="fa-solid fa-arrow-trend-up"></i>
                    <span>4.5%</span>
                </td>
            </tr>



            <!-- ------------------------------------------------------------------------------------------->


            <tr class="product_item">
                <td class="rank">7</td>
                <td class="product">
                    <img class="product_image" src=""${pageContext.request.contextPath}/static/image/tulanh2.jpg" height="467" width="700"/>
                    <span class="product_name">Tủ Lạnh 4 Cửa RF48A4000B4</span>
                </td>
                <td class="sold">23</td>
                <td class="remaining">247</td>
                <td class="view">2131</td>
                <td class="rating ">
                    4.6
                    <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                </td>


                <td class=" wrap_trend_up ">
                    <i class="fa-solid fa-arrow-trend-up"></i>
                    <span>4.5%</span>
                </td>
            </tr>



            <!-- ------------------------------------------------------------------------------------------->


            <tr class="product_item">
                <td class="rank">8</td>
                <td class="product">
                    <img class="product_image" src=""${pageContext.request.contextPath}/static/image/tulanh2.jpg" height="467" width="700"/>
                    <span class="product_name">Tủ Lạnh 4 Cửa RF48A4000B4</span>
                </td>
                <td class="sold">23</td>
                <td class="remaining">247</td>
                <td class="view">2131</td>
                <td class="rating ">
                    4.6
                    <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                </td>


                <td class=" wrap_trend_up ">
                    <i class="fa-solid fa-arrow-trend-up"></i>
                    <span>4.5%</span>
                </td>
            </tr>




            <!-- ------------------------------------------------------------------------------------------->


            <tr class="product_item">
                <td class="rank">9</td>
                <td class="product">
                    <img class="product_image" src=""${pageContext.request.contextPath}/static/image/tulanh2.jpg" height="467" width="700"/>
                    <span class="product_name">Tủ Lạnh 4 Cửa RF48A4000B4</span>
                </td>
                <td class="sold">23</td>
                <td class="remaining">247</td>
                <td class="view">2131</td>
                <td class="rating ">
                    4.6
                    <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                </td>


                <td class=" wrap_trend_up ">
                    <i class="fa-solid fa-arrow-trend-up"></i>
                    <span>4.5%</span>
                </td>
            </tr>




            <!-- ------------------------------------------------------------------------------------------->


            <tr class="product_item">
                <td class="rank">10</td>
                <td class="product">
                    <img class="product_image" src=""${pageContext.request.contextPath}/static/image/tulanh2.jpg" height="467" width="700"/>
                    <span class="product_name">Tủ Lạnh 4 Cửa RF48A4000B4</span>
                </td>
                <td class="sold">23</td>
                <td class="remaining">247</td>
                <td class="view">2131</td>
                <td class="rating ">
                    4.6
                    <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                </td>


                <td class=" wrap_trend_up ">
                    <i class="fa-solid fa-arrow-trend-up"></i>
                    <span>4.5%</span>
                </td>
            </tr>


            </tbody>

        </table>


    </div>


</div>

</body>
</html>