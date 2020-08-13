package _utils.utils;

public class MQEnum {

    /**
     * MQ返回值
     */
    public enum MQResultCodeEnum{
        SUCCESS("0","正确(成功)"),
        NETWORK_ANOMALY("1","网络异常"),
        FORMAT_ERROR("2","格式不正确"),
        DATABASE_EXCEPTION("3","数据库异常"),
        SIGNATURE_ERROR("4","签名不正确"),
        FORMAT_CONVERSION_EXCEPTION("5","格式转换异常"),
        PARAMETER_CHECKING_NULL("7","必传项或参数校验为空"),
        CHECK_FEE_FAILURE("6B","查费失败"),
        NO_EQU_IP("1","未查询到相机ip"),
        NO_EQU_ENTER_RECORD("1","未找到入场记录。"),
        NO_TIMEOUT_NEED_NOT_PAY("6D","未超过超时时间，不需缴费"),
        PAID_BUT_TIMEOUT_NEED_PAY("6E","已缴费，但超过超时时间，需再缴费"),
        NO_PAY("6C","未缴过费"),
        MONTH_CARD_NO_EXIST("8001","月卡不存在"),
        CAR_OWNERS_NO_EXIST("7001","车主不存在"),
        CAR_BRAND_NO_EXIST("7002","车牌号不存在"),
        SYSTEM_EXCEPTION("9999","系统异常");

        private String code;
        private String description;
        MQResultCodeEnum (String code,String description){
            this.code=code;
            this.description=description;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }

    /**
     * MQ开类型下发接口 操作类型
     */
    public enum MQcardTypeOperatorTypeEnum{
        ADD("0","新增"),
        DEL("1","删除"),
        UPDATE("2","修改");

        private String code;
        private String description;
        MQcardTypeOperatorTypeEnum (String code,String description){
            this.code=code;
            this.description=description;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }


    /**
     * MQ访客车辆下发接口 操作类型
     */
    public enum MQvisitorListOperatorTypeEnum{
        ADD("1","新增"),
        UPDATE("2","修改");

        private String code;
        private String description;
        MQvisitorListOperatorTypeEnum (String code,String description){
            this.code=code;
            this.description=description;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }
    /**
     * MQ新增移除黑名单类型
     */
    public enum MQBlackListTypeEnum{
        ADD("1","新增"),
        DEL("2","移除");

        private String code;
        private String description;
        MQBlackListTypeEnum (String code,String description){
            this.code=code;
            this.description=description;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }

    /**
     * MQ岗亭人员登录登出接口 操作类型 1：登入 2：登出
     */
    public enum MQloginOutTypeEnum{
        LOGIN("1","登入"),
        LOGIN_OUT("2","登出");

        private String code;
        private String description;
        MQloginOutTypeEnum (String code,String description){
            this.code=code;
            this.description=description;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }

    /**
     * MQ接口码
     */
    public enum MQInterfaceCommandCodeEnum{
        COMMAND_inOrder("inOrder","进场数据同步接口"),
        COMMAND_outOrder("outOrder","离场数据同步接口"),
        COMMAND_opened("opened","遥控开闸数据上报接口"),
        COMMAND_upCarSpaces("upCarSpaces","剩余车位同步接口"),
        COMMAND_cardType("cardType","卡类型下发接口"),
        COMMAND_cardOpen("cardOpen","卡开通下发接口"),
        COMMAND_sendRenewal("sendRenewal","卡续费下发接口"),
        COMMAND_updateCardMoney("updateCardMoney","卡消费上报接口"),
        COMMAND_sendCardRefund("sendCardRefund","卡退费下发接口"),
        COMMAND_updateCar("updateCar","车牌号变更下发接口"),
        COMMAND_getPayMoney("getPayMoney","获取停车信息及停车费用接口"),
        COMMAND_payCost("payCost","缴费接口"),
        COMMAND_carOwner("carOwner","车主信息下发接口"),
        COMMAND_car("car","车辆信息下发接口"),
        COMMAND_blackList("blackList","新增移除黑名单接口"),
        COMMAND_deviceInfo("deviceInfo","停车场设备信息接口"),
        COMMAND_deviceStatus("deviceStatus","设备状态上报接口"),
        COMMAND_internalCar("internalCar","内部车辆下发接口"),
        COMMAND_loginOut("loginOut","岗亭人员登录登出接口"),
        COMMAND_gateOpened("gateOpened","遥控开闸数据上报接口"),
        COMMAND_inRule("inRule","入口规则下发接口"),
        COMMAND_visitorList("visitorList","访客车辆下发接口");

        private String code;
        private String description;
        MQInterfaceCommandCodeEnum (String code,String description){
            this.code=code;
            this.description=description;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }


    /**
     * MQ MD5私钥生成签名串Origin
     */
    public enum MQMD5EncodeOriginEnum{
        ORIGIN_anft("anft","anft"),
        ORIGIN_qianfang("qianfang","qianfang");

        private String code;
        private String description;
        MQMD5EncodeOriginEnum (String code,String description){
            this.code=code;
            this.description=description;
        }
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }


    public enum MQInScanRecordEnum{
        SUCCESS("0","正确(成功)"),
        FAIL("1","失败"),
        NETWORK_ANOMALY("2","网络异常");

        private String code;
        private String description;

        MQInScanRecordEnum(String code,String description){
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }




}
