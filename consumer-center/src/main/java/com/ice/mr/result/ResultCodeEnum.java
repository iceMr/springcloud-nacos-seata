package com.ice.mr.result;

public enum ResultCodeEnum {

    /**
     *成功
     */
    DEFAULT_SUCCESS("SUCCESS","成功"),
    /**
     * 默认异常
     */
    DEFAULT_FAILED("UNKNOWN_ERROR","系统繁忙"),

    /**
     * 参数校验未通过
     */
    VALIDATOR_FAILED("VALIDATOR_FAILED","参数校验未通过"),

    /**
     * 非法Session
     */
    DEFAULT_ILLEGAL_SESSION("ILLEGAL_SESSION","非法SESSION"),

    /**
     *请填写SWAGGER认证头
     */
    DEFAULT_ILLEGAL_SWAGGER("SWAGGER_AUTHORIZATION","请填写SWAGGER认证头"),
    /**
     *非法用户
     */
    ILLEGAL_USER("ILLEGAL_USER","用户名或密码错误"),
    /**
     * 数据不存在
     */
    NODATA("NODATA","数据不存在"),

    /**
     * 图片验证码不正确
     */
    ILLEGAL_KAPTCHA("ILLEGAL_KAPTCHA","图片验证码不正确"),

    /**
     *旧密码不正确
     */
    ILLEGAL_OLDPASSWORD("ILLEGAL_OLDPASSWORD","旧密码不正确"),


    /**
     *父级菜单已不存在
     */
    INEXISTENCE_MENU_PARENT("INEXISTENCE_MENU_PARENT","父级菜单已不存在"),

    /**
     *只允许删除叶子节点
     */
    REMOVE_MENU("REMOVE_MENU","只允许删除叶子节点"),

    /**
     *该用户不允许被删除
     */
    REFUSE_DELETE_USER("REFUSE_DELETE_USER","该用户不允许被删除"),

    /**
     *操作过于频繁
     */
    FREQUENTLY_CALL("FREQUENTLY_CALL","操作过于频繁"),

    /**
     *短信验证码不正确
     */
    ILLEGAL_PHONE_CODE("ILLEGAL_PHONE_CODE","短信验证码不正确"),

    /**
     *该手机号不存在或已停用
     */
    USER_PHONE_NOEXEIST("USER_NOEXEIST","该手机号不存在或已停用"),

    /**
     *驻留时间过长,请重新发起
     */
    DWELL_TIME_LONG("DWELL_TIME_LONG","驻留时间过长,请重新发起"),

    /**
     *请关闭其他找回页面,重新发起
     */
    CLOSE_OTHER_PAGE("CLOSE_OTHER_PAGE","请关闭其他找回页面,重新发起"),

    /**
     *暂时无法获取自增锁,请稍后
     */
    LOCKED("LOCKED","暂时无法获取锁,请稍后"),


    /**
     *您没有权限查询该接口
     */
    FORBIDDEN("FORBIDDEN","您没有权限查询该接口"),


    /**
     *出现多个有效账户,请联系管理员
     */
    MULTIPLE_ACCOUNT("MULTIPLE_ACCOUNT","出现多个有效账户,请联系管理员"),

    /**
     *无效的文件对象
     */
    FILE_ERROR("FILE_ERROR","无效的文件对象"),

    /**
     *无效的文件地址
     */
    FILE_PATH_ERROR("FILE_PATH_ERROR","无效的文件地址"),


    /**
     * 未定义分页参数
     */
    PAGE_PARAM_ERROR("PAGE_PARAM_ERROR","未定义分页参数"),

    /**
     * 未定义分页参数格式不正确
     */
    PAGE_PARAM_FORAMT_ERROR("PAGE_PARAM_FORAMT_ERROR","未定义分页参数格式不正确"),


    ;

    private  String code;
    private  String message;
    ResultCodeEnum(String code ,String message ){
        this.code =code;
        this.message =message;
    }
    public  String  code(){
        return  this.code;
    }
    public  String  message(){
        return  this.message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResultCodeEnum getEnum(String code) {
        for (ResultCodeEnum ele : ResultCodeEnum.values()) {
            if (ele.code().equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
