
package com.nebula.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigInteger;

@Entity
@Data
public class Transaction {

    @ManyToOne
    @JoinColumn(name = "block_number")
    private Block block;

    private String hash;

    private BigInteger nonce;

    @Id
    private String blockHash;

//    private BigInteger blockNumber;

    private BigInteger transactionIndex;

    private String from;

    private String to;

    private BigInteger value;

    private BigInteger gasPrice;

    private BigInteger gas;

    private String input;

    private String creates;

    private String publicKey;

    private String raw;

    private String r;

    private String s;

    private BigInteger v;

    private String nonceRaw;

    private String blockNumberRaw;

    private String transactionIndexRaw;

    private String valueRaw;

    private String gasPriceRaw;

    private BigInteger chainId;

    private String gasRaw;
}
