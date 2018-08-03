package com.nebula.demo.service;

import com.nebula.demo.entity.Block;
import com.nebula.demo.entity.Transaction;
import com.nebula.demo.repository.BlockRepository;
import com.nebula.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@Cacheable(cacheNames = "blocks")
public class BlockChainService {

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public Page<Block> getLatestBlocks(Pageable pageable){

        return blockRepository.getLatestBlocks(pageable);
    }

    public Block getBlockByBlockNumber(BigInteger number){
        return blockRepository.findBlockByNumber(number);
    }

    public Transaction getTransactionByHash(String hash) {
        return transactionRepository.findTransactionByHash(hash);
    }

    public Block getBlockByTransactionHash(String hash){
        return transactionRepository.findBlockByTransactionHash(hash);

    }

}
