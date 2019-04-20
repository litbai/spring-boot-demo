/**
 * 
 */
package cc.lzy.sb.query;

import java.util.Collection;

/**
 * reference from xiaoyao@item-center
 */
public class Condition implements Comparable<Condition> {
    private static final String GT = ">";
    private static final String GE = ">=";
    private static final String LT = "<";
    private static final String LE = "<=";
    private static final String EQ = "=";
    private static final String NEQ = "!=";
    private static final String NOT_IN = "NOT IN";
    private static final String IN = "IN";
    private static final String LIKE = "LIKE";

    public enum PRIORITY {
        LOW, NORMAL, HIGH,
    }

    private String operator;
    private String field;
    private String value;
    private PRIORITY priority;
    private boolean needParenthesis = false;

    private Condition(Condition one,String operator,Condition two){
        this.priority = PRIORITY.NORMAL;
        this.operator = operator;
        this.field = one.toString();
        this.value = two.toString();
        this.needParenthesis = true;
    }

    private Condition(String field, String operator, Object value) {
        this.field = SqlUtil.filterField(field);
        this.operator = operator;
        this.value = SqlUtil.filterValue(value);
        this.priority = PRIORITY.NORMAL;
    }

    public static Condition complexAnd(Condition one, Condition two) {
        return new Condition(one, " AND ", two);
    }

    public static Condition complexOr(Condition one, Condition two) {
        return new Condition(one, " OR ", two);
    }

    public static Condition gt(String field, Object value) {
        return new Condition(field, Condition.GT, value);
    }

    public static Condition ge(String field, Object value) {
        return new Condition(field, Condition.GE, value);
    }

    public static Condition lt(String field, Object value) {
        return new Condition(field, Condition.LT, value);
    }

    public static Condition le(String field, Object value) {
        return new Condition(field, Condition.LE, value);
    }

    public static Condition eq(String field, Object value) {
        return new Condition(field, Condition.EQ, value);
    }

    public static Condition neq(String field, Object value) {
        return new Condition(field, Condition.NEQ, value);
    }

    public static Condition notIn(String field, Collection<? extends Number> items) {
        return new Condition(field, Condition.NOT_IN, items);
    }

    public static Condition in(String field, Collection<? extends Number> items) {
        return new Condition(field, Condition.IN, items);
    }

    public static Condition like(String field, Object value) {
        return new Condition(field, Condition.LIKE, value);
    }

    /**
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     *            the operator to set
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field
     *            the field to set
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the priority
     */
    public PRIORITY getPriority() {
        return priority;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(PRIORITY priority) {
        this.priority = priority;
    }

    public String toString() {
        if(needParenthesis){
            return " ( " + this.field + " " + this.operator + " " + this.value + " ) ";
        }else {
            return this.field + " " + this.operator + " " + this.value;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Condition o) {
        return this.priority.compareTo(o.priority);
    }

}
