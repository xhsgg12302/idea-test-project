package site.wtfu.framework.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLReturnObject {
    Integer code;
    String desc;
    Employee employee;

    public XMLReturnObject(){

    }

    public XMLReturnObject(Integer code, String desc, Employee employee){
        this.code = code;
        this.desc = desc;
        this.employee = employee;
    }
}
