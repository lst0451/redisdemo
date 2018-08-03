package com.nebula.demo.repository;

import com.nebula.demo.entity.Block;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block,BigInteger> {

    @Query(value = "select max(number) from block",nativeQuery = true)
    public BigInteger getLastBlockNumber();

    @Query(value = "select b from Block b order by b.number desc")
    public Page<Block> getLatestBlocks(Pageable pageable);

}
