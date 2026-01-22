package org.example.Annotations;
/*
    -> repository ek spring stereotype annotation hai jo btata hai ki ye class databases se interact karegi and
       data access logic like CRUD operation ke logics yahi likhe jayege.
    -> its main work is to get the data from the repository, save, update and delete data, run different queries and convert
       data into objects.
    -> @Respositoy @Component ka special form hai.
    ->

    -> Scope:
        - it is used to control how many beans will be created and managed.
        - apan use karege @Scope("sigleton") when we want only one instance of the bean and
        - apan use karege @Scope("prototype") when we want that for every getBean() call a new instance/object is created.
        - @Scope("request") and @Scope("session")  is used in web apps only and for each http request a bean is created and then destroyed.
        - @Scope("request") is used when we request specific data from data headers and session is used for getting logged in user data
          and Cart,preferences.
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
class singletonBean{

}


@Component
@Scope("prototype")
class PrototypeBean {

}


@Component
@Scope("request")
class RequestBean {

}


@Component
@Scope("session")
class SessionBean {
}

@SpringBootApplication
public class RepositoryAndScope {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RepositoryAndScope.class, args);
        singletonBean a = context.getBean(singletonBean.class);
        singletonBean b = context.getBean(singletonBean.class);
        System.out.println("a==b? "+(a==b));


        PrototypeBean c = context.getBean(PrototypeBean.class);
        PrototypeBean d = context.getBean(PrototypeBean.class);
        System.out.println("c==d? "+(c == d)); // false



    }
}
