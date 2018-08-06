
package com.nebula.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.web3j.utils.Numeric;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "block")
@Data
public class Block implements Serializable {


    @Id
    private BigInteger number;

    @OneToMany(mappedBy = "block",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JsonManagedReference
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Transaction> transactions;

    //!!!
    private String hash;
    private String parentHash;

//    public void setNonce(BigInteger nonce) {
//        this.nonce = Numeric.encodeQuantity(nonce);
//    }

    private String nonce;
    private String sha3Uncles;
    private String logsBloom;
    private String transactionsRoot;
    private String stateRoot;
    private String receiptsRoot;
    private String author;
    private String miner;
    private String mixHash;

//    public void setDifficulty(BigInteger difficulty) {
//        this.difficulty = Numeric.encodeQuantity(difficulty);
//    }

    private String difficulty;

//    public void setTotalDifficulty(BigInteger totalDifficulty) {
//        this.totalDifficulty =  Numeric.encodeQuantity(totalDifficulty);
//    }

    private String totalDifficulty;
    private String extraData;

//    public void setSize(BigInteger size) {
//        this.size =  Numeric.encodeQuantity(size);
//    }

    private String size;

//    public void setGasLimit(BigInteger gasLimit) {
//        this.gasLimit =  Numeric.encodeQuantity(gasLimit);
//    }

    private String gasLimit;

//    public void setGasUsed(BigInteger gasUsed) {
//        this.gasUsed =  Numeric.encodeQuantity(gasUsed);
//    }

    private String gasUsed;

//    public void setTimestamp(BigInteger timestamp) {
//        this.timestamp =  Numeric.encodeQuantity(timestamp);
//    }

    private String timestamp;

}
