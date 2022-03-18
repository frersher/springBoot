package com.shine.util.test1;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class HttpUtils {

        public static void main(String[] args) {
            // 需要爬的网页的文章列表
            String url = "http://www.toutiao.com/news_finance/";
            //文章详情页的前缀(由于今日头条的文章都是在group这个目录下,所以定义了前缀,而且通过请求获取到的html页面)
            String url2="http://www.toutiao.com/group/";
            //链接到该网站
            Connection connection = Jsoup.connect(url);
            Document content = null;
            try {
                //获取内容
                content = connection.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //转换成字符串
            String htmlStr = content.html();
            //因为今日头条的文章展示比较奇葩,都是通过js定义成变量,所以无法使用获取dom元素的方式获取值
            String jsonStr = StringUtils.substringBetween(htmlStr,"var _data = ", ";");
            System.out.println(jsonStr);
            Map parse = (Map) JSONObject.parse(jsonStr);
            JSONArray parseArray = (JSONArray) parse.get("real_time_news");
            Map map=null;
            List<Map> maps=new ArrayList<>();
            //遍历这个jsonArray,获取到每一个json对象,然后将其转换成Map对象(在这里其实只需要一个group_id,那么没必要使用map)
            for(int i=0;i<parseArray.size();i++){
                map = (Map)parseArray.get(i);
                maps.add((Map)parseArray.get(i));
                System.out.println(map.get("group_id"));

            }
            //遍历之前获取到的map集合,然后分别访问这些文章详情页
            for (Map map2 : maps) {
                connection = Jsoup.connect(url2+map2.get("group_id"));
                try {
                    Document document = connection.get();
                    //获取文章标题
                    Elements title = document.select("[class=article-title]");
                    System.out.println(title.html());
                    //获取文章来源和文章发布时间
                    Elements articleInfo = document.select("[class=articleInfo]");
                    Elements src = articleInfo.select("[class=src]");
                    System.out.println(src.html());
                    Elements time = articleInfo.select("[class=time]");
                    System.out.println(time.html());
                    //获取文章内容
                    Elements contentEle = document.select("[class=article-content]");
                    System.out.println(contentEle.html());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}