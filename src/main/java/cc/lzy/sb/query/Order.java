package cc.lzy.sb.query;

public class Order {

    private static final String DESC = "DESC";
    private static final String ASC = "ASC";

    private String field;

    private String method;

    private Order(String field, String method) {
        this.field = field;
        this.method = method;
    }

    public static Order desc(String field) {
        return new Order(field, Order.DESC);
    }

    public static Order asc(String field) {
        return new Order(field, Order.ASC);
    }

    public String toString() {
        return this.field + " " + this.method;
    }
}
