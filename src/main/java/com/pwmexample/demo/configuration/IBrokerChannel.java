package com.pwmexample.demo.configuration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface IBrokerChannel {

	@Output("inbound")
	MessageChannel outbound();
	  
		/*
		 * @Output("output") MessageChannel outbound();
		 */
}
