package api.mengkang.net;

import java.util.HashMap;

/**
 * @author zhoumengkang
 * @date 17/7/26.
 */
public class ErrorCode {
    /**
     * method 不能为空
     */
    public static final int METHOD_CAN_NOT_BE_NULL = 1001;

    public static final HashMap<Integer,String> ERROR_MESSAGES = new HashMap<>();

    static {
        ERROR_MESSAGES.put(METHOD_CAN_NOT_BE_NULL,"method 不能为空");
    }
}
