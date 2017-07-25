package api.mengkang.net.api;

import api.mengkang.net.Request;

/**
 * @author zhoumengkang
 * @date 17/7/26.
 */
public class BaseController {
    Request request;

    public BaseController(Request request) {
        this.request = request;
    }
}
