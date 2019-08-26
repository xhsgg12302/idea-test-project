package entity;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/5/8 14:40
 * @Description:
 */
public class  MqMain<T> {
    private String sign;
    private String timestamp;
    private T data;
    private String command;
    private String messageId;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "MqMain{" +
                "sign='" + sign + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", data=" + data +
                ", command='" + command + '\'' +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
