package com.ssvictor.im.codec;

import cn.hutool.core.util.ClassUtil;
import com.ssvictor.im.dto.*;
import com.ssvictor.im.dto.request.*;
import com.ssvictor.im.dto.response.*;
import com.ssvictor.im.protocol.command.Command;
import com.ssvictor.im.protocol.Serializer;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Object, Class<?>> packetClassMap = new HashMap<>();

    private PacketCodeC () {
        try {
            Set<Class<?>> classSet = ClassUtil.scanPackage("com.ssvictor.im.dto");

            for (Class<?> clazz : classSet) {
                if (clazz == BasePacket.class) {
                    continue;
                }
                packetClassMap.put(clazz.getDeclaredMethod("getCommand").invoke(clazz.newInstance()), clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ByteBuf encode(ByteBuf byteBuf, BasePacket packet) {
        // 1. 创建 ByteBuf 对象
        // ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        // 2. 序列化 Java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public BasePacket decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends BasePacket> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        switch (serializeAlgorithm) {
            case Serializer.JSON_SERIALIZER:
                return Serializer.DEFAULT;

            default:
                return null;
        }
    }

    private Class<? extends BasePacket> getRequestType(byte command) {
        return (Class<? extends BasePacket>) packetClassMap.get(command);
//        switch (command) {
//            case Command.LOGIN_REQUEST:
//                return LoginRequestPacket.class;
//            case Command.LOGIN_RESPONSE:
//                return LoginResponsePacket.class;
//            case Command.MESSAGE_REQUEST:
//                return MessageRequestPacket.class;
//            case Command.MESSAGE_RESPONSE:
//                return MessageResponsePacket.class;
//            case Command.CREATE_GROUP_REQUEST:
//                return CreateGroupRequestPacket.class;
//            case Command.CREATE_GROUP_RESPONSE:
//                return CreateGroupResponsePacket.class;
//            case Command.JOIN_GROUP_REQUEST:
//                return JoinGroupRequestPacket.class;
//            case Command.JOIN_GROUP_RESPONSE:
//                return JoinGroupResponsePacket.class;
//            case Command.QUIT_GROUP_REQUEST:
//                return QuitGroupRequestPacket.class;
//            case Command.QUIT_GROUP_RESPONSE:
//                return QuitGroupResponsePacket.class;
//            case Command.LIST_GROUP_MEMBERS_REQUEST:
//                return ListGroupMembersRequestPacket.class;
//            case Command.LIST_GROUP_MEMBERS_RESPONSE:
//                return ListGroupMembersResponsePacket.class;
//            case Command.GROUP_MESSAGE_REQUEST:
//                return GroupMessageRequestPacket.class;
//            case Command.GROUP_MESSAGE_RESPONSE:
//                return GroupMessageResponsePacket.class;
//            case Command.LOGOUT_REQUEST:
//                return LogoutRequestPacket.class;
//            case Command.LOGOUT_RESPONSE:
//                return LogoutResponsePacket.class;
//
//            default:
//                return null;
//        }

    }

}
