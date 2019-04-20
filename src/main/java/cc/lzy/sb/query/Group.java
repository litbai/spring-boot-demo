package cc.lzy.sb.query;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by yingmu on 17/8/30.
 * Group by 子句
 */
public class Group {
    private Collection<String> field;

    public Group(Collection<String> field) {
        this.field = field;
    }

    public static Group build(String ... field){
        HashSet<String> set = Sets.newHashSet(field);
        return new Group(set);
    }

    @Override
    public String toString() {
        String join = StringUtils.join(field.toArray(), ",");
        return join;
    }
}
