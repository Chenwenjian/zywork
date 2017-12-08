package top.zywork.dto;

import java.io.Serializable;

/**
 * 所有DTO的父类，具有所有DTO的统一标识符id属性，id属性为Long类型
 * 创建于2017-08-24
 *
 * @author 王振宇
 * @version 1.0
 */
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = -6526471404848464405L;

    @Override
    public String toString() {
        return "BaseDTO{}";
    }
}
