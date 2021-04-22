package com.jiadongrui.trans.domain;

import java.security.PublicKey;

/**
 * @program: bitcoin
 * @description: 用于计算Hash的Combine对象
 * @author: jiadongrui
 * @create: 2021-04-22 22:00
 **/
public class HashCombine {

    public HashCombine(Transaction preTransaction, PublicKey nextOwnerPublicKey) {
        this.preTransaction = preTransaction;
        this.nextOwnerPublicKey = nextOwnerPublicKey;
    }

    /**
     * 上一笔交易
     */
    private Transaction preTransaction;

    /**
     * 下一位所有者的公钥
     */
    private PublicKey nextOwnerPublicKey;

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

    @Override
    public String toString() {
        return "HashCombine{" +
                "preTransaction=" + preTransaction +
                ", nextOwnerPublicKey='" + nextOwnerPublicKey + '\'' +
                '}';
    }
}
