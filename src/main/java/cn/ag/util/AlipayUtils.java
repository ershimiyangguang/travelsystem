package cn.ag.util;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.*;
import com.alipay.api.request.AlipayTradePagePayRequest;

import java.util.UUID;

public class AlipayUtils {
    //沙箱支付网关
    private static  String serverUrl="https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    //应用账号
    private static String appId="9021000132685779";

    //个人应用私钥
    private static String privateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCSPuVF+73/ykZSVsoVEWhOOkIjSDREFxLK5Pse8FvXcea3pHCXVayXxbh9yT82BIM3yPCEpLxCBMFAhXFwY6zGYt6RdE/m02BWRyEXBd9zcXCRBc3WLyveh8wyER4HSuvhX88hnRIFyE4FfTqswxTbpwG5LIx6cY6VTBTIHEk808BGRZvMqtvNhSTGPOv0AgAsg31zglWM7o7W4GRPr2hT4CHkFbZc8ViLDClQR/Wq7lHggiT0aidyxPpwDG57E3bnHbMhFw9dkzn6SkCiQMwvQ81HF/3zd00iBc5v1bDMmGxMEbBiYZlR5vy3DebOcz1qu+y+AlEzH0tw95C+T5dpAgMBAAECggEAE8lkteJIo6oXd558evs+9I6XUjS7tZa1mTP/8gCtPGoRwDkEbsVNs78xpfsrjUBxDIQoXpn3SqtgBj3HymACCVlpKZ7mDKH+v82h4X6O7AymIUj6v4bkVZhj/UYpnOz7GyQeXzrkLFocjEuUO7mtQcbHyekIHKzukK6ZhTWmhw8e8YUDwgk71YwAskudCaAo9tT3z29G3SVLYxqkn5t6fg7ApkMSUlzzfwNPWLOs3XL0NuVVm53z2Yi8A2PLHlyUklN6CcP0+4ti+Rl3ixRntuv/1Gd1hh2Y2jnaRcTtkN5U2ymP2G2nkrYM1sTMKlrO/PrLJ2zKEDLQubqPWxIasQKBgQDCzoBpSF6VJWVx/B4G4iHtVimMQLWJdoK4eZqWPF8oeQF4yh5R2PA/pKtHyM+cBxfc6ejwS33BwjLwSZI71bTEqDp2Noc7V7sKp+AmtmYEDBtwVilOneopi90oHV+IpVm3hkINxk5l/Vu4QJNsBwEeZ0EcrrlZprkUpDL6s8dEzQKBgQDAL1V8r2cZ2umsbeW/0bfPbGzc8ruGEB9t1ufdxiq+NVDarDTfCDJ4jQcrCN6nJ8FGdM6aSZqQJSKU1+y2/JG+Bxt35JXtYtPePOSNy0odWvlIDkHOjl27zNH+PtovfhAz6CBx9k/Fo25D1TcJ44JdnLva/tvoIbH5WLKnmv19DQKBgEd7XsNDOvGfd0oMKTh9YdWk+DnuxK5cPjtS49ac8I35WQDpqZZ+9ClmUzNUDFpEgA0gB8mk6dBfYjGpzW8HS/2dfVZDS/wKhSez0hxicayqhKcD9sRSTcpQ+CXwIU4S3ojpSvOjAMGL5kwF5hKNv2tYmBveQUUUWHWqispTjZtBAoGAb2VAVORKi4cDOQn+SMAjndxIAY3EukFwJjeR8E9kCRpYQlfc0YYr0wpTpFMg05zMf61G79FIANM1hzbdI3ekdpQiDM13RcCp88Ax7ipuTDOmGGkDJdoJZMik04zU5QBaHewnmkbeGD3RtQnKPDYeI2qszJQ7HQMi6NfLvjCtS6UCgYBhZuRG8CmQ1FF2A7dl+0/VVgYpPF/3mO4nkiNjG7uYpQX4LYN9grX8yNFhzJFbMTXBoEfXqrm9J5lPd8H30qHZjhyNslB0oHqwOtMgsjnyZE6FIR7aopKeATnrY8f5kbLR0A5aSPz/liwfRLlRamOfPn7iBx+4PxPYkoT51kJJGg==";

    //返回数据格式
    private static String format="json";

    //编码方式
    private static String charset="utf-8";

    //支付宝公钥
    public static String alipayKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAibxOE6t0+eDpXBxLzGe7vGDgfkYI6kJLsAS3Bm1OFk1R0lfwpZyxchnSI0b3CrVatAZLieEqYvl0uVN80eS4ZTEYloUveIVqC0LZjmT5z313alH+/94fnkcss14D4pZLiiIiRufSeY229pDryUlEp41YRCxYO57M00jf98AVyayEhONQsYTy8xGFMQ1uhImz+DOSVEyqcM13EEecjotGDQEWwnXkKrTXvYG2BPCDsP9iiu6N+17PjgqnnocbnZREdKD+GpyXyU7WYhijuDbYVVKAqHchhORzg9D1KJKmZ3kQIbf3f0DvLrCcEyIw40NM1zhffNowYnn4qa4wHWQesQIDAQAB";
    private static String signType="RSA2";

    public static String pay(String name ,Double price){

        //准备接入的配置
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,appId,privateKey,format,charset,alipayKey,signType);
        //request对象
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //商家的post请求接口
        request.setNotifyUrl("http://127.0.0.1:8080/travel/route_detail.html");//回调--post请求
        //商家的get请求接口
        request.setReturnUrl("http://127.0.0.1:8080/travel/route_detail.html");//回调--get请求

        String replace = UUID.randomUUID().toString().replace("-", "");

        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", replace);
        bizContent.put("total_amount", price);
        bizContent.put("subject", name);
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toString());
        String form= "" ;
        try  {
            form = alipayClient.pageExecute(request).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }
}
