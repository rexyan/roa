package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016080300160299";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtfyeWC9wd7qLMv+ka5gkiopSF7RLKapQw7SJPyf74gMUMYguOmDxoSOBYrmV/BnxlA8/cWCPgEH0Et9lgV2zkYtRV+v8R4HMvjLOn3gPnD9bFoE6KaHyhYemb06AVTYxpnAmySsNHpH4PLsBMCmy/1LMz0962jK7RTo5zl9QW3LNv5uwkZw6GpIzlgBl6bfwKF4QI438xca/T9poVBXgbf5Xj3VgmpJ3DyHY0znxxPajEnwgNcGYJ5cTkLktUo2+jsRfn69ZVFzqQEiGR+D9TD4KG1f2EZ+L8yGafHWA9XZxJcJgaHxFj+IyZkNlnb/smgj1odFXmrYjX+IXfwjY3AgMBAAECggEBAJbKbR18MVPSlKxgH7nBNS9lHrFH240SFLpxX+D5LyHMyXZ5wJGuvtHkhRiOeqhserFRHtfjw0p7Ci2PnN6fHYViYCpafFaNPY+WOo8r5zhsC764FYs2RRv+fYtx4zCgtekUNVlAoXki/m0ztO5iISKZOydQfcErhMVmAWl2DF/Q/rr9KtX+ZGmTouzH7HfOmUmdYRrJDhhVY3up9amKjyP6BnLQiGRtEpnqpQ/hnei/7efrqN9GB6GjmxEKjSrPDcgDo2Hp4dTVTl1CZ89c7Zitku0u8jC8fzKCKU90zrAExi0MfosJkNFGBf+fW0Y9EgzGn3lArvEzITj9ezWnJAECgYEA+NeCON8XFGMOccfwcnAzxmEdG2RbOUxhMVGO7Jrdqg3/t+emucmNCtcb9VkJ6vbclvBwAqHYlDHH6DDQ5/TssrrLA7kh/bKVtoql9M9ms1ygNwJmjYfoFPNGSONC3+CZeJsr5y3z+feeVuPJ7bsSCWnC8Gbh4hDwTW02Py2/ciUCgYEAsnzMXlSo4hXQundmaAxDVleeK/xcXjG0d9EodnNASYdq92I48PdQkSrRCczH0Gy52UWvzwHRdeibACGmUCZYtnpd1HIJZd885iq9jecT3Ix2AJxNETXRR39vS2OzPzCrckUyFhDzKf/PLMSRAs2ftGz1wO0BTREgrELtMAeSwisCgYBf+db7Emh3b4E5t1DxAn+lqv0r/HgG0IPIHtnOPijylvWjO9CdZbRlkvPwnEfr/Qy1lsTM7xl2hwDAeFuKq3fDs7HrnLMUg5Wk0zMRChFlantM3DsrqViFBBN+t1CqwpjO4KJ9RscMCUpj3vWaIIXjZYxPZo8Gv1u/sTi0A3QMlQKBgBhMeR4WdG9i/OmFu8FKehhVEUl3r7N7HNB6/wExK31XHC7Vhl73rFY2+AJXiJRNWPfFTuvlmXht1DwuWI/syIL/Iar8Y18lBwtmoiuhW8jYrm/i13MSWo1bQfGHfy2JGK4T0X/Divcaxj+X+w3gpG9Rrhs3qMevHz2UVuoE/evFAoGBAPXQ43U493Wy9bJdfSPwNAD/i/uWHR2ZveuvdCNhyGQV0Vwe7UryEM11xTpWuf4pmbYp3do7wnb1zspOuimLy4dcCGF2TV7sg6p2ZytIL7TeIuJ8tuSDwba3JWaxy14eTFii43+5xze+VfDmGBmn0e62lvIIm32Y6LDO72mi1Jw+";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr9Bz1pkvs0KteX4DbW+JZz6SKe9j1/DF/WET05R8OamMDq39xz1KFRjsbsLk3mvQAN3N19vLijLHFxEdEpSPYlnC2V6DDGvMMSf84Ga5vYzf5qvarjEGM/56aArzYqcG7ZRnPMu0IPogbD/hksaoZ4twEb8YVKHKDwovmvOu2gH6VDw9khL5tNdOIRIPRc7B4WddOANW8qt2iaM+7tOpxtP9x7eRaWfFdnff2HNB+BT+3jRqaRp7up7b7KRNA1/+tmy1aVF1y2voOZnXv0Snpe2qzmEVf6dXnCkDTJtW+dwENDEQ3ha07c+fzdOPl+e7TeiKC3QCA6Ax612jZ5UrEQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://zcw.com:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://zcw.com:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	 
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

