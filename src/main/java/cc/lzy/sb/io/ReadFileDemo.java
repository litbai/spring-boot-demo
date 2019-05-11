package cc.lzy.sb.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chengyu
 * @version 19/4/10
 */
public class ReadFileDemo {
    public static void main(String[] args) {
        List<ActorBgImg> bgImg = new ArrayList<>();

        bgImg.add(new ActorBgImg(1, "1"));
        bgImg.add(new ActorBgImg(2, "2"));
        bgImg.add(new ActorBgImg(3, "3"));

        bgImg.add(new ActorBgImg(1, "11"));
        bgImg.add(new ActorBgImg(2, "22"));
        bgImg.add(new ActorBgImg(3, "33"));

        bgImg.add(new ActorBgImg(1, "111"));
        bgImg.add(new ActorBgImg(2, "222"));
        bgImg.add(new ActorBgImg(4, "333"));


        Map<Long, String> map = bgImg.stream().collect(Collectors.toMap(
                ActorBgImg::getId, ActorBgImg::getImage, (x1, x2) -> x2
        ));

        System.out.println(map);

        try {
            try {
                Integer.parseInt("c123c");
            } catch (Exception e) {
                throw new RuntimeException("parseInt ex", e);
            }
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("===========");
            System.out.println(e.getCause());
            System.out.println(e.getCause().getCause());
        }



        System.out.println("fafa");
    }


    static class ActorBgImg {
        private long id;
        private String image;

        public ActorBgImg(long id, String image) {
            this.id = id;
            this.image = image;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
