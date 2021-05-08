package com.redis.random;

import lombok.*;

import java.io.Serializable;

/**
 * 客户端用户连接session持久化信息
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientSessionInfo implements Serializable {

    /** 连接的客户端ID */
    private String clientId;

    /** 是否清除session */
    private boolean cleanSession;

    /** 连接状态 */
    private String connStatus;

    /** 连接时间 */
    private long connTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientSessionInfo that = (ClientSessionInfo) o;
        return clientId.equals(that.getClientId());
    }

    @Override
    public int hashCode() {
        return clientId.hashCode();
    }
}
