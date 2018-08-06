package com.nebula.demo.repository;

import com.nebula.demo.entity.WTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WTransactionRepository extends JpaRepository<WTransaction,String> {

    public WTransaction findTransactionByHash(String hash);

//    @Query(value = "select t.block from Transaction t where t.hash = ?1")
//    public Block findBlockByTransactionHash(String hash);

}
