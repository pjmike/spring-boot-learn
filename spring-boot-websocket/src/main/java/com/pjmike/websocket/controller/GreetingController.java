package com.pjmike.websocket.controller;

import com.pjmike.websocket.model.RequestMessage;
import com.pjmike.websocket.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author pjmike
 * @create 2018-09-11 23:40
 */
@Controller
public class GreetingController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    /**
     * 处理发往 /app/greeting目的地的消息
     *
     * @param greeting
     * @return
     */
    @MessageMapping("/greeting")
//    @SendTo("/topic/say")
    public ResponseMessage handle(RequestMessage greeting) {
        //Spring的某一个消息转换器会将STOMP消息的负载转换为 RequestMessage对象
        System.out.println(greeting.getName());
        return new ResponseMessage("welcome，" + greeting.getName());
    }

    @SubscribeMapping("/subscribe")
    public ResponseMessage subscribe() {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("欢迎订阅");
        return responseMessage;
    }

    @Scheduled(fixedRate = 10000)
    public void scheduledTask() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ss HH:mm:ss");
        simpMessagingTemplate.convertAndSend("/topic/simp", new ResponseMessage("定时任务：" + simpleDateFormat.format(new Date())));
    }
}
