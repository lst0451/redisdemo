package com.nebula.demo.controller;


import com.nebula.demo.entity.Block;
import com.nebula.demo.entity.Transaction;
import com.nebula.demo.repository.BlockRepository;
import com.nebula.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    BlockRepository blockRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @RequestMapping(value = "tx/{tx}", method = RequestMethod.GET)
    public ResponseEntity<?> gettx(@PathVariable String tx){
        Transaction transaction = null;
        Block block;
        transaction  = transactionRepository.findByHash(tx);
        BigInteger bi = transaction.getBlock().getNumber();
        block = blockRepository.findByNumber(bi);
        return ResponseEntity.ok(block);
    }

    @RequestMapping(value = "blocknumber/{blocknumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getnumber(@PathVariable Long blocknumber){
        Block block = null;
        block = blockRepository.findByNumber(BigInteger.valueOf(blocknumber));
        return ResponseEntity.status(HttpStatus.OK).body(block);
    }


    @RequestMapping(value = "minerAddress/{minerAddress}", method = RequestMethod.GET)
    public ResponseEntity<?> getMinerAddress(@PathVariable String minerAddress){
        Block block;
        Page<Block> blockList = blockRepository.findByMiner(new PageRequest(0,10),minerAddress);
        return ResponseEntity.status(HttpStatus.OK).body(blockList);
    }
}
