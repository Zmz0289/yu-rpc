package com.yupi.example.consumer;

import com.yupi.yurpc.protocol.ProtocolConstant;
import com.yupi.yurpc.protocol.ProtocolMessage;

import java.nio.ByteBuffer;

/**
 * 字节缓存池数据测试
 */
public class ByteBufferTest {

    public static void main(String[] args) throws Exception {
        // from yupi-rpc-data.pcapng
        byte[] bytes = {1, 1, 0, 1, 20, 25, 3, 43, 31, (byte) 212, (byte) 197, (byte) 208, 0, 0, 0, 1, 5};

        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        ProtocolMessage.Header header = new ProtocolMessage.Header();
        byte magic = buffer.get(0);

        System.out.println("magic = " + magic);

        // 校验魔数
        if (magic != ProtocolConstant.PROTOCOL_MAGIC) {
            throw new RuntimeException("消息 magic 非法");
        }

        System.out.println("buffer.get(9) = " + buffer.get(9));

        header.setMagic(magic);
        header.setVersion(buffer.get(1));
        header.setSerializer(buffer.get(2));
        header.setType(buffer.get(3));
        header.setStatus(buffer.get(4));
        header.setRequestId(buffer.getLong(5));
        header.setBodyLength(buffer.getInt(13));

        System.out.println("header = " + header);

    }

}
