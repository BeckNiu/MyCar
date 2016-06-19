package com.niuben.mycar.Bean;

import java.util.List;

/**
 * Created by niuben on 2016/5/10.
 */
public class WeatherReportBean {

    /**
     * reason : successed!
     * result : {"data":{"realtime":{"wind":{"windspeed":"7.0","direct":"北风","power":"1级","offset":null},"time":"03:00:00","weather":{"humidity":"94","img":"18","info":"雾","temperature":"13"},"dataUptime":1462821962,"date":"2016-05-10","city_code":"101180101","city_name":"郑州","week":2,"moon":"四月初四"},"life":{"date":"2016-5-10","info":{"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"yundong":["较适宜","天气较好，户外运动请注意防晒。推荐您进行室内运动。"],"ziwaixian":["强","紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"],"ganmao":["少发","各项气象条件适宜，无明显降温过程，发生感冒机率较低。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"wuran":["较差","气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"],"chuanyi":["舒适","建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"]}},"weather":[{"date":"2016-05-10","info":{"dawn":["1","多云","12","无持续风向","微风","19:16"],"night":["0","晴","17","无持续风向","微风","19:17"],"day":["0","晴","26","无持续风向","微风","05:25"]},"week":"二","nongli":"四月初四"},{"date":"2016-05-11","info":{"dawn":["0","晴","17","无持续风向","微风","19:17"],"night":["1","多云","21","无持续风向","微风","19:18"],"day":["0","晴","32","无持续风向","微风","05:25"]},"week":"三","nongli":"四月初五"},{"date":"2016-05-12","info":{"dawn":["1","多云","21","无持续风向","微风","19:18"],"night":["1","多云","16","北风","3-4 级","19:19"],"day":["2","阴","30","无持续风向","微风","05:24"]},"week":"四","nongli":"四月初六"},{"date":"2016-05-13","info":{"dawn":["1","多云","16","北风","3-4 级","19:19"],"night":["2","阴","16","无持续风向","微风","19:20"],"day":["2","阴","23","无持续风向","微风","05:23"]},"week":"五","nongli":"四月初七"},{"date":"2016-05-14","info":{"dawn":["0","晴","17","无持续风向","微风","19:17"],"night":["1","多云","21","无持续风向","微风","19:18"],"day":["0","晴","32","无持续风向","微风","05:25"]},"week":"六","nongli":"四月初八"},{"date":"2016-05-15","info":{"night":["1","多云","13","东北风","微风","19:30"],"day":["1","多云","22","东北风","微风","07:30"]},"week":"日","nongli":"四月初九"},{"date":"2016-05-16","info":{"night":["0","晴","15","","微风","19:30"],"day":["0","晴","26","","微风","07:30"]},"week":"一","nongli":"四月初十"}],"pm25":{"key":"","show_desc":0,"pm25":{"curPm":"63","pm25":"34","pm10":"63","level":2,"quality":"良","des":"今天的空气质量是可以接受的，除少数异常敏感体质的人群外，大家可在户外正常活动。"},"dateTime":"2016年05月10日03时","cityName":"郑州"},"date":null,"isForeign":0}}
     * error_code : 0
     */

