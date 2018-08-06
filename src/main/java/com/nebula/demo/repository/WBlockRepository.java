package com.nebula.demo.repository;


import com.nebula.demo.entity.WBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface WBlockRepository extends JpaRepository<WBlock,BigInteger> {

    //    @Query(value = "select max(number) from block",nativeQuery = true)
//    public BigInteger getLastBlockNumber();

//    @Query(value = "select b from Block b order by b.number desc")
//    public Page<Block> getLatestBlocks(Pageable pageable);

//    @Cacheable(cacheNames = "vvv")
//    public Block findBlockByNumber(BigInteger number);

}
