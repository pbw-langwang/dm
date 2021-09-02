/*
 Navicat MySQL Data Transfer

 Source Server         : pbw
 Source Server Type    : MySQL
 Source Server Version : 50022
 Source Host           : localhost:3306
 Source Schema         : tx_dm

 Target Server Type    : MySQL
 Target Server Version : 50022
 File Encoding         : 65001

 Date: 24/06/2021 14:06:15
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for actmessage
-- ----------------------------
DROP TABLE IF EXISTS `actmessage`;
CREATE TABLE `actmessage`  (
  `actname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '活动的名称',
  `actimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '活动的展示图片',
  `acturl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '活动的跳转链接',
  `actintroduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '活动的介绍内容',
  `actvip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '活动是否只有vip可以参加',
  `type` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT '' COMMENT '活动的类型',
  `actid` int(25) NOT NULL AUTO_INCREMENT COMMENT '活动的id',
  PRIMARY KEY USING BTREE (`actid`)
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of actmessage
-- ----------------------------
INSERT INTO `actmessage` VALUES ('cs游戏联动', 'game2.jpg', 'https://csgo.wanmei.com/anniversary20', '限量枪支等你来拿！', '0', 2, 17);
INSERT INTO `actmessage` VALUES ('中国加油！！！', 'timg.jpg', 'https://www.sohu.com/a/371379746_120068222', '世界有你的支持，才会更加美好！', '0', 0, 18);
INSERT INTO `actmessage` VALUES ('体验乐花卡', 'hh.jpg', 'https://act-vip-ssl.xunlei.com/pc/vip/2020/lepaysale/index.html', '1元购价值9928元礼包', '0', 2, 19);
INSERT INTO `actmessage` VALUES ('全新斗帝大陆', 'game.jpg', 'https://www.haodewap.net/article/9957.html', '全新内容，全新体验', '0', 1, 20);
INSERT INTO `actmessage` VALUES ('校园动漫节运动会', 'school.jpg', 'http://www.onlyjp.cn/t/xiaoyuanyundonghui', '丰富的校园活动，乐趣多多的互动游戏等你来', '0', 0, 22);

-- ----------------------------
-- Table structure for adminmessage
-- ----------------------------
DROP TABLE IF EXISTS `adminmessage`;
CREATE TABLE `adminmessage`  (
  `adminname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `adminpwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `adminimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `admintype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ''
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of adminmessage
-- ----------------------------

-- ----------------------------
-- Table structure for attention
-- ----------------------------
DROP TABLE IF EXISTS `attention`;
CREATE TABLE `attention`  (
  `aid` int(5) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `authorid` int(1) NOT NULL DEFAULT '' COMMENT '作者的id号',
  `userid` int(1) NOT NULL DEFAULT '' COMMENT '用户的id号',
  PRIMARY KEY USING BTREE (`aid`)
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of attention
-- ----------------------------
INSERT INTO `attention` VALUES (4, 1, 2);
INSERT INTO `attention` VALUES (5, 2, 2);
INSERT INTO `attention` VALUES (7, 1, 3);
INSERT INTO `attention` VALUES (8, 3, 3);

-- ----------------------------
-- Table structure for contentclass
-- ----------------------------
DROP TABLE IF EXISTS `contentclass`;
CREATE TABLE `contentclass`  (
  `classId` int(20) NOT NULL AUTO_INCREMENT COMMENT '分类的id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '分类的名称',
  PRIMARY KEY USING BTREE (`classId`)
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of contentclass
-- ----------------------------
INSERT INTO `contentclass` VALUES (0, '精选');
INSERT INTO `contentclass` VALUES (1, '少年魂');
INSERT INTO `contentclass` VALUES (2, '少女心');
INSERT INTO `contentclass` VALUES (3, '兄弟情');
INSERT INTO `contentclass` VALUES (4, '日漫');
INSERT INTO `contentclass` VALUES (5, '正能量');
INSERT INTO `contentclass` VALUES (6, 'ice+');

-- ----------------------------
-- Table structure for contenthistory
-- ----------------------------
DROP TABLE IF EXISTS `contenthistory`;
CREATE TABLE `contenthistory`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `contentname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '观看的内容名称',
  `contentSeeTime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '观看的时间',
  `historyid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY USING BTREE (`historyid`)
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of contenthistory
-- ----------------------------
INSERT INTO `contenthistory` VALUES ('admin', '犬夜叉', '2021-06-22 21:47:07', 11);
INSERT INTO `contenthistory` VALUES ('rx', '犬夜叉', '2021-06-23 14:00:01', 12);
INSERT INTO `contenthistory` VALUES ('rx', '海绵宝宝', '2021-06-23 14:00:04', 13);
INSERT INTO `contenthistory` VALUES ('admin', '犬夜叉', '2021-06-23 14:48:09', 14);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦4', '2021-06-23 14:49:22', 15);
INSERT INTO `contenthistory` VALUES ('admin', '犬夜叉', '2021-06-23 14:51:06', 16);
INSERT INTO `contenthistory` VALUES ('admin', 'aaa', '2021-06-23 15:24:52', 17);
INSERT INTO `contenthistory` VALUES ('admin', '海贼王', '2021-06-23 15:26:32', 18);
INSERT INTO `contenthistory` VALUES ('admin', '海绵宝宝', '2021-06-23 15:26:35', 19);
INSERT INTO `contenthistory` VALUES ('admin', '死神', '2021-06-23 15:26:37', 20);
INSERT INTO `contenthistory` VALUES ('admin', '妖精的尾巴', '2021-06-23 15:26:39', 21);
INSERT INTO `contenthistory` VALUES ('admin', '东京喰种', '2021-06-23 15:26:41', 22);
INSERT INTO `contenthistory` VALUES ('admin', 'aaa', '2021-06-23 15:42:44', 23);
INSERT INTO `contenthistory` VALUES ('admin', 'aaa', '2021-06-23 17:37:06', 24);
INSERT INTO `contenthistory` VALUES ('admin', 'aaa', '2021-06-23 19:31:32', 25);
INSERT INTO `contenthistory` VALUES ('admin', '犬夜叉', '2021-06-23 19:33:43', 26);
INSERT INTO `contenthistory` VALUES ('admin', '海贼王', '2021-06-23 19:35:22', 27);
INSERT INTO `contenthistory` VALUES ('admin', '犬夜叉', '2021-06-23 20:16:17', 28);
INSERT INTO `contenthistory` VALUES ('admin', 'aaa', '2021-06-23 20:20:00', 29);
INSERT INTO `contenthistory` VALUES ('admin', '犬夜叉', '2021-06-23 20:20:07', 30);
INSERT INTO `contenthistory` VALUES ('admin', '犬夜叉', '2021-06-23 20:21:31', 31);
INSERT INTO `contenthistory` VALUES ('admin', '犬夜叉', '2021-06-23 20:23:38', 32);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦3', '2021-06-23 20:23:56', 33);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦4', '2021-06-23 20:24:23', 34);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦4', '2021-06-23 20:24:33', 35);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦4', '2021-06-23 20:25:32', 36);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦2', '2021-06-23 20:37:05', 37);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦4', '2021-06-23 20:38:58', 38);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦4', '2021-06-23 20:39:00', 39);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦4', '2021-06-23 20:39:17', 40);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦4', '2021-06-23 20:39:32', 41);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦3', '2021-06-23 20:39:35', 42);
INSERT INTO `contenthistory` VALUES ('admin', '哆啦A梦4', '2021-06-23 20:41:31', 43);
INSERT INTO `contenthistory` VALUES ('admin', '海贼王', '2021-06-23 23:20:49', 44);
INSERT INTO `contenthistory` VALUES ('pbw', '海贼王', '2021-06-23 23:38:45', 45);
INSERT INTO `contenthistory` VALUES ('pbw', '海贼王', '2021-06-23 23:39:04', 46);
INSERT INTO `contenthistory` VALUES ('pbw', '海贼王', '2021-06-23 23:39:07', 47);
INSERT INTO `contenthistory` VALUES ('admin', '海贼王', '2021-06-24 08:31:28', 48);
INSERT INTO `contenthistory` VALUES ('admin', '海绵宝宝', '2021-06-24 11:58:14', 49);
INSERT INTO `contenthistory` VALUES ('admin', '海贼王', '2021-06-24 11:58:19', 50);
INSERT INTO `contenthistory` VALUES ('admin', '海贼王', '2021-06-24 12:24:34', 51);
INSERT INTO `contenthistory` VALUES ('admin', '海贼王1', '2021-06-24 12:24:38', 52);

-- ----------------------------
-- Table structure for contentmessage
-- ----------------------------
DROP TABLE IF EXISTS `contentmessage`;
CREATE TABLE `contentmessage`  (
  `contentname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '动漫名称',
  `contenturl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '动漫播放地址',
  `contentimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '动漫封面图',
  `contentintroduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '动漫简介',
  `contenttype` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT '' COMMENT '动漫类型',
  `contentid` int(25) NOT NULL AUTO_INCREMENT COMMENT '动漫id',
  `contentshow` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT '' COMMENT '动漫是否展示',
  `contentvip` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT '' COMMENT '动漫是否需要vip',
  PRIMARY KEY USING BTREE (`contentid`)
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of contentmessage
-- ----------------------------
INSERT INTO `contentmessage` VALUES ('东京喰种1', 'http://www.imomoe.ai/view/2149.html', 'djcz.jpg', '金木研作为人与喰种的桥？', 1, 2, 0, 0);
INSERT INTO `contentmessage` VALUES ('东京喰种2', 'http://www.imomoe.ai/view/2149.html', 'djcz.jpg', '金木研作为人与喰种的桥？', 2, 3, 0, 1);
INSERT INTO `contentmessage` VALUES ('东京喰种3', 'http://www.imomoe.ai/view/2149.html', 'djcz.jpg', '金木研作为人与喰种的桥？', 3, 4, 0, 0);
INSERT INTO `contentmessage` VALUES ('东京喰种4', 'http://www.imomoe.ai/view/2149.html', 'djcz.jpg', '金木研作为人与喰种的桥？', 4, 5, 0, 0);
INSERT INTO `contentmessage` VALUES ('哆啦A梦', 'https://www.iqiyi.com/lib/m_200023214.html', 'dl.jpg', '大熊＆哆啦A梦的搞笑日常', 0, 6, 0, 1);
INSERT INTO `contentmessage` VALUES ('哆啦A梦1', 'https://www.iqiyi.com/lib/m_200023214.html', 'dl.jpg', '大熊＆哆啦A梦的搞笑日常', 1, 7, 0, 0);
INSERT INTO `contentmessage` VALUES ('哆啦A梦2', 'https://www.iqiyi.com/lib/m_200023214.html', 'dl.jpg', '大熊＆哆啦A梦的搞笑日常', 2, 8, 0, 0);
INSERT INTO `contentmessage` VALUES ('哆啦A梦4', 'https://www.iqiyi.com/lib/m_200023214.html', 'dl.jpg', '大熊＆哆啦A梦的搞笑日常', 4, 10, 0, 0);
INSERT INTO `contentmessage` VALUES ('妖精的尾巴', 'https://www.iqiyi.com/v_19rrek0xfo.html?vfm=2008_aldbd', 'yjdwb.jpg', '妖精的尾巴是世界上最好的工会', 0, 11, 0, 1);
INSERT INTO `contentmessage` VALUES ('妖精的尾巴1', 'https://www.iqiyi.com/v_19rrek0xfo.html?vfm=2008_aldbd', 'yjdwb.jpg', '妖精的尾巴是世界上最好的工会', 1, 12, 0, 1);
INSERT INTO `contentmessage` VALUES ('妖精的尾巴2', 'https://www.iqiyi.com/v_19rrek0xfo.html?vfm=2008_aldbd', 'yjdwb.jpg', '妖精的尾巴是世界上最好的工会', 2, 13, 0, 0);
INSERT INTO `contentmessage` VALUES ('妖精的尾巴3', 'https://www.iqiyi.com/v_19rrek0xfo.html?vfm=2008_aldbd', 'yjdwb.jpg', '妖精的尾巴是世界上最好的工会', 3, 14, 0, 1);
INSERT INTO `contentmessage` VALUES ('妖精的尾巴4', 'https://www.iqiyi.com/v_19rrek0xfo.html?vfm=2008_aldbd', 'yjdwb.jpg', '妖精的尾巴是世界上最好的工会', 4, 15, 0, 0);
INSERT INTO `contentmessage` VALUES ('死亡笔记', 'http://www.imomoe.ai/view/2665.html', 'swbj.jpg', '夜捡到了一本死神留下的笔记本，他会如何？', 0, 16, 0, 0);
INSERT INTO `contentmessage` VALUES ('死亡笔记1', 'http://www.imomoe.ai/view/2665.html', 'swbj.jpg', '夜捡到了一本死神留下的笔记本，他会如何？', 1, 17, 0, 0);
INSERT INTO `contentmessage` VALUES ('死亡笔记2', 'http://www.imomoe.ai/view/2665.html', 'swbj.jpg', '夜捡到了一本死神留下的笔记本，他会如何？', 2, 18, 0, 0);
INSERT INTO `contentmessage` VALUES ('死亡笔记3', 'http://www.imomoe.ai/view/2665.html', 'swbj.jpg', '夜捡到了一本死神留下的笔记本，他会如何？', 3, 19, 0, 0);
INSERT INTO `contentmessage` VALUES ('死亡笔记4', 'http://www.imomoe.ai/view/2665.html', 'swbj.jpg', '夜捡到了一本死神留下的笔记本，他会如何？', 4, 20, 0, 0);
INSERT INTO `contentmessage` VALUES ('死神', 'https://v.youku.com/v_show/id_XMTM3OTE1NzMzNg==.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'ss.jpg', '一护和他的伙伴，虚化的世界', 0, 21, 0, 0);
INSERT INTO `contentmessage` VALUES ('死神1', 'https://v.youku.com/v_show/id_XMTM3OTE1NzMzNg==.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'ss.jpg', '一护和他的伙伴，虚化的世界', 1, 22, 0, 0);
INSERT INTO `contentmessage` VALUES ('死神2', 'https://v.youku.com/v_show/id_XMTM3OTE1NzMzNg==.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'ss.jpg', '一护和他的伙伴，虚化的世界', 2, 23, 0, 0);
INSERT INTO `contentmessage` VALUES ('死神3', 'https://v.youku.com/v_show/id_XMTM3OTE1NzMzNg==.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'ss.jpg', '一护和他的伙伴，虚化的世界', 3, 24, 0, 0);
INSERT INTO `contentmessage` VALUES ('死神4', 'https://v.youku.com/v_show/id_XMTM3OTE1NzMzNg==.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'ss.jpg', '一护和他的伙伴，虚化的世界', 4, 25, 0, 1);
INSERT INTO `contentmessage` VALUES ('海绵宝宝', 'https://www.iqiyi.com/v_2aym84m3igw.html?vfm=2008_aldbd&fv=p_02_01', 'hmbb.jpg', '我准备好了！海绵宝宝又开始了新的一天', 0, 26, 0, 0);
INSERT INTO `contentmessage` VALUES ('海绵宝宝1', 'https://www.iqiyi.com/v_2aym84m3igw.html?vfm=2008_aldbd&fv=p_02_01', 'hmbb.jpg', '我准备好了！海绵宝宝又开始了新的一天', 1, 27, 0, 0);
INSERT INTO `contentmessage` VALUES ('海绵宝宝2', 'https://www.iqiyi.com/v_2aym84m3igw.html?vfm=2008_aldbd&fv=p_02_01', 'hmbb.jpg', '我准备好了！海绵宝宝又开始了新的一天', 2, 28, 0, 0);
INSERT INTO `contentmessage` VALUES ('海绵宝宝3', 'https://www.iqiyi.com/v_2aym84m3igw.html?vfm=2008_aldbd&fv=p_02_01', 'hmbb.jpg', '我准备好了！海绵宝宝又开始了新的一天', 3, 29, 0, 0);
INSERT INTO `contentmessage` VALUES ('海绵宝宝4', 'https://www.iqiyi.com/v_2aym84m3igw.html?vfm=2008_aldbd&fv=p_02_01', 'hmbb.jpg', '我准备好了！海绵宝宝又开始了新的一天', 4, 30, 0, 0);
INSERT INTO `contentmessage` VALUES ('海贼王', 'https://www.iqiyi.com/v_1zjocozlips.html?vfm=2008_aldbd&fv=p_02_01', 'hzw.jpg', '路飞的梦想是当上海贼王！', 0, 31, 0, 0);
INSERT INTO `contentmessage` VALUES ('海贼王1', 'https://www.iqiyi.com/v_1zjocozlips.html?vfm=2008_aldbd&fv=p_02_01', 'hzw.jpg', '路飞的梦想是当上海贼王！', 1, 32, 0, 0);
INSERT INTO `contentmessage` VALUES ('海贼王2', 'https://www.iqiyi.com/v_1zjocozlips.html?vfm=2008_aldbd&fv=p_02_01', 'hzw.jpg', '路飞的梦想是当上海贼王！', 2, 33, 0, 0);
INSERT INTO `contentmessage` VALUES ('海贼王3', 'https://www.iqiyi.com/v_1zjocozlips.html?vfm=2008_aldbd&fv=p_02_01', 'hzw.jpg', '路飞的梦想是当上海贼王！', 3, 34, 0, 0);
INSERT INTO `contentmessage` VALUES ('海贼王4', 'https://www.iqiyi.com/v_1zjocozlips.html?vfm=2008_aldbd&fv=p_02_01', 'hzw.jpg', '路飞的梦想是当上海贼王！', 4, 35, 0, 0);
INSERT INTO `contentmessage` VALUES ('火影忍者', 'https://v.youku.com/v_show/id_XNTQwMTgxMTE2.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'hyrz.jpg', '木叶村风起云涌', 0, 36, 0, 0);
INSERT INTO `contentmessage` VALUES ('火影忍者1', 'https://v.youku.com/v_show/id_XNTQwMTgxMTE2.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'hyrz.jpg', '木叶村风起云涌', 1, 37, 0, 0);
INSERT INTO `contentmessage` VALUES ('火影忍者2', 'https://v.youku.com/v_show/id_XNTQwMTgxMTE2.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'hyrz.jpg', '木叶村风起云涌', 2, 38, 0, 0);
INSERT INTO `contentmessage` VALUES ('火影忍者3', 'https://v.youku.com/v_show/id_XNTQwMTgxMTE2.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'hyrz.jpg', '木叶村风起云涌', 3, 39, 0, 0);
INSERT INTO `contentmessage` VALUES ('火影忍者4', 'https://v.youku.com/v_show/id_XNTQwMTgxMTE2.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'hyrz.jpg', '木叶村风起云涌', 4, 40, 0, 0);
INSERT INTO `contentmessage` VALUES ('犬夜叉', 'https://www.bilibili.com/bangumi/play/ep289986?bsource=baidu_aladdin', 'qyc.jpg', '半人半妖的他，和人类的她', 0, 41, 0, 0);
INSERT INTO `contentmessage` VALUES ('犬夜叉4', 'https://www.bilibili.com/bangumi/play/ep289986?bsource=baidu_aladdin', 'qyc.jpg', '半人半妖的他，和人类的她', 4, 45, 0, 0);
INSERT INTO `contentmessage` VALUES ('东京喰种', 'http://www.imomoe.ai/view/2149.html', 'djcz.jpg', '金木研作为人与喰种的桥？', 0, 47, 0, 0);
INSERT INTO `contentmessage` VALUES ('东京喰种1', 'http://www.imomoe.ai/view/2149.html', 'djcz.jpg', '金木研作为人与喰种的桥？', 1, 48, 0, 1);
INSERT INTO `contentmessage` VALUES ('东京喰种2', 'http://www.imomoe.ai/view/2149.html', 'djcz.jpg', '金木研作为人与喰种的桥？', 2, 49, 0, 0);
INSERT INTO `contentmessage` VALUES ('东京喰种3', 'http://www.imomoe.ai/view/2149.html', 'djcz.jpg', '金木研作为人与喰种的桥？', 3, 50, 0, 0);
INSERT INTO `contentmessage` VALUES ('东京喰种4', 'http://www.imomoe.ai/view/2149.html', 'djcz.jpg', '金木研作为人与喰种的桥？', 4, 51, 0, 1);
INSERT INTO `contentmessage` VALUES ('哆啦A梦', 'https://www.iqiyi.com/lib/m_200023214.html', 'dl.jpg', '大熊＆哆啦A梦的搞笑日常', 0, 52, 0, 0);
INSERT INTO `contentmessage` VALUES ('哆啦A梦1', 'https://www.iqiyi.com/lib/m_200023214.html', 'dl.jpg', '大熊＆哆啦A梦的搞笑日常', 1, 53, 0, 0);
INSERT INTO `contentmessage` VALUES ('哆啦A梦2', 'https://www.iqiyi.com/lib/m_200023214.html', 'dl.jpg', '大熊＆哆啦A梦的搞笑日常', 2, 54, 0, 0);
INSERT INTO `contentmessage` VALUES ('哆啦A梦3', 'https://www.iqiyi.com/lib/m_200023214.html', 'dl.jpg', '大熊＆哆啦A梦的搞笑日常', 3, 55, 0, 0);
INSERT INTO `contentmessage` VALUES ('哆啦A梦4', 'https://www.iqiyi.com/lib/m_200023214.html', 'dl.jpg', '大熊＆哆啦A梦的搞笑日常', 4, 56, 0, 0);
INSERT INTO `contentmessage` VALUES ('妖精的尾巴', 'https://www.iqiyi.com/v_19rrek0xfo.html?vfm=2008_aldbd', 'yjdwb.jpg', '妖精的尾巴是世界上最好的工会', 0, 57, 0, 0);
INSERT INTO `contentmessage` VALUES ('妖精的尾巴1', 'https://www.iqiyi.com/v_19rrek0xfo.html?vfm=2008_aldbd', 'yjdwb.jpg', '妖精的尾巴是世界上最好的工会', 1, 58, 0, 0);
INSERT INTO `contentmessage` VALUES ('妖精的尾巴2', 'https://www.iqiyi.com/v_19rrek0xfo.html?vfm=2008_aldbd', 'yjdwb.jpg', '妖精的尾巴是世界上最好的工会', 2, 59, 0, 0);
INSERT INTO `contentmessage` VALUES ('妖精的尾巴3', 'https://www.iqiyi.com/v_19rrek0xfo.html?vfm=2008_aldbd', 'yjdwb.jpg', '妖精的尾巴是世界上最好的工会', 3, 60, 0, 0);
INSERT INTO `contentmessage` VALUES ('妖精的尾巴4', 'https://www.iqiyi.com/v_19rrek0xfo.html?vfm=2008_aldbd', 'yjdwb.jpg', '妖精的尾巴是世界上最好的工会', 4, 61, 0, 0);
INSERT INTO `contentmessage` VALUES ('死亡笔记', 'http://www.imomoe.ai/view/2665.html', 'swbj.jpg', '夜捡到了一本死神留下的笔记本，他会如何？', 0, 62, 0, 0);
INSERT INTO `contentmessage` VALUES ('死亡笔记1', 'http://www.imomoe.ai/view/2665.html', 'swbj.jpg', '夜捡到了一本死神留下的笔记本，他会如何？', 1, 63, 0, 0);
INSERT INTO `contentmessage` VALUES ('死亡笔记2', 'http://www.imomoe.ai/view/2665.html', 'swbj.jpg', '夜捡到了一本死神留下的笔记本，他会如何？', 2, 64, 0, 0);
INSERT INTO `contentmessage` VALUES ('死亡笔记3', 'http://www.imomoe.ai/view/2665.html', 'swbj.jpg', '夜捡到了一本死神留下的笔记本，他会如何？', 3, 65, 0, 0);
INSERT INTO `contentmessage` VALUES ('死亡笔记4', 'http://www.imomoe.ai/view/2665.html', 'swbj.jpg', '夜捡到了一本死神留下的笔记本，他会如何？', 4, 66, 0, 0);
INSERT INTO `contentmessage` VALUES ('死神', 'https://v.youku.com/v_show/id_XMTM3OTE1NzMzNg==.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'ss.jpg', '一护和他的伙伴，虚化的世界', 0, 67, 0, 0);
INSERT INTO `contentmessage` VALUES ('死神1', 'https://v.youku.com/v_show/id_XMTM3OTE1NzMzNg==.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'ss.jpg', '一护和他的伙伴，虚化的世界', 1, 68, 0, 0);
INSERT INTO `contentmessage` VALUES ('死神2', 'https://v.youku.com/v_show/id_XMTM3OTE1NzMzNg==.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'ss.jpg', '一护和他的伙伴，虚化的世界', 2, 69, 0, 1);
INSERT INTO `contentmessage` VALUES ('死神3', 'https://v.youku.com/v_show/id_XMTM3OTE1NzMzNg==.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'ss.jpg', '一护和他的伙伴，虚化的世界', 3, 70, 0, 0);
INSERT INTO `contentmessage` VALUES ('死神4', 'https://v.youku.com/v_show/id_XMTM3OTE1NzMzNg==.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'ss.jpg', '一护和他的伙伴，虚化的世界', 4, 71, 0, 1);
INSERT INTO `contentmessage` VALUES ('海绵宝宝', 'https://www.iqiyi.com/v_2aym84m3igw.html?vfm=2008_aldbd&fv=p_02_01', 'hmbb.jpg', '我准备好了！海绵宝宝又开始了新的一天', 0, 72, 0, 0);
INSERT INTO `contentmessage` VALUES ('海绵宝宝1', 'https://www.iqiyi.com/v_2aym84m3igw.html?vfm=2008_aldbd&fv=p_02_01', 'hmbb.jpg', '我准备好了！海绵宝宝又开始了新的一天', 1, 73, 0, 0);
INSERT INTO `contentmessage` VALUES ('海绵宝宝2', 'https://www.iqiyi.com/v_2aym84m3igw.html?vfm=2008_aldbd&fv=p_02_01', 'hmbb.jpg', '我准备好了！海绵宝宝又开始了新的一天', 2, 74, 0, 1);
INSERT INTO `contentmessage` VALUES ('海绵宝宝3', 'https://www.iqiyi.com/v_2aym84m3igw.html?vfm=2008_aldbd&fv=p_02_01', 'hmbb.jpg', '我准备好了！海绵宝宝又开始了新的一天', 3, 75, 0, 0);
INSERT INTO `contentmessage` VALUES ('海绵宝宝4', 'https://www.iqiyi.com/v_2aym84m3igw.html?vfm=2008_aldbd&fv=p_02_01', 'hmbb.jpg', '我准备好了！海绵宝宝又开始了新的一天', 4, 76, 0, 1);
INSERT INTO `contentmessage` VALUES ('海贼王', 'https://www.iqiyi.com/v_1zjocozlips.html?vfm=2008_aldbd&fv=p_02_01', 'hzw.jpg', '路飞的梦想是当上海贼王！', 0, 77, 0, 1);
INSERT INTO `contentmessage` VALUES ('海贼王1', 'https://www.iqiyi.com/v_1zjocozlips.html?vfm=2008_aldbd&fv=p_02_01', 'hzw.jpg', '路飞的梦想是当上海贼王！', 1, 78, 0, 0);
INSERT INTO `contentmessage` VALUES ('海贼王2', 'https://www.iqiyi.com/v_1zjocozlips.html?vfm=2008_aldbd&fv=p_02_01', 'hzw.jpg', '路飞的梦想是当上海贼王！', 2, 79, 0, 0);
INSERT INTO `contentmessage` VALUES ('海贼王3', 'https://www.iqiyi.com/v_1zjocozlips.html?vfm=2008_aldbd&fv=p_02_01', 'hzw.jpg', '路飞的梦想是当上海贼王！', 3, 80, 0, 0);
INSERT INTO `contentmessage` VALUES ('海贼王4', 'https://www.iqiyi.com/v_1zjocozlips.html?vfm=2008_aldbd&fv=p_02_01', 'hzw.jpg', '路飞的梦想是当上海贼王！', 4, 81, 0, 1);
INSERT INTO `contentmessage` VALUES ('火影忍者2', 'https://v.youku.com/v_show/id_XNTQwMTgxMTE2.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'hyrz.jpg', '木叶村风起云涌', 2, 84, 0, 0);
INSERT INTO `contentmessage` VALUES ('火影忍者3', 'https://v.youku.com/v_show/id_XNTQwMTgxMTE2.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'hyrz.jpg', '木叶村风起云涌', 3, 85, 0, 1);
INSERT INTO `contentmessage` VALUES ('火影忍者4', 'https://v.youku.com/v_show/id_XNTQwMTgxMTE2.html?tpa=dW5pb25faWQ9MTAzNzUzXzEwMDAwMV8wMV8wMQ&refer=sousuotoufang_market.qrwang_00002944_000000_QJFFvi_19031900', 'hyrz.jpg', '木叶村风起云涌', 4, 86, 0, 0);
INSERT INTO `contentmessage` VALUES ('海贼王', 'https://www.runoob.com/sql/sql-update.html', 'ad67c340ef494520981890f3af4294ef.jpg', 'fahjkfhakfhaf', 0, 93, 0, 1);

-- ----------------------------
-- Table structure for inform
-- ----------------------------
DROP TABLE IF EXISTS `inform`;
CREATE TABLE `inform`  (
  `informtitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `informdetail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `informread` int(1) NOT NULL DEFAULT ''
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of inform
-- ----------------------------

-- ----------------------------
-- Table structure for postcomment
-- ----------------------------
DROP TABLE IF EXISTS `postcomment`;
CREATE TABLE `postcomment`  (
  `commentid` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论内容',
  `time` datetime NOT NULL DEFAULT '' COMMENT '评论时间',
  `postid` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT '' COMMENT '评论帖子id',
  `isDel` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY USING BTREE (`commentid`)
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of postcomment
-- ----------------------------
INSERT INTO `postcomment` VALUES (1, 'pbw', '哈哈哈，好看', '2021-05-03 20:41:04', 2, 0);
INSERT INTO `postcomment` VALUES (2, 'rx', '确实好看', '2021-05-01 20:41:44', 2, 0);
INSERT INTO `postcomment` VALUES (3, 'pbw', '会换行', '2021-05-06 15:28:52', 2, 0);
INSERT INTO `postcomment` VALUES (4, 'pbw', '一起点赞', '2021-05-06 15:29:24', 10, 0);
INSERT INTO `postcomment` VALUES (5, 'pbw', '案发生发', '2021-05-06 15:44:29', 10, 0);
INSERT INTO `postcomment` VALUES (6, 'rx', 'hfakf', '2021-06-21 15:52:22', 10, 0);
INSERT INTO `postcomment` VALUES (7, 'rx', 'fhakfja', '2021-06-21 16:24:00', 2, 0);
INSERT INTO `postcomment` VALUES (8, 'admin', '啊哒哒哒', '2021-06-23 20:21:12', 10, 0);

-- ----------------------------
-- Table structure for slideshow
-- ----------------------------
DROP TABLE IF EXISTS `slideshow`;
CREATE TABLE `slideshow`  (
  `slideshowimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '轮播图图片',
  `slideshowtype` int(2) NOT NULL DEFAULT '' COMMENT '轮播图对应的class'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of slideshow
-- ----------------------------
INSERT INTO `slideshow` VALUES ('1-1.jpg', 0);
INSERT INTO `slideshow` VALUES ('1-2.jpg', 0);
INSERT INTO `slideshow` VALUES ('2-1.jpg', 1);
INSERT INTO `slideshow` VALUES ('2-2.jpg', 1);
INSERT INTO `slideshow` VALUES ('3-1.jpg', 2);
INSERT INTO `slideshow` VALUES ('3-2.jpg', 2);
INSERT INTO `slideshow` VALUES ('4-1.jpg', 3);
INSERT INTO `slideshow` VALUES ('4-2.jpg', 3);
INSERT INTO `slideshow` VALUES ('5-1.jpg', 4);
INSERT INTO `slideshow` VALUES ('5-2.jpg', 4);

-- ----------------------------
-- Table structure for usermessage
-- ----------------------------
DROP TABLE IF EXISTS `usermessage`;
CREATE TABLE `usermessage`  (
  `userid` int(255) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `useremail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `userpwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `userimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户头像',
  `userRegTime` datetime NOT NULL DEFAULT '' COMMENT '用户注册时间',
  `uservip` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT '' COMMENT '用户是否是vip',
  `usertype` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT '' COMMENT '用户是否是管理员',
  PRIMARY KEY USING BTREE (`userid`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of usermessage
-- ----------------------------
INSERT INTO `usermessage` VALUES (1, '1911847149@qq.com', 'pbw', '123', 'e4c927e6ecf6416089637fac207f444e.jpg', '2021-05-30 00:41:06', 1, 0);
INSERT INTO `usermessage` VALUES (2, '1911847149@qq.com', 'rx', '123', '5fe09acc72a24eafba42e85bef5b2675.jpg', '2021-05-31 12:03:00', 0, 0);
INSERT INTO `usermessage` VALUES (3, '', 'admin', '123', '01.jpg', '2021-06-22 20:44:26', 1, 1);

-- ----------------------------
-- Table structure for userpost
-- ----------------------------
DROP TABLE IF EXISTS `userpost`;
CREATE TABLE `userpost`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `postimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '帖子图片',
  `postcontent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '帖子内容',
  `postgood` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT '' COMMENT '是否过审',
  `postid` int(255) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY USING BTREE (`postid`)
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of userpost
-- ----------------------------
INSERT INTO `userpost` VALUES ('rx', '5-2.jpg', '骨王yyds，装逼class，牛批也是真的牛批！爱了爱了', 1, 2);
INSERT INTO `userpost` VALUES ('pbw', '5-2.jpg', '骨王yyds，喜欢的朋友记得留下点赞呀！一起冲！！！！', 1, 10);
INSERT INTO `userpost` VALUES ('rx', 'ad690830e29b4cffa939bfc73a8a8a71.jpg', 'ewrwafsfaf', 0, 11);
INSERT INTO `userpost` VALUES ('admin', '386b76ac362a48e3bef8b8a0f2b2928b.jpg', 'gajkfh1', 1, 12);
INSERT INTO `userpost` VALUES ('admin', '3db4804c21754ccdadef56ea0225db16.jpg', '阿嘎', 1, 13);
INSERT INTO `userpost` VALUES ('pbw', '4e21c3c89bcc483780977c79e4b9d800.jpg', '海贼王冲', 1, 14);

SET FOREIGN_KEY_CHECKS = 1;
