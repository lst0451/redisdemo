package com.nebula.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;

@Component
public class Web3jRunner implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://testnet.nebula-ai.com:8545"));

        web3j.catchUpToLatestAndSubscribeToNewBlocksObservable(DefaultBlockParameter.valueOf(BigInteger.valueOf(1175000)),true)
                .subscribe(ethBlock -> {
                    EthBlock.Block result = ethBlock.getResult();
                    String hash = result.getHash();
                    List<EthBlock.TransactionResult> transactions = result.getTransactions();
                    transactions.forEach(t->{
                        System.out.println("has ts: "+((EthBlock.TransactionObject)t.get()).get().getHash());
                    });
                });
    }
}
