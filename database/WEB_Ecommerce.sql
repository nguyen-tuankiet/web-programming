/*
 Navicat Premium Dump SQL

 Source Server         : LTW
 Source Server Type    : MySQL
 Source Server Version : 100428 (10.4.28-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : WEB_Ecommerce

 Target Server Type    : MySQL
 Target Server Version : 100428 (10.4.28-MariaDB)
 File Encoding         : 65001

 Date: 27/12/2024 18:17:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `commune` varchar(100) DEFAULT NULL,
  `detail` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `isDefault` tinyint(1) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `address_ibfk_1` (`userId`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of address
-- ----------------------------
BEGIN;
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (1, 1, 'Hà Nội', 'Cầu Giấy', 'Dịch Vọng', 'Số 10, Ngõ 2, Phố Nguyễn Khánh Toàn', 'Nguyen Thi Mai', '0987654321', 1, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (2, 1, 'Hà Nội', 'Ba Đình', 'Phúc Xá', 'Số 15, Phố Đội Cấn', 'Nguyen Thi Mai', '0987654321', 0, 'Office');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (3, 2, 'Hồ Chí Minh', 'Quận 1', 'Bến Nghé', 'Số 20, Đường Lê Thánh Tôn', 'Tran Minh Hoang', '0908765432', 0, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (4, 2, 'Hồ Chí Minh', 'Quận 5', 'Phường 9', 'Số 50, Đường Nguyễn Trãi', 'Tran Minh Hoang', '0908765432', 1, 'Office');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (5, 3, 'Đà Nẵng', 'Hải Châu', 'Phước Ninh', 'Số 5, Đường Nguyễn Văn Linh', 'Le Thi Lan', '0912345678', 1, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (6, 3, 'Đà Nẵng', 'Liên Chiểu', 'Hòa Khánh Nam', 'Số 30, Đường Tôn Đức Thắng', 'Le Thi Lan', '0912345678', 0, 'Office');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (7, 4, 'Hải Phòng', 'Ngô Quyền', 'Máy Tơ', 'Số 12, Đường Trần Phú', 'Pham Minh Tuan', '0978123456', 1, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (8, 5, 'Hà Nội', 'Long Biên', 'Gia Thụy', 'Số 50, Ngõ 1, Phố Ngọc Lâm', 'Nguyen Hoang Nam', '0934567890', 0, 'Office');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (9, 6, 'Bình Dương', 'Thuận An', 'An Phú', 'Số 8, Đường Nguyễn Văn Tiết', 'Nguyen Thi Bao', '0987123456', 1, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (10, 6, 'Bình Dương', 'Dĩ An', 'An Bình', 'Số 25, Đường Lê Văn Lương', 'Nguyen Thi Bao', '0987123456', 0, 'Office');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (11, 7, 'Hồ Chí Minh', 'Quận 3', 'Phường 12', 'Số 100, Đường Cách Mạng Tháng 8', 'Tran Quang Hieu', '0901234567', 1, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (12, 7, 'Hồ Chí Minh', 'Quận 2', 'Thảo Điền', 'Số 8, Đường Nguyễn Văn Hưởng', 'Tran Quang Hieu', '0901234567', 0, 'Office');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (13, 8, 'Đà Nẵng', 'Liên Chiểu', 'Hòa Khánh Nam', 'Số 30, Đường Tôn Đức Thắng', 'Le Thi Mai Lan', '0913456789', 1, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (14, 9, 'Hà Nội', 'Ba Đình', 'Phúc Xá', 'Số 15, Phố Đội Cấn', 'Pham Quoc Duy', '0923456789', 0, 'Office');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (15, 9, 'Hà Nội', 'Hoàn Kiếm', 'Hàng Bông', 'Số 12, Phố Hàng Bông', 'Pham Quoc Duy', '0923456789', 1, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (16, 10, 'Hồ Chí Minh', 'Quận 7', 'Tân Hưng', 'Số 25, Đường Huỳnh Tấn Phát', 'Nguyen Thi Thanh', '0935678901', 0, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (17, 10, 'Hồ Chí Minh', 'Quận 9', 'Hiệp Phú', 'Số 10, Đường Lê Văn Việt', 'Nguyen Thi Thanh', '0935678901', 1, 'Office');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (18, 11, 'Bình Định', 'Quy Nhơn', 'Nguyễn Văn Cừ', 'Số 8, Đường Lê Lợi', 'Vu Minh Tuan', '0988123456', 0, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (19, 12, 'Hà Nội', 'Hoàng Mai', 'Lĩnh Nam', 'Số 22, Ngõ 12, Phố Giáp Bát', 'Nguyen Minh Tu', '0917676543', 1, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (20, 13, 'Hồ Chí Minh', 'Quận 10', 'Phường 4', 'Số 15, Đường 3/2', 'Do Thi Lan', '0932134567', 0, 'Office');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (21, 14, 'Hà Nội', 'Đống Đa', 'Khâm Thiên', 'Số 35, Phố Khâm Thiên', 'Hoang Minh Tien', '0912345678', 1, 'Home');
INSERT INTO `address` (`id`, `userId`, `province`, `district`, `commune`, `detail`, `name`, `phone`, `isDefault`, `type`) VALUES (27, 2, 'Hồ Chí Minh', 'TP.Thủ Đức', 'Linh Trung', '75/37, Linh Trung', 'Đỗ Thị Thừa', '99999113', 1, 'Home');
COMMIT;

-- ----------------------------
-- Table structure for attribute
-- ----------------------------
DROP TABLE IF EXISTS `attribute`;
CREATE TABLE `attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_attribute_1` (`categoryId`),
  CONSTRAINT `fk_attribute_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of attribute
-- ----------------------------
BEGIN;
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (1, 1, 'Năm sản xuất');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (2, 1, 'Mức tiêu thụ điện');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (3, 1, 'Kích thước');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (4, 1, 'Khối lượng');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (5, 1, 'Thương hiệu');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (6, 1, 'Màu sắc');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (7, NULL, 'Kiểu tủ');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (8, NULL, 'Dung tích');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (9, NULL, 'Tính năng làm lạnh');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (10, NULL, 'Đặc điểm ngăn lạnh');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (11, NULL, 'Đặc điểm ngăn đông');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (12, NULL, 'Tính năng chung');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (13, 2, 'Lồng giặt');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (14, 2, 'Loại máy giặt');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (15, 2, 'Khối lượng giặt');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (16, 2, 'Kiểu động cơ');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (17, 2, 'Tốc độ quay tối đa');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (18, 2, 'Công nghệ giặt');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (19, 3, 'Diện tích lọc');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (20, 3, 'Mức độ âm thanh');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (21, 3, 'Công nghệ lọc');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (22, 3, 'Loại màn lọc');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (23, 4, 'Pin');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (24, 4, 'Đầu chổi');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (25, 4, 'Hệ thống lọc');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (26, 4, 'Mức độ âm thanh');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (27, 4, 'Dung tích chứa bụi');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (28, 5, 'Chế độ rửa');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (29, 5, 'Độ ồn');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (30, 5, 'Mức tiêu thụ nước');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (31, 5, 'Chế độ khóa');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (32, 5, 'Bảng điều khiển');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (33, 5, 'Chế độ hút mùi');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (34, 5, 'Bộ lọc');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (35, 5, 'Kiểu lắp đặt');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (36, 6, 'Hệ thống làm lạnh');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (37, 6, 'Chế độ làm lạnh');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (38, 6, 'Máy nén');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (39, 6, 'Mức độ âm thanh');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (40, 6, 'Lọc Sạch Không Khí');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (41, 6, 'Chiều Dài Ống Dẫn (Tối Đa, m)');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (42, 6, 'Chiều Cao Ống Dẫn (Tối Đa, m)');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (43, 6, 'Lượng Gas Cần Nạp (kg)');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (44, 6, 'Kích thước cục nóng');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (45, 6, 'Kích thước cục lạnh');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (46, 6, 'Khối lượng cục lạnh');
INSERT INTO `attribute` (`id`, `categoryId`, `name`) VALUES (47, 6, 'Khối lượng cục nóng');
COMMIT;

-- ----------------------------
-- Table structure for banners
-- ----------------------------
DROP TABLE IF EXISTS `banners`;
CREATE TABLE `banners` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imageId` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `image_id` (`imageId`),
  CONSTRAINT `banners_ibfk_1` FOREIGN KEY (`imageId`) REFERENCES `image` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of banners
-- ----------------------------
BEGIN;
INSERT INTO `banners` (`id`, `imageId`, `status`, `startDate`, `endDate`) VALUES (1, 21, 'Tủ lạnh số 1 Thế giới và Việt Nam', '2024-11-01', '2024-12-31');
INSERT INTO `banners` (`id`, `imageId`, `status`, `startDate`, `endDate`) VALUES (2, 25, 'Máy giặt thông minh', '2024-10-01', '2024-10-31');
INSERT INTO `banners` (`id`, `imageId`, `status`, `startDate`, `endDate`) VALUES (3, 26, 'Máy lạnh công nghệ cao', '2024-11-15', '2025-01-15');
INSERT INTO `banners` (`id`, `imageId`, `status`, `startDate`, `endDate`) VALUES (4, 27, 'Dụng cụ nhà bếp hiện đại', '2024-12-01', '2025-01-31');
COMMIT;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of brand
-- ----------------------------
BEGIN;
INSERT INTO `brand` (`id`, `name`) VALUES (1, 'Samsung');
INSERT INTO `brand` (`id`, `name`) VALUES (2, 'LG');
INSERT INTO `brand` (`id`, `name`) VALUES (3, 'Panasonic');
INSERT INTO `brand` (`id`, `name`) VALUES (4, 'Philips');
INSERT INTO `brand` (`id`, `name`) VALUES (5, 'Toshiba');
INSERT INTO `brand` (`id`, `name`) VALUES (6, 'Sony');
INSERT INTO `brand` (`id`, `name`) VALUES (7, 'Electrolux');
INSERT INTO `brand` (`id`, `name`) VALUES (8, 'Sharp');
INSERT INTO `brand` (`id`, `name`) VALUES (9, 'Whirlpool');
INSERT INTO `brand` (`id`, `name`) VALUES (10, 'Bosch');
INSERT INTO `brand` (`id`, `name`) VALUES (11, 'Daikin');
INSERT INTO `brand` (`id`, `name`) VALUES (12, 'Mitsubishi');
INSERT INTO `brand` (`id`, `name`) VALUES (13, 'Sunhouse');
INSERT INTO `brand` (`id`, `name`) VALUES (14, 'Ariston');
INSERT INTO `brand` (`id`, `name`) VALUES (15, 'KDK');
INSERT INTO `brand` (`id`, `name`) VALUES (16, 'Sanaky');
INSERT INTO `brand` (`id`, `name`) VALUES (17, 'Bluestone');
INSERT INTO `brand` (`id`, `name`) VALUES (18, 'Kangaroo');
INSERT INTO `brand` (`id`, `name`) VALUES (19, 'Daikin');
INSERT INTO `brand` (`id`, `name`) VALUES (20, 'Sharp');
COMMIT;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `duration` date DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `isDefault` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`userId`),
  CONSTRAINT `card_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of card
-- ----------------------------
BEGIN;
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (1, 1, '2024-12-31', 'Visa', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (2, 2, '2025-01-15', 'MasterCard', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (3, 3, '2025-02-20', 'Visa', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (4, 4, '2024-11-30', 'MasterCard', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (5, 5, '2025-03-10', 'Visa', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (6, 6, '2024-12-15', 'MasterCard', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (7, 7, '2024-11-25', 'Visa', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (8, 8, '2025-01-20', 'MasterCard', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (9, 9, '2025-02-25', 'Visa', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (10, 10, '2024-11-10', 'MasterCard', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (11, 11, '2025-03-05', 'Visa', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (12, 12, '2024-12-10', 'MasterCard', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (13, 13, '2025-01-30', 'Visa', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (14, 14, '2024-11-05', 'MasterCard', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (15, 15, '2024-12-05', 'Visa', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (16, 16, '2025-02-28', 'MasterCard', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (17, 17, '2024-11-15', 'Visa', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (18, 18, '2024-12-25', 'MasterCard', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (19, 19, '2025-01-25', 'Visa', NULL);
INSERT INTO `card` (`id`, `userId`, `duration`, `type`, `isDefault`) VALUES (20, 20, '2025-02-15', 'MasterCard', NULL);
COMMIT;

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of categories
-- ----------------------------
BEGIN;
INSERT INTO `categories` (`id`, `name`) VALUES (8, 'Khác');
INSERT INTO `categories` (`id`, `name`) VALUES (2, 'Máy giặt');
INSERT INTO `categories` (`id`, `name`) VALUES (3, 'Máy hút bụi');
INSERT INTO `categories` (`id`, `name`) VALUES (4, 'Máy lọc không khí ');
INSERT INTO `categories` (`id`, `name`) VALUES (7, 'SmartThings');
INSERT INTO `categories` (`id`, `name`) VALUES (6, 'Thiết bị nhà bếp');
INSERT INTO `categories` (`id`, `name`) VALUES (1, 'Tủ lạnh');
INSERT INTO `categories` (`id`, `name`) VALUES (5, 'Điều hòa không khí');
COMMIT;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of image
-- ----------------------------
BEGIN;
INSERT INTO `image` (`id`, `url`) VALUES (1, 'https://images.samsung.com/is/image/samsung/p6pim/vn/rs62r5001m9-sv/gallery/vn-side-by-side-rs62r50014g-rs62r5001m9-sv-536800320?$684_547_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (2, 'https://images.samsung.com/is/image/samsung/p6pim/vn/rf48a4000b4-sv/gallery/vn-twin-cooling-plus-rf48a4000b4-sv-thumb-514890317?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (3, 'https://images.samsung.com/is/image/samsung/p6pim/vn/rs62r5001m9-sv/gallery/vn-side-by-side-rs62r50014g-rs62r5001m9-sv-thumb-536800321?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (4, 'https://images.samsung.com/is/image/samsung/p6pim/vn/rt31cb56248asv/gallery/vn-top-mount-freezer-bespoke-rt31cb56248asv-thumb-536476428?$252_252_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (5, 'https://images.samsung.com/is/image/samsung/p6pim/vn/ww95ta046ax-sv/gallery/vn-front-loading-washer-ww80ta046aeef-382666-ww95ta046ax-sv-thumb-536830218?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (6, 'https://images.samsung.com/is/image/samsung/p6pim/vn/ww10tp44dsb-sv/gallery/vn-front-loading-washer-ww10tp44dsxfq-ww10tp44dsb-sv-thumb-536820962?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (7, 'https://images.samsung.com/is/image/samsung/p6pim/vn/wd12db7b85gbsv/gallery/vn-wd7400d-wd12db7b85gbsv-thumb-542083171?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (8, 'https://images.samsung.com/is/image/samsung/p6pim/vn/wd25db8995bzsv/gallery/vn-wd8000dk-wd25db8995bzsv-thumb-543263643?$252_252_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (9, 'https://images.samsung.com/is/image/samsung/p6pim/vn/vs15a6031r1-sv/gallery/vn-vc-stick-vs6700-vs15a6031r1-sv-thumb-422235772?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (10, 'https://images.samsung.com/is/image/samsung/p6pim/vn/vs20b95993b-sv/gallery/vn-bespoke-jet-plus-midnight-blue-vs20b95993b-sv-thumb-539087656?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (11, 'https://images.samsung.com/is/image/samsung/p6pim/vn/vr05r5050wk-sv/gallery/vn-robot-vr05r5050wk-vr05r5050wk-sv-thumb-519428264?$252_252_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (12, 'https://images.samsung.com/is/image/samsung/vn-canister-vc3100m-vc18m3110vb-sv-frontblue-thumb-63146383?$252_252_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (13, 'https://images.samsung.com/is/image/samsung/p6pim/vn/dw60cg550fsgsv/gallery/vn-dw6500amdw60cg550fsgsv-dw60cg550fsgsv-thumb-539468558?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (14, 'https://images.samsung.com/is/image/samsung/p6pim/vn/nz64b5066fk-sv/gallery/vn-nz8500bm-nz64b5066kk-nz64b5066fk-sv-thumb-539268548?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (15, 'https://images.samsung.com/is/image/samsung/p6pim/vn/nv7b41201as-sv/gallery/vn-nv7000b-nb7b41301as-nv7b41201as-sv-thumb-539241133?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (16, 'https://images.samsung.com/is/image/samsung/p6pim/vn/nk24m5070bs-ur/gallery/vn-nk7000mm-nk24m5060ss-nk24m5070bs-ur-thumb-539268565?$252_252_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (17, 'https://images.samsung.com/is/image/samsung/p6pim/vn/f-ar10cyeca-4g/gallery/vn-wall-mount-f-ar10cyeca-4g-front-white-thumb-538643740?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (18, 'https://images.samsung.com/is/image/samsung/p6pim/vn/f-ar13dyhza24/gallery/vn-wall-mount-f-ar13dyhza24-m-t-tr--c-thumb-540239600?$172_172_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (19, 'https://images.samsung.com/is/image/samsung/p6pim/vn/f-ac120tn4pkc/gallery/vn-ceiling-air-conditioner-f-ac120tn4pkc-front-white-thumb-536716711?$252_252_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (20, 'https://images.samsung.com/is/image/samsung/p6pim/vn/f-ar24cyfca-4g/gallery/vn-wall-mount-f-ar24cyfca-4g-front-white-thumb-538646119?$252_252_PNG$');
INSERT INTO `image` (`id`, `url`) VALUES (21, 'https://images.samsung.com/is/image/samsung/assets/vn/refrigerators/top-mount-freezer/optimal-fresh-plus/2410/1440x640_PC.jpg?imwidth=1366');
INSERT INTO `image` (`id`, `url`) VALUES (22, 'https://images.samsung.com/is/image/samsung/assets…n/home/2024/PC_AChomepage.jpg?$LazyLoad_Home_JPG$');
INSERT INTO `image` (`id`, `url`) VALUES (23, 'https://images.samsung.com/is/image/samsung/assets…/home/2024/PC_1440x810BIC.jpg?$LazyLoad_Home_JPG$');
INSERT INTO `image` (`id`, `url`) VALUES (24, 'https://images.samsung.com/is/image/samsung/assets…n/home/2024/PC_1440x810WM.jpg?$LazyLoad_Home_JPG$');
INSERT INTO `image` (`id`, `url`) VALUES (25, 'https://images.samsung.com/is/image/samsung/assets/vn/home/2024/PC_1440x810WM.jpg?$1440_810_JPG$');
INSERT INTO `image` (`id`, `url`) VALUES (26, 'https://images.samsung.com/is/image/samsung/assets/vn/home/2024/PC_AChomepage.jpg?$1440_810_JPG$');
INSERT INTO `image` (`id`, `url`) VALUES (27, 'https://images.samsung.com/is/image/samsung/assets/vn/home/2024/PC_1440x810BIC.jpg?$1440_810_JPG$');
INSERT INTO `image` (`id`, `url`) VALUES (28, 'https://plus.unsplash.com/premium_photo-1664474619075-644dd191935f?q=80&w=3269&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
INSERT INTO `image` (`id`, `url`) VALUES (29, 'https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg');
INSERT INTO `image` (`id`, `url`) VALUES (30, 'https://gratisography.com/wp-content/uploads/2024/10/gratisography-cool-cat-800x525.jpg');
INSERT INTO `image` (`id`, `url`) VALUES (31, 'https://letsenhance.io/static/03620c83508fc72c6d2b218c7e304ba5/11499/UpscalerAfter.jpg');
INSERT INTO `image` (`id`, `url`) VALUES (32, 'https://images.samsung.com/is/image/samsung/p6pim/vn/rb27n4010bu-sv/gallery/vn-rb4000-bmf-with-optimal-fresh-zone-rb27n4010s8sv-426486-rb27n4010bu-sv-533998006?$684_547_PNG$');
COMMIT;

-- ----------------------------
-- Table structure for option_variant_value
-- ----------------------------
DROP TABLE IF EXISTS `option_variant_value`;
CREATE TABLE `option_variant_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `optionId` int(11) DEFAULT NULL,
  `variantValueId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_option_variant_value_1` (`optionId`),
  KEY `fk_option_variant_value_2` (`variantValueId`),
  CONSTRAINT `fk_option_variant_value_1` FOREIGN KEY (`optionId`) REFERENCES `options` (`id`),
  CONSTRAINT `fk_option_variant_value_2` FOREIGN KEY (`variantValueId`) REFERENCES `variant_value` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of option_variant_value
-- ----------------------------
BEGIN;
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (1, 1, 1);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (2, 1, 5);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (3, 2, 2);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (4, 2, 5);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (5, 3, 3);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (6, 3, 5);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (7, 3, 10);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (8, 1, 11);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (9, 2, 12);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (10, 4, 1);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (11, 4, 6);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (12, 4, 12);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (13, 5, 2);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (14, 5, 6);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (15, 5, 12);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (16, 6, 3);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (17, 6, 15);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (18, 6, 18);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (19, 7, 3);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (20, 7, 15);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (21, 7, 19);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (22, 7, 2);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (23, 7, 14);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (24, 7, 18);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (25, 8, 1);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (26, 8, 13);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (27, 8, 18);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (28, 9, 1);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (29, 9, 13);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (30, 9, 17);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (31, 10, 1);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (32, 10, 20);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (33, 10, 24);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (34, 12, 1);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (35, 12, 22);
INSERT INTO `option_variant_value` (`id`, `optionId`, `variantValueId`) VALUES (36, 13, 3);
COMMIT;

-- ----------------------------
-- Table structure for options
-- ----------------------------
DROP TABLE IF EXISTS `options`;
CREATE TABLE `options` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `options_fk_1` (`productId`),
  CONSTRAINT `options_fk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of options
-- ----------------------------
BEGIN;
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (1, 1, 12000000, 20);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (2, 1, 13000000, 30);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (3, 1, 14000000, 25);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (4, 2, 20000000, 40);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (5, 2, 22000000, 30);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (6, 3, 8000000, 40);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (7, 3, 9000000, 20);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (8, 4, 10000000, 30);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (9, 4, 12000000, 20);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (10, 5, 5000000, 40);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (12, 6, 13000000, 40);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (13, 7, 11000000, 30);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (14, 8, 20000000, 10);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (16, 9, 14000000, 30);
INSERT INTO `options` (`id`, `productId`, `price`, `stock`) VALUES (17, 10, 5000000, 40);
COMMIT;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`orderId`),
  KEY `product_id` (`productId`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
BEGIN;
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (1, 1, 1, 3, 45000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (2, 2, 2, 2, 30000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (3, 3, 3, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (4, 4, 4, 2, 30000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (5, 5, 5, 4, 60000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (6, 6, 6, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (7, 6, 1, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (8, 7, 3, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (9, 7, 9, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (10, 8, 6, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (11, 8, 1, 2, 30000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (12, 4, 7, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (13, 7, 8, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (14, 8, 5, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (15, 8, 6, 2, 30000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (16, 8, 10, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (17, 9, 9, 2, 30000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (18, 9, 8, 1, 15000.00);
INSERT INTO `order_detail` (`id`, `orderId`, `productId`, `quantity`, `total`) VALUES (19, 10, 9, 1, 15000.00);
COMMIT;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createAt` date NOT NULL,
  `paymentStatus` varchar(50) NOT NULL,
  `orderStatus` varchar(50) NOT NULL,
  `userId` int(11) NOT NULL,
  `addressId` int(11) NOT NULL,
  `cardId` int(11) DEFAULT NULL,
  `isCOD` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_fk1` (`userId`),
  KEY `order_fk2` (`addressId`),
  KEY `order_fk3` (`cardId`),
  CONSTRAINT `order_fk1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_fk2` FOREIGN KEY (`addressId`) REFERENCES `address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_fk3` FOREIGN KEY (`cardId`) REFERENCES `card` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (1, '2024-11-03', 'Đã Thanh Toán', 'Đã Gửi', 1, 1, NULL, 1);
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (2, '2024-11-05', 'Đã Thanh Toán', 'Đang Xử Lý', 2, 3, 1, 0);
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (3, '2024-11-03', 'Đã Thanh Toán', 'Đã Gửi', 3, 5, NULL, 1);
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (4, '2024-11-20', 'Đã Thanh Toán', 'Đã Giao', 4, 7, NULL, 1);
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (5, '2024-11-12', 'Đã Hoàn Tiền', 'Đã Giao', 5, 8, NULL, 1);
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (6, '2024-11-30', 'Đã Thanh Toán', 'Đã Giao', 5, 8, 1, 0);
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (7, '2024-11-25', 'Đã Hủy', 'Đã Giao', 7, 10, 1, 0);
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (8, '2024-11-13', 'Đã Thanh Toán', 'Đã Giao', 6, 11, NULL, 1);
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (9, '2024-11-25', 'Đã Hủy', 'Đã Giao', 8, 13, NULL, 1);
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (10, '2024-11-13', 'Đã Thanh Toán', 'Đã Giao', 2, 3, NULL, 1);
INSERT INTO `orders` (`id`, `createAt`, `paymentStatus`, `orderStatus`, `userId`, `addressId`, `cardId`, `isCOD`) VALUES (11, '2024-11-25', 'Chưa thanh toán', 'Đang chuẩn bị', 1, 1, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for product_images
-- ----------------------------
DROP TABLE IF EXISTS `product_images`;
CREATE TABLE `product_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productId` int(11) NOT NULL,
  `imageId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`productId`),
  KEY `image_id` (`imageId`),
  CONSTRAINT `product_images_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE CASCADE,
  CONSTRAINT `product_images_ibfk_2` FOREIGN KEY (`imageId`) REFERENCES `image` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of product_images
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for product_tag
-- ----------------------------
DROP TABLE IF EXISTS `product_tag`;
CREATE TABLE `product_tag` (
  `productId` int(11) NOT NULL,
  `tagId` int(11) NOT NULL,
  PRIMARY KEY (`productId`,`tagId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of product_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sku` varchar(50) NOT NULL,
  `description` text DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT 1,
  `brandId` int(11) DEFAULT NULL,
  `categoryId` int(11) DEFAULT NULL,
  `noOfViews` int(11) DEFAULT 0,
  `noOfSold` int(11) DEFAULT 0,
  `primaryImage` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `products_fk_1` (`categoryId`),
  KEY `product_fk_2` (`brandId`),
  KEY `products_fk3` (`primaryImage`),
  CONSTRAINT `product_fk_2` FOREIGN KEY (`brandId`) REFERENCES `brand` (`id`),
  CONSTRAINT `products_fk3` FOREIGN KEY (`primaryImage`) REFERENCES `image` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `products_fk_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of products
-- ----------------------------
BEGIN;
INSERT INTO `products` (`id`, `name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `primaryImage`) VALUES (1, 'Tủ Lạnh LG Ngăn Đông Dưới Fresh Zone 330L', 'SKU001', 'Ngăn Đông Mềm -1 độ giữ thịt cá tươi không đông đá\r\nCông nghệ làm lạnh vòm All-around Cooling\r\nKhử mùi, lọc không khí bằng than hoạt tính', 1, 1, 1, 232, 120, 1);
INSERT INTO `products` (`id`, `name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `primaryImage`) VALUES (2, 'Tủ Lạnh Samsung 4 Cửa 450L  với Twin Cooling Plus™', 'SKU002', '2 Dàn lạnh giữ thực phẩm tươi ngon lâu hơn\r\nHoàn toàn không lẫn mùi giữa các ngăn\r\nChất liệu thép cao cấp bền đẹp, không bám vân tay', 1, 1, 1, 322, 230, 1);
INSERT INTO `products` (`id`, `name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `primaryImage`) VALUES (3, 'AI Ecobubble™ Máy Giặt Cửa Trước Với Giặt Hơi Nước Diệt Khuẩn', 'SKU003', 'Ecobubble™ giặt sạch sâu, bảo vệ sợi vải\r\nHygiene Steam diệt khuẩn 99,9%, ngừa dị ứng\r\nĐộng cơ Digital Inverter êm ái, bền bỉ 23 năm', 1, 1, 2, 122, 110, NULL);
INSERT INTO `products` (`id`, `name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `primaryImage`) VALUES (4, 'Máy giặt thông minh AI Ecobubble™ với Ngăn giặt xả tự động', 'SKU004', 'AI Control ghi nhớ, đề xuất chế độ giặt\r\nAI Dispenser tự động phân bổ nước giặt/xả\r\nEcobubble™ giặt sạch sâu, bảo vệ sợi vải', 1, 1, 2, 311, 130, NULL);
INSERT INTO `products` (`id`, `name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `primaryImage`) VALUES (5, 'Máy hút bụi không dây Bespoke Jet™ 210W', 'SKU005', 'Lực hút siêu mạnh 210W, hút sạch mọi bề mặt\r\nHệ thống lọc đa lớp lọc 99.999% bụi siêu mịn và các tác nhân gây dị ứng\r\nTrạm làm sạch tự động đổ bụi & sạc pin dễ dàng', 1, 1, 4, 322, 230, NULL);
INSERT INTO `products` (`id`, `name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `primaryImage`) VALUES (6, 'Robot POWERbot-E Hút Bụi Và Lau Nhà 2 Trong 1', 'SKU006', 'Robot 2 trong 1 - Hút bụi và lau nhà cùng lúc\r\nLực hút mạnh mẽ đến 5W\r\nPin hiệu suất cao với 150 phút hút bụi liên tục\r\nHệ thống 4 Cảm biến thông minh', 1, 1, 4, 362, 130, NULL);
INSERT INTO `products` (`id`, `name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `primaryImage`) VALUES (7, 'Điều hòa Inverter AR10CYECAWKNSV WindFree™ PM2.5 ', 'SKU007', '2 Dàn lạnh giữ thực phẩm tươi ngon lâu hơn\r\nHoàn toàn không lẫn mùi giữa các ngăn\r\nChất liệu thép cao cấp bền đẹp, không bám vân tay', 1, 1, 6, 422, 330, NULL);
INSERT INTO `products` (`id`, `name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `primaryImage`) VALUES (8, 'Điều hòa Cassette 4 hướng F-AC140TN4DKC WindFree', 'SKU008', '2 Dàn lạnh giữ thực phẩm tươi ngon lâu hơn\r\nHoàn toàn không lẫn mùi giữa các ngăn\r\nChất liệu thép cao cấp bền đẹp, không bám vân tay', 1, 1, 6, 622, 530, NULL);
INSERT INTO `products` (`id`, `name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `primaryImage`) VALUES (9, 'Máy rửa bát DW60CG550FSGSV Chế độ diệt khuẩn chuyên sâu Hygiene', 'SKU009   ', 'Diệt khuẩn 99.999% với Hygiene Care\r\nCửa mở tự động sấy khô nhanh, ngăn mùi\r\nĐiều chỉnh linh hoạt khay kệ\r\nĐa dạng chương trình rửa', 1, 1, 5, 222, 120, NULL);
INSERT INTO `products` (`id`, `name`, `sku`, `description`, `isActive`, `brandId`, `categoryId`, `noOfViews`, `noOfSold`, `primaryImage`) VALUES (10, 'Lò Nướng Series 4 NV7B41201AS/SV', 'SKU010', '20 chương trình nấu nướng tự động\r\nDung tích cực lớn 76L, nấu nhiều món ngon cùng lúc\r\nCông nghệ Catalytic tự làm sạch lò dễ dàng\r\nKết nối thông minh SmartThings mọi lúc, mọi nơi', 1, 1, 5, 322, 230, NULL);
COMMIT;

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `description` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`userId`),
  KEY `product_id` (`productId`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of review
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of tags
-- ----------------------------
BEGIN;
INSERT INTO `tags` (`id`, `name`) VALUES (6, 'Bán chạy');
INSERT INTO `tags` (`id`, `name`) VALUES (5, 'Giảm giá');
INSERT INTO `tags` (`id`, `name`) VALUES (7, 'Hàng chính hãng');
INSERT INTO `tags` (`id`, `name`) VALUES (4, 'Hot Sale');
INSERT INTO `tags` (`id`, `name`) VALUES (3, 'Khuyến mãi');
INSERT INTO `tags` (`id`, `name`) VALUES (1, 'Sản phẩm mới');
INSERT INTO `tags` (`id`, `name`) VALUES (8, 'Top 10');
INSERT INTO `tags` (`id`, `name`) VALUES (2, 'Ưu đãi');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullName` varchar(255) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatarId` int(11) DEFAULT NULL,
  `status` enum('PENDING','ACTIVE','BANNED','DEACTIVE') NOT NULL DEFAULT 'PENDING',
  `confirmationToken` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `user_fk1` (`avatarId`),
  CONSTRAINT `user_fk1` FOREIGN KEY (`avatarId`) REFERENCES `image` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (1, 'Nguyen Thi Bao', 'Bao Nguyen', '1995-08-11', 'Female', '0911223345', 'bao.nguyen@example.com', 'password103', 28, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (2, 'Tran Quang Hieu', 'Hieu Tran', '1993-04-20', 'Male', '0922334456', 'hieu.tran@example.com', 'password104', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (3, 'Le Thi Mai Lan', 'Mai Lan Le', '1992-07-12', 'Female', '0933445567', 'mailan.le@example.com', 'password105', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (4, 'Pham Quoc Duy', 'Duy Pham', '1991-11-14', 'Male', '0944556678', 'duy.pham@example.com', 'password106', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (5, 'Nguyen Thi Thanh', 'Thanh Nguyen', '1990-02-08', 'Female', '0955667789', 'thanh.nguyen@example.com', 'password107', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (6, 'Vu Minh Tuan', 'Tuan Vu', '1989-06-25', 'Male', '0966778890', 'tuan.vu@example.com', 'password108', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (7, 'Nguyen Minh Tu', 'Tu Nguyen', '1994-09-01', 'Male', '0977889901', 'tu.nguyen@example.com', 'password109', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (8, 'Do Thi Lan', 'Lan Do', '1995-03-15', 'Female', '0988990012', 'lan.do@example.com', 'password110', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (9, 'Hoang Minh Tien', 'Tien Hoang', '1993-12-04', 'Male', '0999001123', 'tien.hoang@example.com', 'password111', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (10, 'Pham Thi Kim', 'Kim Pham', '1987-01-18', 'Female', '0911223345', 'kim.pham@example.com', 'password112', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (11, 'Le Minh Bao', 'Bao Le', '1996-07-23', 'Male', '0922334456', 'bao.le@example.com', 'password113', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (12, 'Truong Thi Thu', 'Thu Truong', '1988-05-10', 'Female', '0933445567', 'thu.truong@example.com', 'password114', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (13, 'Nguyen Thi Hoa', 'Hoa Nguyen', '1990-04-05', 'Female', '0944556678', 'hoa.nguyen@example.com', 'password115', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (14, 'Mai Thi Lan', 'Lan Mai', '1992-08-14', 'Female', '0955667789', 'lan.mai@example.com', 'password116', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (15, 'Bui Minh Tuan', 'Tuan Bui', '1994-06-11', 'Male', '0966778890', 'tuan.bui@example.com', 'password117', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (16, 'Nguyen Thi Tieu', 'Tieu Nguyen', '1993-10-29', 'Female', '0977889901', 'tieu.nguyen@example.com', 'password118', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (17, 'Le Quang Hieu', 'Hieu Le', '1991-03-19', 'Male', '0988990012', 'hieu.le@example.com', 'password119', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (18, 'Pham Thi Mai', 'Mai Pham', '1987-07-30', 'Female', '0999001123', 'mai.pham@example.com', 'password120', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (19, 'Trinh Minh Nam', 'Nam Trinh', '1992-11-05', 'Male', '0911223345', 'nam.trinh@example.com', 'password121', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (20, 'Ho Thi Thu', 'Thu Ho', '1993-02-14', 'Female', '0922334456', 'thu.ho@example.com', 'password122', NULL, 'ACTIVE', NULL);
INSERT INTO `user` (`id`, `fullName`, `displayName`, `birth`, `gender`, `phone`, `email`, `password`, `avatarId`, `status`, `confirmationToken`) VALUES (21, 'Tuan Kiet', 'Kiet Cute', '2004-10-10', 'other', '9999999', 'kiet@gmai.com', 'newPassword', 1, 'ACTIVE', NULL);
COMMIT;

-- ----------------------------
-- Table structure for variant
-- ----------------------------
DROP TABLE IF EXISTS `variant`;
CREATE TABLE `variant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryId` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`categoryId`),
  CONSTRAINT `fk_category` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of variant
-- ----------------------------
BEGIN;
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (1, NULL, 'Màu sắc');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (2, 1, 'Kiểu tủ');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (3, 1, 'Dung tích');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (4, 2, 'Loại máy giặc');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (5, 2, 'Khối lượng giặc');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (6, 3, 'Loại máy hút bụi');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (7, 3, 'Công suất hút');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (8, 3, 'Chức năng');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (9, 4, 'Diện tích lọc');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (10, 4, 'Công nghệ lọc');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (11, 5, 'Loại điều hòa');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (12, 5, 'Công suất làm mát');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (13, 5, 'Công nghệ làm lạnh');
INSERT INTO `variant` (`id`, `categoryId`, `name`) VALUES (14, 6, 'Loại thiết bị');
COMMIT;

-- ----------------------------
-- Table structure for variant_value
-- ----------------------------
DROP TABLE IF EXISTS `variant_value`;
CREATE TABLE `variant_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `variantId` int(11) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_variant_value_1` (`variantId`),
  CONSTRAINT `fk_variant_value_1` FOREIGN KEY (`variantId`) REFERENCES `variant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- ----------------------------
-- Records of variant_value
-- ----------------------------
BEGIN;
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (1, 1, 'Bạc');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (2, 1, 'Đen');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (3, 1, 'Trắng');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (4, 2, '4 Cửa');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (5, 2, 'Family Hub');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (6, 2, 'Side by Side');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (7, 2, '2 Cửa - Ngăn đông dưới');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (8, 2, '2 Cửa - Ngăn đông trên');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (9, 3, 'Dưới 300L');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (10, 3, 'Từ 300L - 400L');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (11, 3, 'Từ 400L - 500L');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (12, 3, 'Trên 500L');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (13, 4, 'Máy giặc kèm sấy');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (14, 4, 'Máy giặc cửa trên');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (15, 4, 'Máy giặc cửa trước');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (16, 4, 'Máy sấy');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (17, 5, 'Từ 8-9 KG');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (18, 5, 'Từ 9-10 KG');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (19, 5, 'Trên 10 KG');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (20, 6, 'Máy hút bụi không dây');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (21, 6, 'Máy hút bụi dạng hộp');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (22, 6, 'Robot hút bụi');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (23, 7, 'Dưới 150W');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (24, 7, 'Từ 150W - 300W');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (25, 7, 'Trên 300W');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (26, 8, 'Hút bụi');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (27, 8, 'Lau nhà');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (28, 8, 'Tự giặc sấy giẻ');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (29, 9, 'Dưới 40m2');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (30, 9, 'Từ 40m2 - 55m2');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (31, 9, 'Trên 55m2');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (32, 10, 'Màng lọc bụi siêu mịn');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (33, 10, 'Màng lọc khử mùi, khí độc');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (34, 10, 'Kháng khuẩn');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (35, 11, 'Điều hòa treo tường');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (36, 11, 'Điều hòa cục bộ thương mại');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (37, 12, '9.000 Btu/h');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (38, 12, '12.000 Btu/h');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (39, 12, '18.000 Btu/h');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (40, 12, '24.000 Btu/h');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (41, 12, '34.000 Btu/h');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (42, 12, '41.000 Btu/h');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (43, 12, '48.000 Btu/h');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (44, 13, 'WinFee lạnh tức thì');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (45, 13, 'Bộ lọc mịn PM 2.5');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (46, 13, 'Lưới lọc kháng khuẩn');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (47, 13, 'Digital Inverter Boost');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (48, 13, 'Làm lạnh thông minh');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (49, 13, 'Tự động làm sạch');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (50, 14, 'Lò nướng');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (51, 14, 'Bếp từ');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (52, 14, 'Máy hút mùi');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (53, 14, 'Lò vi sóng');
INSERT INTO `variant_value` (`id`, `variantId`, `value`) VALUES (54, 14, 'Máy rửa bác');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
