package activemq;

import java.io.Serializable;
import java.util.Map;

/**
 * @author bisu
 * @Description:${DESCRIPTION}
 * @create 2017-03-02 14:53
 **/
public class ReturnMqMain implements Serializable {
    private String messageId;
    private String command;
    private String code;
    private String msg;
    private Map data;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReturnMqMain{" +
                "messageId='" + messageId + '\'' +
                ", command='" + command + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
