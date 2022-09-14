package com.gmlee.demo.aio.concurrent.kit;

import java.nio.ByteBuffer;

public class BufferKit {
    /**
     * Get byte [ ].
     *
     * @param byteBuffer the byte buffer
     * @param len        the len
     * @return the byte [ ]
     */
    public static byte[] get(ByteBuffer byteBuffer, int len) {
        if (remain(byteBuffer, len)) {
            byte[] bytes = new byte[len];
            byteBuffer.get(bytes);
            return bytes;
        }
        throw new RuntimeException("数据不足");
    }

    /**
     * 判断数据是否足够
     *
     * @param buffer the buffer
     * @param len    the len
     * @return the boolean
     */
    public static boolean remain(ByteBuffer buffer, int len) {
        return buffer.remaining() >= len;
    }

}
