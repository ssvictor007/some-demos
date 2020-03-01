package com.ssvictor.im.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ssvictor007
 * @date 2019-10-8
 */
@Data
@NoArgsConstructor
public class Session {
    /**
     * 用户唯一性标识
     */
    private String userId;
    /**
     * 用户名称
     */
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + ":" + userName;
    }

}
