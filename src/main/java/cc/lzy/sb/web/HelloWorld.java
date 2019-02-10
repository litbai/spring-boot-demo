package cc.lzy.sb.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${placeholder.custom}")
    private String placeholder;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World A!";
    }

    @RequestMapping("/placeholder")
    public String placeholder() {
        logger.warn("this is warn : " + placeholder);
        return placeholder;
    }
}
