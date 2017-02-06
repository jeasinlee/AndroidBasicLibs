package cn.wwah.basekit.base.entity;

import java.io.Serializable;

/**
 * 后台自定义异常
 */
public class PassException extends Exception implements Serializable {
    private String code;
    private String message;
    private Class aClass;

    /**
     * 木有网络
     **/
    public static final String ERROR_NET = "00000001";
    public static final String ERROR_SYSTEM = "00000000";
    public static final String ERROR_UNKNOWN = "00000002";
    /**
     * TOKEN异常
     */
    public static final String TOKEN_NULLERROR = "01010012";
    public static final String TOKEN_INVALID = "01010014";
    public static final String UserNUll = "01010006";
    /**
     * 连接异常
     */
    public static final String ConnectError = "010100016";
    /**
     * 异常消息为空
     */
    public static final String MESSAGE_NULL = "80000001";
    /**
     * 参数不全
     */
    public static final String PARAMENT_Incomplete = "80000002";
    /**
     * 商品无库存
     */
    public static final String NoInventory = "80000003";
    public static final String NoInventory_Code = "50012002";
    /**
     * 门店无该商品
     */
    public static final String Store_NoGoods = "80000004";
    public static final String Store_NoGoods_Code = "50012003";

    public PassException(String errorCode, String errorMessage, Class aClass) {
        super();
        this.code = errorCode;
        this.message = errorMessage;
        this.aClass = aClass;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public String toString() {
        return "PassException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", aClass=" + aClass +
                '}';
    }
}
