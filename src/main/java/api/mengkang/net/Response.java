package api.mengkang.net;

/**
 * @author zhoumengkang
 * @date 17/7/26.
 */
public class Response {
    /**
     * 成功时 0 ,如果大于 0 则表示则显示error_msg
     */
    private int errorCode;
    private String errorMsg;
    private Object data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response(int errorCode) {
        this.errorCode = errorCode;
        if (ErrorCode.ERROR_MESSAGES.containsKey(errorCode)){
            this.errorMsg = ErrorCode.ERROR_MESSAGES.get(errorCode);
        }
    }

    public Response(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Response(Object data) {
        this.data = data;
    }

    public Response() {
    }
}
