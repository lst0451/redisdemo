
package com.nebula.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Data
public class Transaction implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_number")
    @JsonBackReference
    private Block block;

    private String hash;

    private String nonce;

    @Id
    private String blockHash;

//    private BigInteger blockNumber;

    private String transactionIndex;

    @Column(name = "t_from")
    private String from;

    @Column(name = "t_to")
    private String to;

    @Column(length = 255)
    private String value;

    private String gasPrice;

    private String gas;

    private String input;

    private String creates;

    private String publicKey;

    private String raw;

    private String r;

    private String s;

    private String v;

    private String nonceRaw;

    private String blockNumberRaw;

    private String transactionIndexRaw;

    private String valueRaw;

    private String gasPriceRaw;

    private String chainId;

    private String gasRaw;
}
