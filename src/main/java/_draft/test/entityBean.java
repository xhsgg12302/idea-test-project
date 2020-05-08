package _draft.test;

public class entityBean {
    private String type;
    private String style;



    private String pay_chanel;
    private String pay_resource;
    private String income;

    public entityBean() {
    }

    @Override
    public String toString() {
        return "_draft.test.entityBean{" +
                "type='" + type + '\'' +
                ", style='" + style + '\'' +
                ", pay_chanel='" + pay_chanel + '\'' +
                ", pay_resource='" + pay_resource + '\'' +
                ", income='" + income + '\'' +
                '}';
    }

    public entityBean(String type, String style,String pay_chanel, String pay_resource, String income) {
        this.type = type;
        this.style=style;
        this.pay_chanel = pay_chanel;
        this.pay_resource = pay_resource;
        this.income = income;
    }

    public String getType() {
        return type;
    }

    public String getPay_chanel() {
        return pay_chanel;
    }

    public String getPay_resource() {
        return pay_resource;
    }

    public String getIncome() {
        return income;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPay_chanel(String pay_chanel) {
        this.pay_chanel = pay_chanel;
    }

    public void setPay_resource(String pay_resource) {
        this.pay_resource = pay_resource;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
