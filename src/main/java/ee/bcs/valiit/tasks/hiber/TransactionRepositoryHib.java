package ee.bcs.valiit.tasks.hiber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public interface TransactionRepositoryHib extends JpaRepository<TransactionsHib, Integer> {

}
