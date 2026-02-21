package com.example.user_event_analytics.annotation;

import com.example.user_event_analytics.enums.EventType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Document
public @interface TrackEvent {
    String action() default "";
}
