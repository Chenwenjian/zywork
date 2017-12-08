package top.zywork.query;

/**
 * 状态查询对象，可用于更新指定记录的状态
 * 更新记录状态需要提供记录的主键id和status值
 * 创建于2017-08-23
 *
 * @author 王振宇
 * @version 1.0
 */
public class StatusQuery<PK> extends BaseQuery {

    private static final long serialVersionUID = -8543807353237586473L;
    private PK id;
    private Integer status;

    public StatusQuery() {}

    public StatusQuery(PK id, Integer status) {
        this.id = id;
        this.status = status;
    }

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
