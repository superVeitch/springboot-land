package com.mybatis.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 到达圈异常类型
 */
public enum ExceptionTypeEnum {
    OUT_RANGE(1, "商户在圈外"),
    NONE_NORMAL(2, "没有正常圈"),
    NONE_EMERGENCY(3, "没有紧急圈"),
    NONE_URGENT(4, "没有超紧急圈"),
    GREATER_EMERGENCY(5, "紧急圈大于正常圈"),
    GREATER_URGENT(6, "超紧急圈大于正常圈");


    private Integer code;
    private String msg;

    public static List<ExceptionTypeEnum> outRange = new ArrayList<ExceptionTypeEnum>();
    public static List<ExceptionTypeEnum> noneRange = new ArrayList<ExceptionTypeEnum>();
    public static List<ExceptionTypeEnum> greaterRange = new ArrayList<ExceptionTypeEnum>();

    static {
        // outer
        outRange.addAll(Arrays.asList(ExceptionTypeEnum.OUT_RANGE));

        //缺少配送范围
        noneRange.addAll(Arrays.asList(ExceptionTypeEnum.NONE_NORMAL, ExceptionTypeEnum.NONE_URGENT, ExceptionTypeEnum.NONE_EMERGENCY));

        //紧急或超紧急大于正常圈
        greaterRange.addAll(Arrays.asList(ExceptionTypeEnum.GREATER_URGENT, ExceptionTypeEnum.GREATER_EMERGENCY));
    }
    ExceptionTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ExceptionTypeEnum of(Integer code){
        ExceptionTypeEnum[] var2 = values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4){
            ExceptionTypeEnum type = var2[var4];
            if (type.code.equals(code) ){
                return type;
            }
        }

        return null;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}
