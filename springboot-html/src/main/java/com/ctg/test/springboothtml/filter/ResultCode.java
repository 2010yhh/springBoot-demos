package com.ctg.test.springboothtml.filter;

/**
 * @ClassName: ResultCode
 * @Description: 社区消息码枚举类
 * @Author: zhujunkai
 * @Date: 2019/9/2 11:32
 * @Version: 1.1
 **/
public enum ResultCode {

    /**
     * 成功
     **/
    SUCCESS("00000000", "请求处理成功"),


    /**
     * 参数验证错误
     */
    PARAMETER_VERIFY_ERROR("00000002","参数异常"),

    /**
     * 图片转换异常
     */
    FACE_BASE64_GENERATE_ERROR("00000003", "Base64图片生成异常"),

    /**
     * 权限校验相关
     */
    REQUEST_TOKEN_IS_NULL("70000006", "无权访问相关资源，请求头中未检测到Token"),


    /**
     * 未知错误
     */
    UNKNOWN_FAIL("00000001", "未知错误"),

    /**
     * 系统参数
     */
    GE_PARA_NOT_EXIST("70819101", "系统参数数据不存在"),
    GE_PARA_NOT_SELECTED("70819102", "未查询到系统参数数据"),
    GE_PARA_GET_ERROR("70819103", "获取系统参数出错"),
    GE_PARA_LIST_ERROR("70819104", "获取系统参数列表出错"),
    GE_PARA_INSERT_ERROR("70819105", "新增系统参数出错"),
    GE_PARA_UPDATE_ERROR("70819106", "更新系统参数出错"),
    GE_PARA_DELETE_ERROR("70819107", "删除系统参数出错"),
    GE_PARA_VERIFY_ERROR("70819108", "系统参数验证失败"),

    KZF_HOUSE_PERSON_ERROR("70830001", "查询房屋及人员信息异常"),
    KZF_HOUSE_RESOVE_DATA_ERROR("70830002", "解析模型逻辑以及通行记录异常"),

    XJ_MODEL_1_ERROR("70830003", "XJ模型一定时任务异常"),
    XJ_MODEL_2_ERROR("70830004", "XJ模型二定时任务异常"),
    XJ_MODEL_3_ERROR("70830005", "XJ模型三定时任务异常"),
    XJ_MODEL_4_ERROR("70830006", "XJ模型四定时任务异常"),
    XJ_MODEL_5_ERROR("70830007", "XJ模型五定时任务异常"),
    XD_MODEL_1_ERROR("70830008", "吸毒模型一定时任务异常"),
    XD_MODEL_2_ERROR("70830009", "吸毒模型二定时任务异常"),

    JSBR_MODEL_ERROR("70830010", "精神病人员分析任务异常"),
    WLQKRY_MODEL_ERROR("70830010", "外来前科人员分析任务异常"),

    YSMYRY_HOUSE_PERSON_ERROR("70830011", "查询房屋及人员信息异常"),
    YSMYRY_HOUSE_RESOVE_DATA_ERROR("70830012", "解析模型逻辑以及通行记录异常"),


    ;


    private String key;

    private String value;

    ResultCode(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}