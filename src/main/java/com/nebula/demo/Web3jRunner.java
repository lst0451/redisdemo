package com.nebula.demo;

import com.nebula.demo.entity.Block;
import com.nebula.demo.entity.Transaction;
import com.nebula.demo.entity.WBlock;
import com.nebula.demo.entity.WTransaction;
import com.nebula.demo.repository.BlockRepository;
import com.nebula.demo.repository.WBlockRepository;
import com.nebula.demo.repository.WTransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
    BlockRepository br;

    @Autowired
    WBlockRepository wBlockRepository;

    @Autowired
    WTransactionRepository transactionRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) {
        Web3j web3j = Web3j.build(new HttpService("http://testnet.nebula-ai.com:8545"));

        BigInteger lastBlockNumber = br.getLastBlockNumber();
        lastBlockNumber = lastBlockNumber==null?BigInteger.ONE:lastBlockNumber;

        web3j.catchUpToLatestAndSubscribeToNewBlocksObservable(DefaultBlockParameter.valueOf(lastBlockNumber),true)
                .subscribe(ethBlock -> {
                    WBlock wBlock = new WBlock();
                    List<WTransaction> transactionList = new ArrayList<>();
                    EthBlock.Block result = ethBlock.getResult();

                    BeanUtils.copyProperties(result,wBlock);

                    wBlock.setTransactions(null);

                    try {
                        wBlock = wBlockRepository.save(wBlock);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println(wBlock);

                    List<EthBlock.TransactionResult> transactions = result.getTransactions();

                    for (EthBlock.TransactionResult t : transactions) {
                        WTransaction wTransaction = new WTransaction();
                        BeanUtils.copyProperties(t, wTransaction);
                        wTransaction.setBlock(wBlock);
//                        System.out.println(transaction);
                        transactionList.add(wTransaction);
                    }
                    List<WTransaction> savedTransactions = null;
                    try {
                        savedTransactions = transactionRepository.save(transactionList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    savedTransactions.forEach(t->t.setBlock(block));
                    wBlock.setTransactions(savedTransactions);


                    WBlock savedBlock = null;
                    try {
                        savedBlock = wBlockRepository.save(wBlock);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    System.out.println("saved block: "+ block1);
                });
    }
}
