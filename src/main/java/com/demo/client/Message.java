package com.demo.client;

import io.netty.channel.Channel;
import lombok.Data;

import java.util.concurrent.ScheduledFuture;


public class Message {
    private final String content;
    private final Channel channel;
    private int retryCount;
    private ScheduledFuture<?> future;

    public Message(String content, Channel channel) {
        this.content = content;
        this.channel = channel;
        this.retryCount = 0;
    }

    public String getContent() {
        return content;
    }

    public Channel getChannel() {
        return channel;
    }

    public int incrementRetryCount() {
        return ++retryCount;
    }

    public void setFuture(ScheduledFuture<?> future) {
        this.future = future;
    }

    public void cancelFuture() {
        if (future != null) {
            future.cancel(true);
        }
    }
}
