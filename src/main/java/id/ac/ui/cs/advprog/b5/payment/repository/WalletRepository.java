package id.ac.ui.cs.advprog.b5.payment.repository;

import id.ac.ui.cs.advprog.b5.payment.core.Wallet;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    @NonNull
    List<Wallet> findAll();
    @NonNull
    Optional<Wallet> findById(@NonNull Integer Id);

}
