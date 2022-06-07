import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {

    private static Factory instance;

    private static SessionFactory factory;

    private Factory() {
        init();
    }

    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public void init() {

        factory = new Configuration()
                    .configure("configs/hibernate.cfg.xml")
                    .buildSessionFactory();
    }

    public SessionFactory getFactory() {
        return factory;
    }
}
