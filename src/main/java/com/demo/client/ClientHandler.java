package com.demo.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private ChannelHandlerContext ctx;
    private ChannelPromise promise;


    private final ConcurrentHashMap<String, Message> pendingMessages;


    public ClientHandler(ConcurrentHashMap<String, Message> pendingMessages) {
        this.pendingMessages = pendingMessages;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端开始初始化，被激活");
        super.channelActive(ctx);
        this.ctx = ctx;
        ctx.writeAndFlush("这是一个初始化消息");
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String response = (String) msg;
        if (pendingMessages.containsKey(response)) {
            Message message = pendingMessages.remove(response);
            message.cancelFuture();
            System.out.println("Received ACK for message: " + response);
        } else {
            System.out.println("Received message from server: " + response);
        }
    }



//    public synchronized ChannelPromise sendMessage(Object message) {
//        while (ctx == null) {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//                //logger.error("等待ChannelHandlerContext实例化");
//            } catch (InterruptedException e) {
//                log.info("等待ChannelHandlerContext实例化过程中出错",e);
//            }
//        }
//        promise = ctx.newPromise();
//        ctx.writeAndFlush(message);
//        return promise;
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
