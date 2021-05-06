package com.jiadongrui.trans.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: bitcoin
 * @description: 区块
 * @author: jiadongrui
 * @create: 2021-05-06 21:09
 **/
public class Block {

    private static final Logger LOGGER = LoggerFactory.getLogger(Block.class);

    private int blockNum;

    private String previousBlockHash;

    private int randomNum;

    private List<Transaction> transactionList;

    public Block(int i){
        this.blockNum = i;
        this.transactionList = new LinkedList<>();
    }

    public Block append(Transaction transaction){
        this.transactionList.add(transaction);
        return this;
    }

    public Block appendList(List<Transaction> transactions){
        this.transactionList.addAll(transactions);
        return this;
    }

    public int getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(int blockNum) {
        this.blockNum = blockNum;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public int getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockNum=" + blockNum +
                ", previousBlockHash='" + previousBlockHash + '\'' +
                ", randomNum=" + randomNum +
                ", transactionList=" + transactionList +
                '}';
    }
}
