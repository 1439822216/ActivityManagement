package com.example.administrator.activitymanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table userInfo (uid varchar(20) primary key ,name varchar(20),telephone varchar(20),clazz varchar(20),touxiang varchar(20),username varchar(20) unique,password varchar(20))";
        String sql1 = "create table activity(aid varchar(20) primary key ,aName varchar(50)  not null,aimageId varchar(20) not null,aUid varchar(20) not null,aUsername varchar(20) not null,aOpenTime varchar(20) not null, aEndTime varchar(20) not null,aPlace varchar(30) not null,aInfo varchar(200) not null,aTelephone varchar(20) not null)";
        String sql2 = "create table joinTo(uid varchar(20) not null,aid varchar(20) not null)";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL("insert into userInfo values('E3BF64A5C21243678CDBC4C21649C954','戴俊迈','123456','移动172','t1','111','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('ADC80B549F2D4D1DB1E5315C1144F0BA','戴逸仙','123456','化学172','t2','222','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('8BB3195305814946903BBFA742D1BC49','文安然','123456','数学172','t3','333','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('0559A1CC0D8F4CDAB3F46AF80A8ACD14','董思远','123456','机器人17','t4','444','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('31F0275BBE62445681A451701826B607','范德华','123456','网站171','t5','555','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('F192C168E2594B33BF49DCB74AF10AE4','金羽彤','123456','移动172','t1','666','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('AA3326A5654747E3878E3C34429EFEAF','毛梓珊','123456','化学172','t2','777','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('F46704EE82984AF1A6DF8F25A9E5FF5C','冯山柏','123456','数学172','t3','888','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('7A661C34C4FD4E29A018B46AF2477DC7','蒋迎松','123456','机器人17','t4','999','123456')");
        sqLiteDatabase.execSQL("insert into userInfo values('42D284C4837E4830B53C5CF3E22B1A9C','乔曼妮','123456','网站171','t5','110','123456')");
        insertActivity(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertActivity(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("insert into activity values('9591AD2C90404CA9B61B8D73C8AB6F3F','广州城建职业学院“蚁人团队”寒假择校资讯推广公益活动','a1','E3BF64A5C21243678CDBC4C21649C954','戴俊迈',\n" +
                "'2019-01-20','2019-02-22','广东省广州市从化区环市东路166号','一、活动主题\n" +
                "广州城建职业学院“蚁人团队”寒假择校资讯推广公益活动\n" +
                "二、承办单位\n" +
                "广州城建职业学院招生工作室“蚁人团队”\n" +
                "三、活动目的\n" +
                "由于高三的同学正在努力备考高考，关于学校方面的资讯了解不多。对于自主招生的报考以及择校所知寥寥无几，我校开展择校资讯公益活动，旨在通过城建学院“蚁人团队”成员与招生志愿者回访母校的方式，有效帮助备考的高三同学们了解择校相关资讯，同时借此机会向高三同学们推广学校，扩大城建学院在高三学生群体中的知名度。通过学院在校学生的回访，让高中、中职的同学增加对于城建自招、城建学考、城建的环境各大方面的了解。\n" +
                "四、活动对象\n" +
                "广州城建职业学院在校生\n" +
                "五、活动进行\n" +
                "活动人员自己与老师沟通或自己与相关同学沟通自行选择时间去到自己的高中学校，自己去宣讲有关的大学录取途径并发城建学校的宣传册（必须提前做好相关的宣讲内容工作），可以在讲述宣讲的同时加上自己在城建的大学生活。',\n" +
                "'123456')");
        sqLiteDatabase.execSQL("insert into activity values('8837B253D2B14E4CB1C2932715722378','霞洞中学2019年元旦文艺晚会','a2','ADC80B549F2D4D1DB1E5315C1144F0BA','戴逸仙','2018-12-27','2018-12-27','电白区霞洞中学','时间如白驹过隙，转眼间2019年元旦将至，为庆祝元旦佳节，也进一步推进我校文艺活动的蓬勃发展，丰富校园文化生活，活跃学习氛围，给同学们一个真正展示自我风采和勇气的舞台。2019年元旦晚会将以宏大的气魄，不凡的手笔，新颖的题材，经典的节目，集多种舞台表演形式，真实反映我校校园文化建设，以出色的工作和优异的成绩，推进我校校风学风及校园文化建设，迎接新年的到来。\n" +
                "一、活动主题：好心茂名，平安电白。\n" +
                "二、活动对象：全校学生，以班级为单位。\n" +
                "三、活动地点：学校舞台\n" +
                "四、活动时间：2018年12月27日18:00 -22:00\n" +
                "五、节目类型：1.现代舞；2.民族舞；3.小品；4.相声；5.综艺（独唱伴舞、双人唱伴舞、小组唱伴舞、乐器表演、才艺展示、时装秀、武术、健美操等，节目内容需健康向上）','123456')");
        sqLiteDatabase.execSQL("insert into activity values('2FB3BA010AED4FD2880DF20813E7E204','2019桂城青年创客墟接受报名啦！','a3','8BB3195305814946903BBFA742D1BC49','文安然','2019-01-31','2019-02-04','佛山市南海区迎春花市','1．以团队形式申报\n" +
                "2．申报团队要求：\n" +
                "（1）申报团队由4-7人组成，成员须为16周岁以上28周岁以下。（建议至少有一名懂得财务相关知识的成员）\n" +
                "（2）每人只可加入一支队伍，违者所在队伍整队取消参赛资格。\n" +
                "（3）每支队伍须确定领队人选、成员名单、申报方案等信息，方案提交后不得随意更改。\n" +
                "','123456')");
        sqLiteDatabase.execSQL("insert into activity values('8DDE027543EF4A6FAE98A6D9C94B953B','随手公益','a4','0559A1CC0D8F4CDAB3F46AF80A8ACD14','董思远','2018-12-27','2018-12-31','珠江大道','活动目的：致敬环卫工人，保护环境，爱护他人劳动成果，从我们做起。\n" +
                "活动主题：随手公益。\n" +
                "活动时间：2018-12-31  07:00-08:00。\n" +
                "活动要求 ：已收到短信通知为准。','123456')");
        sqLiteDatabase.execSQL("insert into activity values('36787A16F1624D29B2CB9F62C19B95FE','DIY折纸','a5','31F0275BBE62445681A451701826B607','范德华','2018-12-29','2018-12-31','广东省珠海市香洲区金鸡路608-127号','一.活动名称：折纸\n" +
                "二.活动时间：2018年12月29日下午4：30-5:30\n" +
                "三.活动地点 ：南沙湾社区公共服务站二楼活动室\n" +
                "四.活动对象：6-12岁青少年\n" +
                "五.指导单位：共青团珠海市委员会\n" +
                "六.举办单位：南沙湾社区居委会、南沙湾亲青家园\n" +
                "七.活动内容：为提高青少年的动手能力，在共青团珠海市委员会和南沙湾社区居委会的指导和支持下，南沙湾亲青家园将于12月29日下午4:30开展“折纸活动”。\n" +
                "八.报名方式：1.青年之声2.编辑短信“姓名+性别+年龄+联系方式”至36363636 陈先生','123456')");
        sqLiteDatabase.execSQL("insert into activity values('EB0D41F327A84771824B2042433462C0','二临新春晚会','a6','F192C168E2594B33BF49DCB74AF10AE4','金羽彤','2019-01-06','2019-01-09','南方医科大学顺德校区综合馆','一、活动主题：\n" +
                "   蔓茂医心，梦圆珠江\n" +
                "二、活动背景：\n" +
                "2018即将远去，2019正向我们招手。站在岁末，回望过去的一年里，第二临床医学院的成员们携手并进，开创了许多属于我们二临人的佳话。我们期盼未来，希望在2019年所有正青春的二临人能更加团结，一起为建设更加温馨美好的二临大家庭而努力。\n" +
                "三、活动内容：\n" +
                "主办单位：南方医科大学第二临床医学院\n" +
                "活动时间：2019年1月1日19:00-21:30\n" +
                "活动地点：南方医科大学顺德校区综合馆\n" +
                "活动对象：南方医科大学顺德校区全体师生、第二临床医学院卓越创新班\n" +
                "活动内容\n" +
                "1、 在传单上有位置可以集印章，玩摊位游戏（参与即可，不计达标）就可以获得印章。集满两个印章即可有转转盘的机会。转盘的奖品设置为：暖水袋（20元×5个）、晾衣杆（29个）、粘钩（23组）、牙膏（21个）、小奖品。\n" +
                "2、 凭传单到晚会现场可先到先得领取鹿角灯（105个）\n" +
                "3、 幸运数字：在倒五推送上说明凭传单可到晚会现场领取小礼品和参与幸运数字活动。在倒一推送上公布4个获得奖品的数字（1、19、52、72），剩下的数字不对外公布。记录有传单的同学的到场顺序，第1、8、19、28、52、68、72、88、99可得奖品。奖品随机抽取。奖品为蓝牙音响（1）、零食礼包（3）、柠檬茶一组（5）。','123456')");
        sqLiteDatabase.execSQL("insert into activity values('64D4F80180B84955AA20790DA8B9ACEE','97中新年剧场','a7','AA3326A5654747E3878E3C34429EFEAF','毛梓珊','2019-01-06','2019-01-08','广东省广州市海珠区金恒路75','2018学年广州市第九十七中学“我的青春我做主”\n" +
                "高中部“新年剧场”语言艺术类节目汇演比赛方案\n" +
                "一、比赛形式及内容\n" +
                "　　围绕本次比赛主题，可以选择多种语言艺术表现形式，具体形式和要求如下：\n" +
                "　（一）朗诵、讲故事、演讲、相声、快板等（3分钟以内，不超过4人，不加伴舞）；\n" +
                "　（二）小品、校园剧、课本剧、童话剧等（5分钟以内，不超过8人，不用大型道具）。\n" +
                "二、评奖办法\n" +
                "　　本部决赛12个节目：分别设一等奖3名、二等奖5名、三等奖4名；设立个人奖和集体奖，集体奖纳入科艺节班级总分。\n" +
                "　　金碧决赛4个节目：分别设一等奖1名、二等奖1名、三等奖2名，成绩纳入科艺节班级总分。','123456')\n");
        sqLiteDatabase.execSQL("insert into activity values('1D076E656B8047A58947BC96C45F83CF','白鹤洞街长者健康管理日','a1','E3BF64A5C21243678CDBC4C21649C954','戴俊迈','2019-1-11','2019-1-11','广东省广州市荔湾区鹤洞大街37号白鹤洞街家庭服务中心','活动地点：广东省广州市荔湾区鹤洞大街37号白鹤洞街家庭服务中心一楼\n" +
                "活动时间：2019-01-11 14:00-16:00\n" +
                "活动对象：白鹤洞街60岁以上长者\n" +
                "活动内容：以健康量血压为桥梁为社区长者提供一个交流平台，在提升长者健康管理意识的同时巩固长者的社交圈。','123456')");
        sqLiteDatabase.execSQL("insert into activity values('E10FDC6849954EFCAED1F3A43AE8AD09','金海岸社区 浓情腊八，喜迎新春游园会','a2','E3BF64A5C21243678CDBC4C21649C954','戴俊迈','2019-1-13','2019-1-13','广东省珠海市金湾区金海岸大道东526','天时人事日相催，冬至阳生春又来。走过2018年，\n" +
                "我们怀着激动的心情，带着崭新的精神面貌踏进了2019年的殿堂。欣的一年春节即将到来，为了丰富社区居民的文化生活需求，搭建居民之间相互交流沟通平台，增加“邻里情”，增强社区居民的凝聚力、向心力，营造出一种团结奋进、\n" +
                "文明和谐、喜庆祥和的春节浓郁氛围，按照金湾区团区委的安排，结合社区实际， 在金海岸社区内举办“浓情腊八，喜迎新春”游园会，届时有写对联、喝腊八粥、磁铁飞镖、灯笼谜语和剪纸、义剪、\n" +
                "创意手工等多样的游园活动来满足社区居民的需求，欢迎广大居民热心参与。','123456')");
        sqLiteDatabase.execSQL("insert into activity values('48BBAA4E940A4878BA49A5C3E7E1B58B','共健互助齐庆春主题游园会','a3','E3BF64A5C21243678CDBC4C21649C954','戴俊迈','2019-1-24','2019-1-24','白鹤洞街鹤洞社区鹤洞小苑（广东省广州市荔湾区南来大街134号）','活动地点：白鹤洞街鹤洞社区鹤洞小苑（广东省广州市荔湾区南来大街134号）\n" +
                "活动时间：2019-01-24 09:30—10:30\n" +
                "活动对象：社区60岁以上失独残障长者','123456')");
        sqLiteDatabase.execSQL("insert into activity values('C73ADDCAA57E490E83324BF44D95F44E','红色电影导赏小组','a4','E3BF64A5C21243678CDBC4C21649C954','戴俊迈','2019-1-16','2019-1-16','广东省广州市荔湾区鹤洞大街37号白鹤洞街家庭服务中心','活动地点：广东省广州市荔湾区鹤洞大街37号白鹤洞街家庭服务中心一楼\n" +
                "活动时间：2019-01-16 15:00—16:00\n" +
                "活动对象：白鹤洞街60岁以上长者','123456')");
        sqLiteDatabase.execSQL("insert into activity values('049D0C02ED9D4A60988A89675F0DE5A5','社区培育','a5','E3BF64A5C21243678CDBC4C21649C954','戴俊迈','2019-1-15','2019-1-15','广东省广州市荔湾区鹤洞大街37号白鹤洞街家庭服务中心','活动地点：广东省广州市荔湾区鹤洞大街37号白鹤洞街家庭服务中心一楼\n" +
                "活动时间：2019-01-15 10:00—11:00\n" +
                "活动对象：有兴趣的义工\n" +
                "活动内容：培育义工骨干，协助社工共同开展社区活动。','123456')");
    }
}
