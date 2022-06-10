package beans.JpaDAO;
import entities.Customer;
import factory.Factory;
import entities.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProductDAO {


    private Factory factory = Factory.getInstance();

    public  List<Product> findAll() {
        List<Product> products;
        try (Session currentSession = factory.getFactory().getCurrentSession()) {
            currentSession.beginTransaction();
            products = currentSession.createQuery("select p from Product p").getResultList();
            System.out.println(products);
            currentSession.getTransaction().commit();
        }
        return products;
    }

    public void deleteById(int id) {
        try (Session currentSession = factory.getFactory().getCurrentSession()) {
            currentSession.beginTransaction();
            Product product = currentSession.get(Product.class, (long) id);
            if (product != null) {
                currentSession.delete(product);
            }
            currentSession.getTransaction().commit();
        }
    }


    public Product saveOrUpdate(Product product) {
        Product result = null;
        try (final Session currentSession = factory.getFactory().getCurrentSession()) {
            currentSession.beginTransaction();
            if (product.getId() != null) {
                Product existingProduct = currentSession.get(Product.class, product.getId());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setTitle(product.getTitle());
                result = existingProduct;
            } else {
                currentSession.save(product);
                result = product;
            }
            currentSession.getTransaction().commit();
        }
        return result;
    }

    public Product findById(int id) {
        Product product;
        try (final Session currentSession = factory.getFactory().getCurrentSession()) {
            currentSession.beginTransaction();
            product = currentSession.createQuery("select p from Product p where p.id = " + (long) id, Product.class).getSingleResult();
            currentSession.getTransaction().commit();
        }
        /*System.out.println(product);*/
        return product;
    }

    public void tableBuild() {
        try (Session currentSession = factory.getFactory().getCurrentSession()) {
            String sql = Files.lines(Paths.get("script.sql")).collect(Collectors.joining(" "));
            currentSession.beginTransaction();
            currentSession.createNativeQuery(sql).executeUpdate();
            currentSession.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCustomersProducts(int id) {
        try(Session currentSession = factory.getFactory().getCurrentSession()) {
            currentSession.beginTransaction();
            Customer customer = currentSession.get(Customer.class, (long) id);
            System.out.printf("Покупатель: %s, Корзина: %s",customer.getName(),customer.getProducts());
            currentSession.getTransaction().commit();
        }
    }

}