    private String reason;
    /**
     * data : {"realtime":{"wind":{"windspeed":"7.0","direct":"北风","power":"1级","offset":null},"time":"03:00:00","weather":{"humidity":"94","img":"18","info":"雾","temperature":"13"},"dataUptime":1462821962,"date":"2016-05-10","city_code":"101180101","city_name":"郑州","week":2,"moon":"四月初四"},"life":{"date":"2016-5-10","info":{"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"yundong":["较适宜","天气较好，户外运动请注意防晒。推荐您进行室内运动。"],"ziwaixian":["强","紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"],"ganmao":["少发","各项气象条件适宜，无明显降温过程，发生感冒机率较低。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"wuran":["较差","气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"],"chuanyi":["舒适","建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"]}},"weather":[{"date":"2016-05-10","info":{"dawn":["1","多云","12","无持续风向","微风","19:16"],"night":["0","晴","17","无持续风向","微风","19:17"],"day":["0","晴","26","无持续风向","微风","05:25"]},"week":"二","nongli":"四月初四"},{"date":"2016-05-11","info":{"dawn":["0","晴","17","无持续风向","微风","19:17"],"night":["1","多云","21","无持续风向","微风","19:18"],"day":["0","晴","32","无持续风向","微风","05:25"]},"week":"三","nongli":"四月初五"},{"date":"2016-05-12","info":{"dawn":["1","多云","21","无持续风向","微风","19:18"],"night":["1","多云","16","北风","3-4 级","19:19"],"day":["2","阴","30","无持续风向","微风","05:24"]},"week":"四","nongli":"四月初六"},{"date":"2016-05-13","info":{"dawn":["1","多云","16","北风","3-4 级","19:19"],"night":["2","阴","16","无持续风向","微风","19:20"],"day":["2","阴","23","无持续风向","微风","05:23"]},"week":"五","nongli":"四月初七"},{"date":"2016-05-14","info":{"dawn":["0","晴","17","无持续风向","微风","19:17"],"night":["1","多云","21","无持续风向","微风","19:18"],"day":["0","晴","32","无持续风向","微风","05:25"]},"week":"六","nongli":"四月初八"},{"date":"2016-05-15","info":{"night":["1","多云","13","东北风","微风","19:30"],"day":["1","多云","22","东北风","微风","07:30"]},"week":"日","nongli":"四月初九"},{"date":"2016-05-16","info":{"night":["0","晴","15","","微风","19:30"],"day":["0","晴","26","","微风","07:30"]},"week":"一","nongli":"四月初十"}],"pm25":{"key":"","show_desc":0,"pm25":{"curPm":"63","pm25":"34","pm10":"63","level":2,"quality":"良","des":"今天的空气质量是可以接受的，除少数异常敏感体质的人群外，大家可在户外正常活动。"},"dateTime":"2016年05月10日03时","cityName":"郑州"},"date":null,"isForeign":0}
     */

    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * realtime : {"wind":{"windspeed":"7.0","direct":"北风","power":"1级","offset":null},"time":"03:00:00","weather":{"humidity":"94","img":"18","info":"雾","temperature":"13"},"dataUptime":1462821962,"date":"2016-05-10","city_code":"101180101","city_name":"郑州","week":2,"moon":"四月初四"}
         * life : {"date":"2016-5-10","info":{"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"yundong":["较适宜","天气较好，户外运动请注意防晒。推荐您进行室内运动。"],"ziwaixian":["强","紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"],"ganmao":["少发","各项气象条件适宜，无明显降温过程，发生感冒机率较低。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"wuran":["较差","气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"],"chuanyi":["舒适","建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"]}}
         * weather : [{"date":"2016-05-10","info":{"dawn":["1","多云","12","无持续风向","微风","19:16"],"night":["0","晴","17","无持续风向","微风","19:17"],"day":["0","晴","26","无持续风向","微风","05:25"]},"week":"二","nongli":"四月初四"},{"date":"2016-05-11","info":{"dawn":["0","晴","17","无持续风向","微风","19:17"],"night":["1","多云","21","无持续风向","微风","19:18"],"day":["0","晴","32","无持续风向","微风","05:25"]},"week":"三","nongli":"四月初五"},{"date":"2016-05-12","info":{"dawn":["1","多云","21","无持续风向","微风","19:18"],"night":["1","多云","16","北风","3-4 级","19:19"],"day":["2","阴","30","无持续风向","微风","05:24"]},"week":"四","nongli":"四月初六"},{"date":"2016-05-13","info":{"dawn":["1","多云","16","北风","3-4 级","19:19"],"night":["2","阴","16","无持续风向","微风","19:20"],"day":["2","阴","23","无持续风向","微风","05:23"]},"week":"五","nongli":"四月初七"},{"date":"2016-05-14","info":{"dawn":["0","晴","17","无持续风向","微风","19:17"],"night":["1","多云","21","无持续风向","微风","19:18"],"day":["0","晴","32","无持续风向","微风","05:25"]},"week":"六","nongli":"四月初八"},{"date":"2016-05-15","info":{"night":["1","多云","13","东北风","微风","19:30"],"day":["1","多云","22","东北风","微风","07:30"]},"week":"日","nongli":"四月初九"},{"date":"2016-05-16","info":{"night":["0","晴","15","","微风","19:30"],"day":["0","晴","26","","微风","07:30"]},"week":"一","nongli":"四月初十"}]
         * pm25 : {"key":"","show_desc":0,"pm25":{"curPm":"63","pm25":"34","pm10":"63","level":2,"quality":"良","des":"今天的空气质量是可以接受的，除少数异常敏感体质的人群外，大家可在户外正常活动。"},"dateTime":"2016年05月10日03时","cityName":"郑州"}
         * date : null
         * isForeign : 0
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * wind : {"windspeed":"7.0","direct":"北风","power":"1级","offset":null}
             * time : 03:00:00
             * weather : {"humidity":"94","img":"18","info":"雾","temperature":"13"}
             * dataUptime : 1462821962
             * date : 2016-05-10
             * city_code : 101180101
             * city_name : 郑州
             * week : 2
             * moon : 四月初四
             */

