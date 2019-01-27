package cc.lzy.sb.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第一个sb应用
 *
 * @author chengyu
 * @version 19/1/27
 */
@RestController

public class HelloWorld {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
