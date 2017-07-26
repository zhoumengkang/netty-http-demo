package api.mengkang.net;

import java.util.HashMap;

/**
 * @author zhoumengkang
 * @date 17/7/26.
 */
public class ErrorCode {

    public static final int SYSTEM_ERROR = 1000;
    public static final int METHOD_CAN_NOT_BE_NULL = 1001;
    public static final int PARAMETER_NOT_FOUND = 1002;
    public static final int PARAMETER_FORMAT_ERROR = 1003;

    public static final HashMap<Integer,String> ERROR_MESSAGES = new HashMap<>();

    static {
        ERROR_MESSAGES.put(METHOD_CAN_NOT_BE_NULL,"method 不能为空");
        ERROR_MESSAGES.put(SYSTEM_ERROR,"系统异常");
    }
}
