package api.mengkang.net.api;

import api.mengkang.net.ErrorCode;
import api.mengkang.net.Request;
import api.mengkang.net.Response;

/**
 * @author zhoumengkang
 * @date 17/7/26.
 */
public class BaseController {
    Request request;

    public BaseController(Request request) {
        this.request = request;
    }

    protected Response responseError(int errorCode) {
        return new Response(errorCode);
    }

    protected Response parameterNotFound(String parameterName){
        return new Response(ErrorCode.PARAMETER_NOT_FOUND,"参数: " + parameterName + " 不可缺省");
    }

    protected Response parameterFormatError(String parameterName){
        return new Response(ErrorCode.PARAMETER_FORMAT_ERROR,"参数: " + parameterName + " 格式错误");
    }
}
