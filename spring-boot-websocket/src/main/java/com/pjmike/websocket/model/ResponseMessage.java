package com.pjmike.websocket.model;

/**
 * 响应消息类
 *
 * @author pjmike
 * @create 2018-09-12 0:24
 */
public class ResponseMessage {
    private String responseMessage;

    public ResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseMessage() {
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
