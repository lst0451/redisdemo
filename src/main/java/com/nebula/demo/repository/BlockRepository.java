package com.nebula.demo.repository;

import com.nebula.demo.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BlockRepository extends JpaRepository<Block,BigInteger> {


}
