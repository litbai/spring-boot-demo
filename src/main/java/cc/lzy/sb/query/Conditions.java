/**
 *
 */
package cc.lzy.sb.query;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 条件查询
 * reference from xiaoyao@item-center
 */
public class Conditions {

    private List<Condition> andConditionList;

    private List<Condition> orConditionList;

    private List<Order> orderList;

    private Paginator paginator;

    private Group group;

    /**
     * 分页
     */
    private String conditions;

    /**
     * limit 分页优先级高于limit，如果有分页，则不需要limit
     */
    private Integer limit;

    public Conditions() {
        andConditionList = new ArrayList<Condition>();
        orConditionList = new ArrayList<Condition>();
        orderList = new ArrayList<Order>();
    }

    public Conditions and(Condition condition) {
        this.andConditionList.add(condition);
        return this;
    }

    public Conditions or(Condition condition) {
        this.orConditionList.add(condition);
        return this;
    }

    public Conditions paginator(Paginator paginator) {
        this.paginator = paginator;
        return this;
    }

    public Conditions order(Order order) {
        this.orderList.add(order);
        return this;
    }

    public Conditions group(Group group){
        this.group = group;
        return this;
    }

    public Conditions limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public String getConditions() {
        return this.conditions;
    }

    public Conditions build() {
        StringBuilder sqlBuilder = new StringBuilder();
        if (!andConditionList.isEmpty()) {
            Collections.sort(andConditionList);
            sqlBuilder.append(StringUtils.join(andConditionList, " AND "));
        }

        if (!orConditionList.isEmpty()) {
            if (sqlBuilder.length() > 0) {
                sqlBuilder.append(" OR ");
            }
            sqlBuilder.append(StringUtils.join(orConditionList, " OR "));
        }

        if(null != group){
            sqlBuilder.append(" GROUP BY ");
            sqlBuilder.append(group);
        }

        if (!orderList.isEmpty()) {
            sqlBuilder.append(" ORDER BY ");
            sqlBuilder.append(StringUtils.join(orderList, ", "));
        }

        if (null != paginator) {
            sqlBuilder.append(" LIMIT ");
            sqlBuilder.append(paginator);
        } else if (null != limit) {
            sqlBuilder.append(" LIMIT ");
            sqlBuilder.append(limit);
        } else {
            sqlBuilder.append(" LIMIT 5000");
        }
        this.conditions = sqlBuilder.toString();
        return this;
    }

    @Override
    public String toString() {
        this.build();
        return conditions;
    }

}
