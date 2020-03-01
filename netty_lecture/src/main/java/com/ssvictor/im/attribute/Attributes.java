package com.ssvictor.im.attribute;

import com.ssvictor.im.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
