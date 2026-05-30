package com.payflow.payflow.repository;

import com.payflow.payflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUpiId(String upiId);

    @Query("SELECT u FROM User u WHERE u.balance > :amount")
    List<User> findUsersWithBalanceGreaterThan(@Param("amount") Double amount);
}
