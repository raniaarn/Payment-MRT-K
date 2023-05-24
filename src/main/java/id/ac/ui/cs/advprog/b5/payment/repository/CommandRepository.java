package id.ac.ui.cs.advprog.b5.payment.repository;

import id.ac.ui.cs.advprog.b5.payment.core.UserWalletCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandRepository extends JpaRepository<UserWalletCommand, Integer> {
    @NonNull
    List<UserWalletCommand> findAll();
    @NonNull
    Optional<UserWalletCommand> findById(@NonNull Integer id);

    List<UserWalletCommand> findAllByUserId(Integer userId);
}
