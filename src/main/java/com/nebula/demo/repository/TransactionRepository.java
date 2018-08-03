package com.nebula.demo.repository;

import com.nebula.demo.entity.Block;
import com.nebula.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {

    public Transaction findTransactionByHash(String hash);

    @Query(value = "select t.block from Transaction t where t.hash = ?1")
    public Block findBlockByTransactionHash(String hash);

}
