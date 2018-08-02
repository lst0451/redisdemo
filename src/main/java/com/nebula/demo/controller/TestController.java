package com.nebula.demo.controller;

import com.nebula.demo.entity.Product;
import com.nebula.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class TestController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object,Product> prodRedisTemplate;


    @Autowired
    ProductRepository repository;

    @GetMapping("/products")
    public ResponseEntity<?> getData(){

        Iterable<Product> all = repository.findAll();

//        Product range = prodRedisTemplate.opsForList().rightPop("product");
//        System.out.println(range);
        return ResponseEntity.ok(all);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<?> getProdByid(@PathVariable("id") Long id){

        Product byId = repository.findById(Long.valueOf(id));

        return ResponseEntity.ok(byId);
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
