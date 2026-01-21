package org.example;
/*
    it tells the spring that for which class wee have to create beans.
    -> it's a replacement for beans.xml
    -> jabki beans spring ko ye btata hai ki jis method se jo object banega, usko spring manage kare.
    -> method se return hone wala object spring bean hota hai aur uska default scope singleton hota h.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
class bata{
    public void test(){
        System.out.println("test done running!");
    }
}
class loffer{
    public void test(){
        System.out.println("loffer done running!");
    }
}
@Configuration
class Malls{
    @Bean
    public bata batasales(){
        return new bata();
    }

    @Bean
    public loffer loffersales(){
        return new loffer();
    }
}
public class MallConfig{
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Malls.class);
        loffer mall = context.getBean("loffersales",loffer.class);
        mall.test();
    }
}
