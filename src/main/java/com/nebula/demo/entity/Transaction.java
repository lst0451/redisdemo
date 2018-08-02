
package com.nebula.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
public class Transaction {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "block_number")
    @JsonBackReference
    private Block block;

    private String hash;

    private BigInteger nonce;

    @Id
    private String blockHash;

//    private BigInteger blockNumber;

    private BigInteger transactionIndex;

    @Column(name = "t_from")
    private String from;

    @Column(name = "t_to")
    private String to;

    @Column(length = 255)
    private String value;

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
