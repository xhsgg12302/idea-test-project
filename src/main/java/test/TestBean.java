package test;

import java.math.BigDecimal;

public class TestBean {

    public String dType;
    public Integer dTypeMod; //有没有改变
    public Integer dTypeSize; //列长


    public String dChanel;
    public Integer dChanelMod;
    public Integer dChanelSize;
    public java.math.BigDecimal dChanelSum;

    public TestBean(String dType, Integer dTypeMod, Integer dTypeSize, String dChanel, Integer dChanelMod, Integer dChanelSize, BigDecimal dChanelSum, String dResource, BigDecimal dIncome) {
        this.dType = dType;
        this.dTypeMod = dTypeMod;
        this.dTypeSize = dTypeSize;
        this.dChanel = dChanel;
        this.dChanelMod = dChanelMod;
        this.dChanelSize = dChanelSize;
        this.dChanelSum = dChanelSum;
        this.dResource = dResource;
        this.dIncome = dIncome;
    }

    public void setdChanelSum(BigDecimal dChanelSum) {
        this.dChanelSum = dChanelSum;
    }

    public BigDecimal getdChanelSum() {
        return dChanelSum;
    }

    public String dResource;
    public java.math.BigDecimal dIncome;

    public TestBean() {
    }



    public void setdType(String dType) {
        this.dType = dType;
    }

    public String getdType() {
        return dType;
    }


    public Integer getdTypeMod() {
        return dTypeMod;
    }

    public Integer getdTypeSize() {
        return dTypeSize;
    }

    public String getdChanel() {
        return dChanel;
    }

    public Integer getdChanelMod() {
        return dChanelMod;
    }

    public Integer getdChanelSize() {
        return dChanelSize;
    }


    public String getdResource() {
        return dResource;
    }

    public BigDecimal getdIncome() {
        return dIncome;
    }

    public void setdTypeMod(Integer dTypeMod) {
        this.dTypeMod = dTypeMod;
    }

    public void setdTypeSize(Integer dTypeSize) {
        this.dTypeSize = dTypeSize;
    }

    public void setdChanel(String dChanel) {
        this.dChanel = dChanel;
    }

    public void setdChanelMod(Integer dChanelMod) {
        this.dChanelMod = dChanelMod;
    }

    public void setdChanelSize(Integer dChanelSize) {
        this.dChanelSize = dChanelSize;
    }

    public void setdResource(String dResource) {
        this.dResource = dResource;
    }

    public void setdIncome(BigDecimal dIncome) {
        this.dIncome = dIncome;
    }

    @Override
    public String toString() {
        return "test.TestBean{" +
                "dType='" + dType + '\'' +
                ", dTypeMod=" + dTypeMod +
                ", dTypeSize=" + dTypeSize +
                ", dChanel='" + dChanel + '\'' +
                ", dChanelMod=" + dChanelMod +
                ", dChanelSize=" + dChanelSize +
                ", dChanelSum=" + dChanelSum +
                ", dResource='" + dResource + '\'' +
                ", dIncome=" + dIncome +
                '}';
    }

    public static void main(String [] args){
     TestBean testBean = new TestBean();
     testBean.setdTypeMod(1);
     if(testBean.getdTypeMod()==1){
         System.out.println("nihoa");
     }
    }
}
