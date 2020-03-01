package com.ssvictor.im.protocol;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public interface Serializer {

    /**
     * json 序列化
     */
    byte JSON_SERIALIZER = 1;

    /**
     * 默认序列化方式
     */
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return 序列化算法标识
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     * @param object java对象
     * @return 序列化后的字节数组
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     * @param clazz java对象的Class类对象
     * @param bytes 序列化后的字节数组
     * @return java对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
