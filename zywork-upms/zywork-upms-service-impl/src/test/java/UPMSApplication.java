import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class UPMSApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/config/spring-*.xml");
        context.start();
        System.out.println("zywork-upms started...");
        System.in.read();
    }

}