            private RealtimeBean realtime;
            /**
             * date : 2016-5-10
             * info : {"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"yundong":["较适宜","天气较好，户外运动请注意防晒。推荐您进行室内运动。"],"ziwaixian":["强","紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"],"ganmao":["少发","各项气象条件适宜，无明显降温过程，发生感冒机率较低。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"wuran":["较差","气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"],"chuanyi":["舒适","建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"]}
             */

            private LifeBean life;
            private Object date;
            private int isForeign;
            /**
             * date : 2016-05-10
             * info : {"dawn":["1","多云","12","无持续风向","微风","19:16"],"night":["0","晴","17","无持续风向","微风","19:17"],"day":["0","晴","26","无持续风向","微风","05:25"]}
             * week : 二
             * nongli : 四月初四
             */

            private List<WeatherBean> weather;

            public RealtimeBean getRealtime() {
                return realtime;
            }

            public void setRealtime(RealtimeBean realtime) {
                this.realtime = realtime;
            }

            public LifeBean getLife() {
                return life;
            }

            public void setLife(LifeBean life) {
                this.life = life;
            }

            public Object getDate() {
                return date;
            }

            public void setDate(Object date) {
                this.date = date;
            }

            public int getIsForeign() {
                return isForeign;
            }

            public void setIsForeign(int isForeign) {
                this.isForeign = isForeign;
            }

            public List<WeatherBean> getWeather() {
                return weather;
            }

            public void setWeather(List<WeatherBean> weather) {
                this.weather = weather;
            }

            public static class RealtimeBean {
                /**
                 * windspeed : 7.0
                 * direct : 北风
                 * power : 1级
                 * offset : null
                 */

                private WindBean wind;
                private String time;
                /**
                 * humidity : 94
                 * img : 18
                 * info : 雾
                 * temperature : 13
                 */

                private WeatherBean weather;
                private int dataUptime;
                private String date;
                private String city_code;
                private String city_name;
                private int week;
                private String moon;

                public WindBean getWind() {
                    return wind;
                }

