package com.niuben.mycar.Bean;

import java.util.List;

/**
 * Created by niuben on 2016/5/11.
 */
public class CarMaintainNewsBean {
    /**
     * reason : 查询成功
     * result : [{"title":"把好这5关,让爱车摆脱\"节后综合症\"!","content":"假期出行,驾车地形不熟,路面情况复杂都有可能对<em>爱车<\/em>轮胎造成损耗。胎纹中卡住的碎石、钉子、铁屑和玻璃碎片等异物如不及时清理,容易刺伤轮胎。另外,长途高速行驶会使轮胎磨损加剧。 回来后,应首先检查每个车轮的胎压是否处在正常范围内,轮胎的胎面是否有裂纹或者扎伤,侧壁...","img_width":"354","full_title":"把好这5关,让爱车摆脱\"节后综合症\"!","pdate":"7小时前","src":"易车网","img_length":"630","img":"http://p4.qhimg.com/t011c4ee8f9cf85b10f.jpg","url":"http://news.bitauto.com/dealer/20160510/2206597018.html","pdate_src":"2016-05-10 22:02:00"},{"title":"别遗忘了爱车的第五只脚 备胎使用有讲究","content":"备胎作为汽车的第五只\"脚\",平时一直都是静静地躺在后备箱里。正因为这样,常常受到车主的\"冷落\"。车主经常会在其他的4个正胎中的一个出了意外,才想起要用备胎,这时候,备胎很可能要么气不足,要么已经\"年迈\",完全派不上用场。 备胎对汽车使用来说非常重要,特别是高速远行,为避...","img_width":"301","full_title":"别遗忘了爱车的第五只脚 备胎使用有讲究","pdate":"10小时前","src":"搜狐","img_length":"492","img":"http://p2.qhimg.com/t012afd43160e093cf9.jpg","url":"http://auto.sohu.com/20160510/n448690716.shtml","pdate_src":"2016-05-10 18:57:45"},{"title":"长安责任保险推\"汽修保\" 防范爱车被\"二次伤害\"","content":"中国网财经5月10日讯 近日,长安责任保险推出了一款专门针对车辆维修企业的责任保险产品--\"汽修保\"。  目前在我国,车险可为车辆提供较为全面的保险保障。而根据车险条款规定,被保险车辆在营业性场所修理、保养和改装期间受到的损失均被列为除外责任。  那么,车辆在这期间...","img_width":"","full_title":"长安责任保险推\"汽修保\" 防范爱车被\"二次伤害\"","pdate":"11小时前","src":"中国网","img_length":"","img":"","url":"http://news.china.com.cn/rollnews/news/live/2016-05/10/content_36024960.htm","pdate_src":"2016-05-10 18:31:30"},{"title":"男子给女友爱车喷粉色LOVE求婚换来一巴掌","content":"白色轿车喷漆求婚 粉色的LOVE字体  5月6日下午,哈尔滨一年轻男子将一辆停在街头的白色纳智捷轿车喷上了粉色的LOVE字体,辅警发现立即上去制止,可男子却说这是自己向女友求婚的另类方式。  5月6日15时许,哈市香坊巡逻辅警大队一中队的辅警王奕龙、孟庆来在执勤时发现,果...","img_width":"335","full_title":"男子给女友爱车喷粉色LOVE求婚换来一巴掌","pdate":"3天前","src":"中国青年网","img_length":"500","img":"http://p1.qhimg.com/t01810ca6074577e313.jpg","url":"http://news.youth.cn/sh/201605/t20160508_7966419.htm","pdate_src":"2016-05-08 03:14:58"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    /**
     * title : 把好这5关,让爱车摆脱"节后综合症"!
     * content : 假期出行,驾车地形不熟,路面情况复杂都有可能对<em>爱车</em>轮胎造成损耗。胎纹中卡住的碎石、钉子、铁屑和玻璃碎片等异物如不及时清理,容易刺伤轮胎。另外,长途高速行驶会使轮胎磨损加剧。 回来后,应首先检查每个车轮的胎压是否处在正常范围内,轮胎的胎面是否有裂纹或者扎伤,侧壁...
     * img_width : 354
     * full_title : 把好这5关,让爱车摆脱"节后综合症"!
     * pdate : 7小时前
     * src : 易车网
     * img_length : 630
     * img : http://p4.qhimg.com/t011c4ee8f9cf85b10f.jpg
     * url : http://news.bitauto.com/dealer/20160510/2206597018.html
     * pdate_src : 2016-05-10 22:02:00
     */

    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String title;
        private String content;
        private String img_width;
        private String full_title;
        private String pdate;
        private String src;
        private String img_length;
        private String img;
        private String url;
        private String pdate_src;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg_width() {
            return img_width;
        }

        public void setImg_width(String img_width) {
            this.img_width = img_width;
        }

        public String getFull_title() {
            return full_title;
        }

        public void setFull_title(String full_title) {
            this.full_title = full_title;
        }

        public String getPdate() {
            return pdate;
        }

        public void setPdate(String pdate) {
            this.pdate = pdate;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getImg_length() {
            return img_length;
        }

        public void setImg_length(String img_length) {
            this.img_length = img_length;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPdate_src() {
            return pdate_src;
        }

        public void setPdate_src(String pdate_src) {
            this.pdate_src = pdate_src;
        }
    }
}
