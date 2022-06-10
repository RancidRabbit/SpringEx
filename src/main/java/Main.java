import beans.services.ClientService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("beans");
        ClientService bean = context.getBean(ClientService.class);
        bean.searchCustomerByProduct(1);
        System.out.println();
        bean.showCustomersProduct(2);

    }

}
