package top.zywork.dos;

import java.math.BigDecimal;
import java.util.Date;

/**
 * {beanName}DO数据对象实体类<br/>
 *
 * 创建于{createDate}<br/>
 *
 * @author {author}
 * @version 1.0
 */
public class {beanName}DO extends BaseDO {

    private static final long serialVersionUID = {serialVersionId}L;

    {fields}
    public {beanName}DO () {}

    public {beanName}DO ({constructorParams}) {
        {constructor}
    }

    {fieldsGetterSetter}
    @Override
    public String toString() {
        return "{beanName}DO{" +
                {toString}"}";
    }

}
