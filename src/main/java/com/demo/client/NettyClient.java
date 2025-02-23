package com.demo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


@Slf4j
public class NettyClient {

    private final String host;
    private final int port;
    private final Bootstrap bootstrap;
    private final EventLoopGroup group;
    private final ConcurrentHashMap<String, Message> pendingMessages;
    private Channel clientChannel;


    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.group = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
        this.pendingMessages = new ConcurrentHashMap<>();
    }

    public void start() {
        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new ClientHandler(pendingMessages));
                        }
                    });

            ChannelFuture future = bootstrap.connect(host, port).sync();
            if (future.isSuccess()) {
                clientChannel = (NioSocketChannel) future.channel();
                log.info("服务端开启成功");
            } else {
                log.info("服务端开启失败");
            }

            sendMessage("this is a test message");

            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    /**
     * 客户端关闭
     */
    private void close() {
        //关闭客户端套接字
        if(clientChannel!=null){
            clientChannel.close();
        }
        //关闭客户端线程组
        if (group != null) {
            group.shutdownGracefully();
        }
    }

    public void sendMessage( String message) {
        log.info("start to sendMsg.....");
        Message msg = new Message(message, clientChannel);
        pendingMessages.put(message, msg);
        clientChannel.writeAndFlush(message);
        scheduleResend(msg);
    }

    private void scheduleResend(Message msg) {
        ScheduledFuture<?> future = group.schedule(() -> {
            if (msg.incrementRetryCount() > 3) {
                System.err.println("Message failed after 3 retries: " + msg.getContent());
                pendingMessages.remove(msg.getContent());
            } else {
                System.out.println("Resending message: " + msg.getContent());
                msg.getChannel().writeAndFlush(msg.getContent());
                scheduleResend(msg);
            }
        }, 1, TimeUnit.SECONDS);

        msg.setFuture(future);
    }

    public static void main(String[] args) {
        NettyClient client = new NettyClient("127.0.0.1", 8080);
        client.start();


    }
}
