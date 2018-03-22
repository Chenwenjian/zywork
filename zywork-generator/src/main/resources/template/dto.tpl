package top.zywork.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * {beanName}DTO数据传输对象类<br/>
 *
 * 创建于{createDate}<br/>
 *
 * @author {author}
 * @version 1.0
 */
public class {beanName}DTO extends BaseDTO {

    private static final long serialVersionUID = {serialVersionId}L;

    {fields}
    public {beanName}DTO () {}

    public {beanName}DTO ({constructorParams}) {
        {constructor}
    }

    {fieldsGetterSetter}
    @Override
    public String toString() {
        return "{beanName}DO{" +
                {toString}"}";
    }

}