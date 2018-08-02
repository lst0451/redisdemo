package com.nebula.demo;

import com.nebula.demo.entity.Block;
import com.nebula.demo.entity.Product;
import com.nebula.demo.entity.Transaction;
import com.nebula.demo.repository.BlockRepository;
import com.nebula.demo.repository.ProductRepository;
import com.nebula.demo.repository.TransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class Web3jRunner implements ApplicationRunner {

    @Autowired
    BlockRepository repository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://testnet.nebula-ai.com:8545"));

        BigInteger lastBlockNumber = repository.getLastBlockNumber();
        lastBlockNumber = lastBlockNumber==null?BigInteger.ONE:lastBlockNumber;

        web3j.catchUpToLatestAndSubscribeToNewBlocksObservable(DefaultBlockParameter.valueOf(lastBlockNumber),true)
                .subscribe(ethBlock -> {
                    Block block = new Block();
                    List<Transaction> transactionList = new ArrayList<>();
                    EthBlock.Block result = ethBlock.getResult();
                    BeanUtils.copyProperties(result,block);
                    System.out.println(block);
                    String hash = result.getHash();
                    List<EthBlock.TransactionResult> transactions = result.getTransactions();

                    transactions.forEach(t->{
                        Transaction transaction = new Transaction();
                        BeanUtils.copyProperties(t,transaction);
                        System.out.println(transaction);
                        transactionList.add(transaction);
                    });
                    List<Transaction> savedTransactions = transactionRepository.save(transactionList);
                    savedTransactions.forEach(t->t.setBlock(block));
                    block.setTransactions(savedTransactions);
                    repository.save(block);
                });
    }
}
