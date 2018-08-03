package com.nebula.demo.controller;

import com.nebula.demo.entity.Block;
import com.nebula.demo.entity.Transaction;
import com.nebula.demo.repository.BlockRepository;
import com.nebula.demo.service.BlockChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@RestController
public class TestController {

    @Autowired
    RedisTemplate blockRedisTemplate;
    @Autowired
    BlockRepository repository;



    @Autowired
    BlockChainService blockChainService;





    @GetMapping("/latestblock/{count}")
    public ResponseEntity<?> getLatestBlock(@PathVariable Integer count) {
        System.out.println("-----------------------------------");
        Page<Block> latestBlocks = blockChainService.getLatestBlocks(new PageRequest(0, count));

        return ResponseEntity.ok(latestBlocks);

    }
    @GetMapping("/block/{number}")
    public ResponseEntity<?> getBlockByNumber(@PathVariable BigInteger number) {
        System.out.println("-----------------------------------");
        Block block = repository.findBlockByNumber(number);
//        Page<Block> latestBlocks = blockChainService.getLatestBlocks(new PageRequest(0, count));
        return ResponseEntity.ok(block);

    }

    @GetMapping("/transaction/{hash}")
    public ResponseEntity<?> getBlockByNumber(@PathVariable String hash) {

        Block block   = blockChainService.getBlockByTransactionHash(hash);

        return ResponseEntity.ok(block);

    }

    @GetMapping("/blockwrite/{number}")
    public ResponseEntity<?> getOneBlock(@PathVariable Long number) {
        System.out.println("-----------------------------------");
        Block block = repository.findOne(BigInteger.valueOf(number));
        BoundHashOperations ops = blockRedisTemplate.boundHashOps("blocks");
        ops.put(block.getNumber(),block);
        return ResponseEntity.ok(block);

    }

    @GetMapping("/blockredis/{number}")
    public ResponseEntity<?> getOneBlockFromRedis(@PathVariable Long number) {
        System.out.println("-----------------------------------");
        BoundHashOperations ops = blockRedisTemplate.boundHashOps("blocks");
        Block block = (Block) ops.get(number);
        return ResponseEntity.ok(block);

    }


    @GetMapping("/get")
    public ResponseEntity<?> get(){
        Web3j web3j = Web3j.build(new HttpService("http://testnet.nebula-ai.com:8545"));
        EthBlock.Block ethBlock = null;
        try {
             ethBlock = web3j.ethGetBlockByHash("0x9620a0ac30f2f30cf30eb2bc726b5e360c5ab24c40a3111ba4ff2e75dd84fa1f", true).sendAsync().get().getBlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ethBlock);
    }

}
