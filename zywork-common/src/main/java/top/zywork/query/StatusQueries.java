package top.zywork.query;

/**
 * 状态查询对象，可用于更新指定记录的状态
 * 更新记录状态需要提供记录的主键id和status值
 * 创建于2017-08-23
 *
 * @author 王振宇
 * @version 1.0
 */
public class StatusQueries extends BaseQuery {

    private static final long serialVersionUID = -8543807353237586473L;
    private Long[] ids;
    private Integer status;

    public StatusQueries() {}

    public StatusQueries(Long[] ids, Integer status) {
        this.ids = ids;
        this.status = status;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
