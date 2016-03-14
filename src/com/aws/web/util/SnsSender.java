package com.aws.web.util;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;


public class SnsSender {

	public static void main(String[] args) {
		
		System.out.println("This is a test");
		
		SnsSender sns = new SnsSender();
		sns.sendToArn("test");
	}
	
	public void sendToArn(String message){
		String topicArn = "arn:aws:sns:us-east-1:515651501414:animus-contact";
		AmazonSNSClient snsClient = new AmazonSNSClient();		          
		snsClient.withRegion(Regions.US_EAST_1);
	
		
		//publish to an SNS topic
		String msg = message;
		PublishRequest publishRequest = new PublishRequest(topicArn, msg);
		PublishResult publishResult = snsClient.publish(publishRequest);
		//print MessageId of message published to SNS topic
		System.out.println("MessageId - " + publishResult.getMessageId());
	}
	
	
}
