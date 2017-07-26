package api.mengkang.net;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MixedAttribute;
import io.netty.util.CharsetUtil;

/**
 * @author zhoumengkang
 * @date 17/7/25.
 */
public class RequestHandler {

    /**
     * 我们约定的请求的 method 参数 来查找控制
     */
    private static final String REQUEST_METHOD = "method";

    private static Request requestFetch(ChannelHandlerContext ctx, Object msg){

        Request request = new Request();

        HttpRequest req = (HttpRequest)msg;
        String uri = req.uri();

        // ip
        String clientIP = (String) req.headers().get("X-Forwarded-For");
        if (clientIP == null) {
            InetSocketAddress remoteSocket = (InetSocketAddress) ctx.channel().remoteAddress();
            clientIP = remoteSocket.getAddress().getHostAddress();
        }
        request.setIp(clientIP);

        // method
        request.setMethod(req.method());

        // get
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
        if (queryStringDecoder.parameters().size() > 0) {
            request.getParameters().putAll(queryStringDecoder.parameters());
        }

        // post
        if (req.method().equals(HttpMethod.POST)) {
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(req);
            try {
                List<InterfaceHttpData> postList = decoder.getBodyHttpDatas();
                for (InterfaceHttpData data : postList) {
                    List<String> values = new ArrayList<>();
                    MixedAttribute value = (MixedAttribute) data;
                    value.setCharset(CharsetUtil.UTF_8);
                    values.add(value.getValue());
                    request.getParameters().put(data.getName(), values);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return request;
    }

    public static byte[] response(ChannelHandlerContext ctx, Object msg) {

        Request request = requestFetch(ctx,msg);

        if (!request.getParameters().containsKey(REQUEST_METHOD)) {
            return responseError(ErrorCode.METHOD_CAN_NOT_BE_NULL);
        }

        String method = request.getParameters().get(REQUEST_METHOD).get(0);
        String[] classAndMethodArray = method.split("\\.");

        if (classAndMethodArray.length < 2) {
            return responseError(ErrorCode.METHOD_CAN_NOT_BE_NULL);
        }

        String clazz = getApiController(classAndMethodArray[0]);
        String function = classAndMethodArray[1];

        Object obj = invoke(clazz, function,request);

        return encode(obj);
    }

    private static byte[] encode(Object object) {
        String data = JSON.toJSONString(object);
        return data.getBytes();
    }

    /**
     * 返回错误信息
     *
     * @return
     */
    private static byte[] responseError(int errorCode) {
        Response response = new Response(errorCode);
        return encode(response);
    }

    private static String getApiController(String method) {
        char[] tmp = method.toCharArray();
        tmp[0] -= 32;
        return String.valueOf(tmp);
    }

    private static Object invoke(String clazz, String function,Request request) {
        Class<?> classname;
        Object   classObject;
        Constructor constructor;
        Method methodName;
        Object result = null;

        try {
            classname = Class.forName("api.mengkang.net.api." + clazz + "Controller");
            constructor = classname.getConstructor(Request.class);
            classObject = constructor.newInstance(request);
            methodName = classname.getMethod(function);
            result = methodName.invoke(classObject);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return result;
    }

}
