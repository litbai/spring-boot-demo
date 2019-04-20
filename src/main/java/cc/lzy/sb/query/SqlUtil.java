package cc.lzy.sb.query;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * Created by kuaidao on 2017/12/17.
 */
public class SqlUtil {
  public static String filterField(String field) {
    return '`' + field.trim() + '`';
  }

  public static String filterValue(Object value) {

    if (null == value) {
      return "NULL";
    }

    if (value instanceof Number) {
      return String.valueOf(value);
    }

    if (value instanceof String) {
      return "\'" + value + "\'";
    }

    if (value instanceof Collection<?>) {
      return "(" + StringUtils.join(((Collection<?>) value).toArray(), ",") + ")";
    }

    return "";
  }
}
