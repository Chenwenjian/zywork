package top.zywork.query;

import java.math.BigDecimal;
import java.util.Date;

/**
 * {beanName}Query查询对象类<br/>
 *
 * 创建于{createDate}<br/>
 *
 * @author {author}
 * @version 1.0
 */
public class {beanName}Query extends BaseQuery {

    private static final long serialVersionUID = {serialVersionId}L;

    {fields}
    public {beanName}Query () {}

    public {beanName}Query ({constructorParams}) {
        {constructor}
    }

    {fieldsGetterSetter}
    @Override
    public String toString() {
        return "{beanName}DO{" +
                {toString}"}";
    }

}