package ee.bcs.valiit.tasks.hiber;

import ee.bcs.valiit.tasks.hiber.AccountsHib;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public interface AccountRepositoryHib extends JpaRepository<AccountsHib, Integer> {

}
