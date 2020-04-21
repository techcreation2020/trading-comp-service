package com.pwmexample.demo.Service;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.pwmexample.demo.configuration.IBrokerChannel;
import com.pwmexample.demo.model.User;

@Service 
public class DataStreamingService {
	
    private final IBrokerChannel iBrokerChannnel;

	public DataStreamingService(IBrokerChannel iBrokerChannnel) {
		super();
		this.iBrokerChannnel = iBrokerChannnel;
	}
   
	 public void sendDataToKafkaTopic(final User user) {
	        MessageChannel messageChannel = iBrokerChannnel.outbound();
	        messageChannel.send(MessageBuilder
	                .withPayload(user)
	                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
	                .build());
	    }
}
