package com.pjmike.websocket.config;

import com.pjmike.websocket.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * @author pjmike
 * @create 2018-09-12 15:45
 */
@Component
public class StompApplicationEventListener implements ApplicationListener<SessionConnectedEvent> {
    private Logger logger = LoggerFactory.getLogger(StompApplicationEventListener.class);
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        logger.info("source:{}",event.getSource());
        logger.info("连接成功");
        messagingTemplate.convertAndSend("/topic/simple", new ResponseMessage("连接成功"));
    }
}
