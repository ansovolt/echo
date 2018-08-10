package com.ansosoft.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import javax.servlet.Filter;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.strategy.sampling.LocalizedSamplingStrategy;

import java.net.URL;

@Configuration
@EnableAutoConfiguration
public class WebConfig {

    @Value("${aws.xray.fixedSegmentName}")
    String fixedSegmentName;


    static {
        AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard();//.withPlugin(new EC2Plugin()).withPlugin(new ECSPlugin());
        URL ruleFile = WebConfig.class.getResource("/sampling-rules.json");
        builder.withSamplingStrategy(new LocalizedSamplingStrategy(ruleFile));
        AWSXRay.setGlobalRecorder(builder.build());
    }

    @Bean
    public Filter TracingFilter() {
        return new AWSXRayServletFilter(fixedSegmentName);
    }
}