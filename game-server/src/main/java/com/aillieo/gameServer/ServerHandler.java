package com.aillieo.gameServer;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
@Qualifier("serverHandler")
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
        log.info("client msg:"+ msg);
        String clientIdToLong= ctx.channel().id().asLongText();
        log.info("client long id:" + clientIdToLong);
        String clientIdToShort= ctx.channel().id().asShortText();
        log.info("client short id:" + clientIdToShort);
        if(msg.contains("exit")){
            //close
            ctx.channel().close();
        }else{
            //send to client
            // ctx.channel().writeAndFlush("Your msg is:"+msg);
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        log.info("Remote " + ctx.channel().remoteAddress() + " active !");

        ctx.channel().writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel disconnected");
        super.channelInactive(ctx);
    }
}
