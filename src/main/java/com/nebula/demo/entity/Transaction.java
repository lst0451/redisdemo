
package com.nebula.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.web3j.utils.Numeric;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "transaction")
@Data
public class Transaction implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_number")
    @JsonBackReference
    private Block block;


    private String blockHash;

    @Id
    private String hash;

    public void setNonce(BigInteger nonce) {
        this.nonce =  Numeric.encodeQuantity(nonce);
    }

    private String nonce;

//    public void setTransactionIndex(BigInteger transactionIndex) {
//        this.transactionIndex =  Numeric.encodeQuantity(transactionIndex);
//    }

    private String transactionIndex;

//    public void setValue(BigInteger value) {
//        this.value =  Numeric.encodeQuantity(value);
//    }

    private String value;

//    public void setGasPrice(BigInteger gasPrice) {
//        this.gasPrice =  Numeric.encodeQuantity(gasPrice);
//    }

    private String gasPrice;

//    public void setGas(BigInteger gas) {
//        this.gas =  Numeric.encodeQuantity(gas);
//    }

    private String gas;
    private String input;
    private String creates;
    private String publicKey;
    private String raw;
    private String r;
    private String s;
    private int v;  // see https://github.com/web3j/web3j/issues/44

    @Column(name = "t_from")
    private String from;

    @Column(name = "t_to")
    private String to;

    @Transient
    private BigInteger block_number;

}
