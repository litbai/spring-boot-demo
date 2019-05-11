package cc.lzy.sb.io;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author chengyu
 * @version 19/5/11
 */
public class CleanTest2 {
    public static void main(String[] args) throws Exception {
        Path p = Paths.get("/Users/chengyu/spring-boot-demo/.git/objects/74/8941a99e65222a403cba6b61cd7503604c6c1b");
        Path p2 = Paths.get("/Users/chengyu/spring-boot-demo/src/main/resources/actor_list.data");
        byte[] bytes = Files.readAllBytes(p);
        byte[] bytes2 = Files.readAllBytes(p2);
        byte[] bytes3 = "I am lzy".getBytes();

        String s = new String(bytes2, StandardCharsets.UTF_8);
        System.out.println(s);

        System.out.println(Arrays.equals(bytes, bytes2));

        int i = 'I';
        int a = 'a';
        System.out.println(i);
        System.out.println(a);

    }
}
