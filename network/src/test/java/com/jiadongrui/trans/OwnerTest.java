package com.jiadongrui.trans;

import com.jiadongrui.trans.domain.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OwnerTest {

    private static final Logger logger = LoggerFactory.getLogger(OwnerTest.class);

    private Owner owner0;
    private Owner owner1;
    private Owner owner2;
    private Owner owner3;

    private Transaction transaction0;

    @Before
    public void init(){
        owner0 = new Owner("owner0");
        owner1 = new Owner("owner1");
        owner2 = new Owner("owner2");
        owner3 = new Owner("owner3");
        transaction0 = initTransaction0();
    }

    /**
     * 区块链世界的第一笔交易
     * @return
     */
    private Transaction initTransaction0(){
        return new Transaction(null,this.owner0.getPublicKey());
    }

    /**
     *
     */
    @Test
    public void test1(){
        //测试场景一:Trans0的拥有者Owner0，将交易转移给Owner1
        Transaction transaction1 = this.owner0.transfer(this.transaction0, this.owner1.getOwnerName());
        //测试场景二:Trans1的拥有者Owner1，将交易转移给Owner2
        Transaction transaction2 = this.owner1.transfer(transaction1,this.owner2.getOwnerName());
        //此时，owner2需要通过owner1的公钥，来验证trans2的签名，以确保trans1为owner1所有
        boolean varify = this.owner2.varify(transaction2, this.owner1.getOwnerName());

        Transaction transaction3 = this.owner2.transfer(transaction2,this.owner3.getOwnerName());
        //此时，owner3需要通过owner2的公钥，来验证trans3的签名，以确保trans2为owner2所有
        boolean varify2 = this.owner3.varify(transaction3, this.owner2.getOwnerName());
    }



}