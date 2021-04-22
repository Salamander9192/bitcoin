package com.jiadongrui.trans.domain;

import java.security.PublicKey;

/**
 * @program: bitcoin
 * @description: 交易对象
 * @author: jiadongrui
 * @create: 2021-04-22 21:32
 **/
public class Transaction {

    /**
     * 交易唯一标识
     */
    private String transactionID;

    /**
     * 上一笔交易
     */
    private Transaction preTransaction;

    /**
     * 下一位所有者的公钥
     */
    private PublicKey nextOwnerPublicKey;

    /**
     * 上一笔交易和下一个拥有者的公钥的哈希值
     */
    private String transactionHash;

    /**
     * 当前所有者的签名：由交易哈希值+当前所有者的私钥形成的数字签名
     */
    private String currOwnerSign;

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Transaction getPreTransaction() {
        return preTransaction;
    }

    public void setPreTransaction(Transaction preTransaction) {
        this.preTransaction = preTransaction;
    }

    public PublicKey getNextOwnerPublicKey() {
        return nextOwnerPublicKey;
    }

    public void setNextOwnerPublicKey(PublicKey nextOwnerPublicKey) {
        this.nextOwnerPublicKey = nextOwnerPublicKey;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public String getCurrOwnerSign() {
        return currOwnerSign;
    }

    public void setCurrOwnerSign(String currOwnerSign) {
        this.currOwnerSign = currOwnerSign;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID='" + transactionID + '\'' +
                ", preTransaction=" + preTransaction +
                ", nextOwnerPublicKey='" + nextOwnerPublicKey + '\'' +
                ", transactionHash='" + transactionHash + '\'' +
                ", currOwnerSign='" + currOwnerSign + '\'' +
                '}';
    }
}