                public void setWind(WindBean wind) {
                    this.wind = wind;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public WeatherBean getWeather() {
                    return weather;
                }

                public void setWeather(WeatherBean weather) {
                    this.weather = weather;
                }

                public int getDataUptime() {
                    return dataUptime;
                }

                public void setDataUptime(int dataUptime) {
                    this.dataUptime = dataUptime;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getCity_code() {
                    return city_code;
                }

                public void setCity_code(String city_code) {
                    this.city_code = city_code;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                public int getWeek() {
                    return week;
                }

                public void setWeek(int week) {
                    this.week = week;
                }

                public String getMoon() {
                    return moon;
                }

                public void setMoon(String moon) {
                    this.moon = moon;
                }

                public static class WindBean {
                    private String windspeed;
                    private String direct;
                    private String power;
                    private Object offset;

                    public String getWindspeed() {
                        return windspeed;
                    }

                    public void setWindspeed(String windspeed) {
                        this.windspeed = windspeed;
                    }

                    public String getDirect() {
                        return direct;
                    }

                    public void setDirect(String direct) {
                        this.direct = direct;
                    }

                    public String getPower() {
                        return power;
                    }

                    public void setPower(String power) {
                        this.power = power;
                    }

                    public Object getOffset() {
                        return offset;
                    }

                    public void setOffset(Object offset) {
                        this.offset = offset;
                    }
                }

                public static class WeatherBean {
                    private String humidity;
                    private String img;
                    private String info;
                    private String temperature;

                    public String getHumidity() {
                        return humidity;
                    }

                    public void setHumidity(String humidity) {
                        this.humidity = humidity;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }

                    public String getInfo() {
                        return info;
                    }

                    public void setInfo(String info) {
                        this.info = info;
                    }

                    public String getTemperature() {
                        return temperature;
                    }

                    public void setTemperature(String temperature) {
                        this.temperature = temperature;
                    }
                }
            }

            public static class LifeBean {
                private String date;
                private InfoBean info;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public static class InfoBean {
                    private List<String> kongtiao;
                    private List<String> yundong;
                    private List<String> ziwaixian;
                    private List<String> ganmao;
                    private List<String> xiche;
                    private List<String> wuran;
                    private List<String> chuanyi;

                    public List<String> getKongtiao() {
                        return kongtiao;
                    }

                    public void setKongtiao(List<String> kongtiao) {
                        this.kongtiao = kongtiao;
                    }

                    public List<String> getYundong() {
                        return yundong;
                    }

                    public void setYundong(List<String> yundong) {
                        this.yundong = yundong;
                    }

                    public List<String> getZiwaixian() {
                        return ziwaixian;
                    }

                    public void setZiwaixian(List<String> ziwaixian) {
                        this.ziwaixian = ziwaixian;
                    }

                    public List<String> getGanmao() {
                        return ganmao;
                    }

                    public void setGanmao(List<String> ganmao) {
                        this.ganmao = ganmao;
                    }

                    public List<String> getXiche() {
                        return xiche;
                    }

                    public void setXiche(List<String> xiche) {
                        this.xiche = xiche;
                    }

                    public List<String> getWuran() {
                        return wuran;
                    }

                    public void setWuran(List<String> wuran) {
                        this.wuran = wuran;
                    }

                    public List<String> getChuanyi() {
                        return chuanyi;
                    }

                    public void setChuanyi(List<String> chuanyi) {
                        this.chuanyi = chuanyi;
                    }
                }
            }

            public static class WeatherBean {
                private String date;
                private InfoBean info;
                private String week;
                private String nongli;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getNongli() {
                    return nongli;
                }

                public void setNongli(String nongli) {
                    this.nongli = nongli;
                }

                public static class InfoBean {
                    private List<String> dawn;
                    private List<String> night;
                    private List<String> day;

                    public List<String> getDawn() {
                        return dawn;
                    }

                    public void setDawn(List<String> dawn) {
                        this.dawn = dawn;
                    }

                    public List<String> getNight() {
                        return night;
                    }

                    public void setNight(List<String> night) {
                        this.night = night;
                    }

                    public List<String> getDay() {
                        return day;
                    }

                    public void setDay(List<String> day) {
                        this.day = day;
                    }
                }
            }
        }
    }
}
