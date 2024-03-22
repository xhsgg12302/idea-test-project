package site.wtfu.framework.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @XmlRootElement xml序列化
 * @Data json序列化
 */
@XmlRootElement
@Data
public class XMLReturnObject {
    private Integer code;
    private String desc;
    private Employee employee;

    public XMLReturnObject(){}

    public XMLReturnObject(Integer code, String desc, Employee employee){
        this.code = code;
        this.desc = desc;
        this.employee = employee;
    }
}
