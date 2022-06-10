package beans.JpaDAO;


import entities.Product;
import factory.Factory;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class CustomerDAO {

    private Factory factory = Factory.getInstance();


    public void searchCustomerByProduct(int id) {
        try(Session currentSession = factory.getFactory().getCurrentSession()) {
            currentSession.beginTransaction();
            Product product = currentSession.get(Product.class, (long) id);
            System.out.printf("Продукт: %s, Список покупателей: %s", product.getTitle(), product.getCustomers());
            currentSession.getTransaction().commit();
        }
    }
}
