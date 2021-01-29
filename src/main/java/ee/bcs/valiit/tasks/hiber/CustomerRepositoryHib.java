package ee.bcs.valiit.tasks.hiber;

import ee.bcs.valiit.tasks.hiber.CustomersHib;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public interface CustomerRepositoryHib extends JpaRepository<CustomersHib, Integer> {
    int countCustomersHibByIsikukoodContains(String kood);

}
