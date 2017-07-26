package api.mengkang.net;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

/**
 * @author zhoumengkang
 * @date 17/7/25.
 */
public class RequestHandler {

    public static byte[] response(ChannelHandlerContext ctx, Object msg) {
        HttpRequest req = (HttpRequest)msg;

        String uri = req.uri();
        if (uri.length() <= 0) {
            return error();
        }

        // get 参数
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
        if (queryStringDecoder.parameters().size() <= 0) {
            return error();
        }

        if (!queryStringDecoder.parameters().containsKey("method")) {
            return error();
        }

        String method = queryStringDecoder.parameters().get("method").get(0);
        String[] classAndMethodArray = method.split("\\.");

        if (classAndMethodArray.length < 2) {
            return error();
        }

        String clazz = getApiController(classAndMethodArray[0]);
        String function = classAndMethodArray[1];

        Object obj = invoke(clazz, function);

        return encode(obj);
    }

    private static byte[] encode(Object object) {
        String data = JSON.toJSONString(object);
        return data.getBytes();
    }

    /**
     * 暂时调试用
     *
     * @return
     */
    private static byte[] error() {
        String error = "method can't be null";
        return error.getBytes();
    }

    private static String getApiController(String method) {
        char[] tmp = method.toCharArray();
        tmp[0] -= 32;
        return String.valueOf(tmp);
    }

    public static Object invoke(String clazz, String function) {
        Class<?> classname;
        Method methodName;
        Object result = null;

        try {
            classname = Class.forName("api.mengkang.net.api." + clazz + "Controller");
            Object instance = classname.newInstance();
            methodName = classname.getMethod(function);
            result = methodName.invoke(instance);
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
