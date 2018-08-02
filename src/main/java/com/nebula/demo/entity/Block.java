
package com.nebula.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Data
public class Block {

    @Id
    private BigInteger number;

    private String hash;

    private String parentHash;

    private BigInteger nonce;

    private String sha3Uncles;

    @Column(length = 1024)
    private String logsBloom;

    private String transactionsRoot;

    private String stateRoot;

    private String receiptsRoot;

    private String author;

    private String miner;

    private String mixHash;

    private BigInteger difficulty;

    private BigInteger totalDifficulty;

    private String extraData;

    private BigInteger size;

    private BigInteger gasLimit;

    private BigInteger gasUsed;

    private BigInteger timestamp;

    @OneToMany(mappedBy = "block",cascade = {CascadeType.ALL})
    @JsonManagedReference
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Transaction> transactions;

//    private List<String> uncles;

    private String sealFields;

    private String totalDifficultyRaw;

    private String timestampRaw;

    private String gasUsedRaw;

    private String numberRaw;

    private String gasLimitRaw;

    private String nonceRaw;

    private String difficultyRaw;

    private String sizeRaw;
}
