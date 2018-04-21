package cn.yout.practice.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class TimeEncoder extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        UnixTime time = (UnixTime) msg;
        ByteBuf encoder = ctx.alloc().buffer(8);
        encoder.writeLong(time.getValue());
        ctx.write(encoder,promise);
    }
}
