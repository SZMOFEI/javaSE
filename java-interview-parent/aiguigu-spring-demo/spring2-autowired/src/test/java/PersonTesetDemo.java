import com.mofei.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author mofei
 * @date 2021/3/17 21:57
 */
public class PersonTesetDemo {
    @Test
    public void testAuto() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("autowired.xml");
        Object car = context.getBean("person");
        System.out.println("person = " + car);
        Person bean = context.getBean(Person.class);
        System.out.println("bean = " + bean);
    }
}
