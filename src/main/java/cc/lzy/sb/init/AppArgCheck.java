package cc.lzy.sb.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 检查传入的应用启动参数
 *
 * @author chengyu
 * @version 19/1/27
 */
@Component
public class AppArgCheck {
    @Autowired
    public AppArgCheck(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        System.out.println("debug mode: " + debug);

        System.out.println("original: " + Arrays.toString(args.getSourceArgs()));
        System.out.println(args.getNonOptionArgs());
    }
}
