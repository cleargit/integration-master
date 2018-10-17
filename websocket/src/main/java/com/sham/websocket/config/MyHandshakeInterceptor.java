package com.sham.websocket.config;

import com.sham.common.utils.ComUtil;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class MyHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        HttpServletRequest servletRequest = ComUtil.getRequest();
        HttpServletResponse servletResponse = ComUtil.getReponse();
        //小程序
        if (!StringUtils.isEmpty(servletRequest.getHeader("Sec-WebSocket-Protocol"))) {
            servletResponse.addHeader("Sec-WebSocket-Protocol", servletRequest.getHeader("Sec-WebSocket-Protocol"));
        }
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
