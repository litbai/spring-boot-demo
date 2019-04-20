package cc.lzy.sb.query;

public class Paginator {
    private int pageIndex = 1;

    private int pageSize = 20;

    public Paginator(int pageIndex, int pageSize) {
        if (pageIndex > 0) {
            this.pageIndex = pageIndex;
        }

        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public String toString() {
        return (this.pageIndex - 1) * this.pageSize + ", " + this.pageSize;
    }
}
