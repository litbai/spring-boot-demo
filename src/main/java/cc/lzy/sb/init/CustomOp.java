package cc.lzy.sb.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import sun.misc.Unsafe;

import java.util.Arrays;

/**
 * 应用启动完成之后, do something
 *
 * @author chengyu
 * @version 19/1/27
 */
@Component
public class CustomOp implements ApplicationRunner {
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("This is do something after app start, args: " + Arrays.toString(args.getSourceArgs()));
        //Unsafe.getUnsafe().fullFence();
    }
}
