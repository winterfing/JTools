package tools.urlAnalyze;

import java.util.HashMap;
import java.util.Map;

public class UrlAnalyze
{

    /**
     * @Title: main
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author Dangzhang
     * @param args
     * @throws
     */
    public static void main(String[] args)
    {
        String url = "https://www.baidu.com/s?wd=18787584355&rsv_spt=1&rsv_iqid=0x8bf499cc0001820e&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=0&rsv_n=2&rsv_sug3=2&rsv_t=44586fd7eA81HZLOt59eok5vHHPgKOeJL2bquMlvqghByLPPkxHzuXB59ASTScCgQMRT&inputT=5150&rsv_sug4=5151";
        urlAnalyzeByString(url);
    }

    public static Map<String, String> urlAnalyzeByString(String url)
    {
        Map<String, String> resultMap = new HashMap<String, String>();
        String path = url.substring(0, url.indexOf("?"));
        System.out.println("path:" + path);
        System.out.println("参数：");
        resultMap.put("path", path);
        String[] urls = url.substring(url.indexOf("?") + 1).split("&");
        for (String str : urls)
        {
            String[] keyValue = str.split("=");
            resultMap.put(keyValue[0], keyValue[1]);
            System.out.println(keyValue[0] + ":" + keyValue[1]);
        }
        return resultMap;
    }

}
