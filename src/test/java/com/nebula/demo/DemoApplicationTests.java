package com.nebula.demo;

import com.nebula.demo.entity.Product;
import com.nebula.demo.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

import javax.xml.bind.SchemaOutputResolver;
import java.math.BigInteger;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object,Product> prodRedisTemplate;

    @Autowired
    ProductRepository repository;

    @Test
    public void test() {

        Iterable<Product> all = repository.findAll();
        all.forEach(product -> {
            prodRedisTemplate.opsForList().leftPush("product",product);
        });

    }


    @Test
    public void getBlock() {
        Web3j web3j = Web3j.build(new HttpService("http://testnet.nebula-ai.com:8545"));

        web3j.catchUpToLatestAndSubscribeToNewBlocksObservable(DefaultBlockParameter.valueOf(BigInteger.valueOf(1175000)),true)
                .subscribe(ethBlock -> {
                    EthBlock.Block result = ethBlock.getResult();
                    String hash = result.getHash();
//                    System.out.println("block-hash: " + hash);
                    List<EthBlock.TransactionResult> transactions = result.getTransactions();
                    transactions.forEach(t->{
                        System.out.println("has ts: "+((EthBlock.TransactionObject)t.get()).get().getHash());
                    });
                });


//        web3j.catchUpToLatestAndSubscribeToNewTransactionsObservable(DefaultBlockParameter.valueOf(BigInteger.valueOf(1175000)))
//                .subscribe(tx->{
//
//                    System.out.println(tx.getHash());
//                });
    }


    @Test
    public void contextLoads() {

    }

}
