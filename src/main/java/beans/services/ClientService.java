package beans.services;


import beans.JpaDAO.CustomerDAO;
import beans.JpaDAO.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ProductDAO productDAO;

    private CustomerDAO customerDAO;

    @Autowired
    public ClientService(ProductDAO productDAO, CustomerDAO customerDAO) {
        this.productDAO = productDAO;
        this.customerDAO = customerDAO;
    }

    public void showCustomersProduct(int id) {
        productDAO.showCustomersProducts(id);
    }

    public void searchCustomerByProduct(int id) {
        customerDAO.searchCustomerByProduct(id);
    }

}
