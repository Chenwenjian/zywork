package top.zywork.vo;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * {beanName}VO值对象类<br/>
 *
 * 创建于{createDate}<br/>
 *
 * @author {author}
 * @version 1.0
 */
public class {beanName}VO extends BaseVO {

    private static final long serialVersionUID = {serialVersionId}L;

    {fields}
    public {beanName}VO () {}

    public {beanName}VO ({constructorParams}) {
        {constructor}
    }

    {fieldsGetterSetter}
    @Override
    public String toString() {
        return "{beanName}DO{" +
                {toString}"}";
    }

}