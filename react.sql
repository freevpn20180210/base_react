/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : react

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-03-20 17:56:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `passtime` datetime DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=222 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('172', 'http://cms-bucket.ws.126.net/2020/0320/7e50225ap00q7gxw8003mc0009c005uc.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85DRS0J0001899O.html', '多地发生森林火灾 山西发布森林草原防火紧急通知');
INSERT INTO `news` VALUES ('173', 'http://cms-bucket.ws.126.net/2020/0320/a7ee4862p00q7gxyt00xic000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85DUKL90001899O.html', '厄瓜多尔确诊病例升至260例 政府已采取宵禁等措施');
INSERT INTO `news` VALUES ('174', 'http://cms-bucket.ws.126.net/2020/0320/6ccce850p00q7gxjd00lxc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85DDERA0001899O.html', '纽约州长：新冠肺炎疫情堪比9·11 瞬间改变一切');
INSERT INTO `news` VALUES ('175', 'http://cms-bucket.ws.126.net/2020/0320/7baca467p00q7gxf200hvc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85DAEEL00019B3E.html', '美国这州州长警告特朗普:病例数将超出救治能力范围');
INSERT INTO `news` VALUES ('176', 'http://cms-bucket.ws.126.net/2020/0320/c9e3b693p00q7gxcw00eyc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85D6E7U0001899O.html', '墨西哥新增新冠肺炎确诊病例46例 累计确诊164例');
INSERT INTO `news` VALUES ('177', 'http://cms-bucket.ws.126.net/2020/0320/89b84039p00q7gy7800dcc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85DIS1G00018AOR.html', '美财政部宣布制裁与伊朗相关5家石油公司');
INSERT INTO `news` VALUES ('178', 'http://cms-bucket.ws.126.net/2020/0320/23b1a34dp00q7gxaa003ic0009c005uc.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85D3S0T0001899O.html', '广西男子因债务纠纷持刀捅人 已被警方抓获归案');
INSERT INTO `news` VALUES ('179', 'http://cms-bucket.ws.126.net/2020/0320/0a8e2cadp00q7gx6u00dzc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85CVKI00001899O.html', '意大利首个方舱医院将于今天启用 能提供60张病床');
INSERT INTO `news` VALUES ('180', 'http://cms-bucket.ws.126.net/2020/0320/d450fc5cp00q7gtel003lc0009c005uc.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://tech.163.com/20/0320/08/F858ALG800097U7R.html', '美股周四小幅反弹 道指收复20000点 特斯拉涨18%');
INSERT INTO `news` VALUES ('181', 'http://cms-bucket.ws.126.net/2020/0320/18227a5ep00q7gwwg00vvc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85CKTUR0001899O.html', '秘鲁新增近百例新冠肺炎确诊病例 累计确诊234例');
INSERT INTO `news` VALUES ('197', 'http://cms-bucket.ws.126.net/2020/0320/32c35344p00q7gwtf00doc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85CI8UU0001899O.html', '奥运圣火运抵日本:小学生欢迎活动取消改这样迎接');
INSERT INTO `news` VALUES ('198', 'http://cms-bucket.ws.126.net/2020/0320/bd41930cp00q7gwur00awc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85CGE7D0001899O.html', '广东新增境外输入确诊病例14例 累计出院1322例');
INSERT INTO `news` VALUES ('199', 'http://cms-bucket.ws.126.net/2020/0320/9a05aa6dp00q7gwrs00auc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85CF0UK0001899O.html', '韩国新增新冠肺炎确诊病例87例 累计升至8652例');
INSERT INTO `news` VALUES ('200', 'http://cms-bucket.ws.126.net/2020/0320/8ce82a84p00q7gwqs011rc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85CE7Q80001899O.html', '智利累计确诊病例342例 复活节岛暂时“封城”');
INSERT INTO `news` VALUES ('201', 'http://cms-bucket.ws.126.net/2020/0320/412b08f1p00q7gwma00hsc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85C98500001899O.html', '湖北一副县长被免职1月后 以＂副县级领导＂身份工作');
INSERT INTO `news` VALUES ('202', 'http://cms-bucket.ws.126.net/2020/0320/033ffa5bp00q7gwaz00f2c000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85BT2MF00019B3E.html', '安倍：不会缩小规模办奥运 希望为观众带去感动');
INSERT INTO `news` VALUES ('203', 'http://cms-bucket.ws.126.net/2020/0320/43c352d9p00q7gw3x00f0c000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/09/F85BMTCK00019B3E.html', '累计189例！近5天境外输入病例持续两位数增加');
INSERT INTO `news` VALUES ('204', 'http://cms-bucket.ws.126.net/2020/0320/a48f7a33p00q7gw6400fvc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/08/F85BJECU0001899O.html', '浙江新增新冠肺炎确诊病例1例 为境外输入病例');
INSERT INTO `news` VALUES ('205', 'http://cms-bucket.ws.126.net/2020/0320/835b830ap00q7gvtw00dzc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/08/F85BECUV00019B3E.html', '纽约市长:医疗用品将在3周内耗尽 必须有大量补给');
INSERT INTO `news` VALUES ('206', 'http://cms-bucket.ws.126.net/2020/0320/8011447fp00q7gvrp00ogc000s600e3c.png?imageView&thumbnail=140y88&quality=85', '2020-03-20 10:00:33', 'https://news.163.com/20/0320/08/F85B7RJ900019B3E.html', '韩美签订600亿美元货币互换协议 以缓解韩＂美元荒＂');
INSERT INTO `news` VALUES ('221', 'https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1584698092&di=e81b58f8f2c5845568000028255febce&src=http://m.360buyimg.com/pop/jfs/t24610/178/236171520/66808/e756b86/5b6974d3N936ef170.jpg', '2020-03-20 17:55:25', 'https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1584698092&di=e81b58f8f2c5845568000028255febce&src=http://m.360buyimg.com/pop/jfs/t24610/178/236171520/66808/e756b86/5b6974d3N936ef170.jpg', '亚索');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
