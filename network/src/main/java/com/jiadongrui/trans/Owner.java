package com.jiadongrui.trans;

import com.jiadongrui.trans.domain.HashCombine;
import com.jiadongrui.trans.domain.OwnerPublicKeyBook;
import com.jiadongrui.trans.domain.Transaction;
import com.jiadongrui.trans.util.CalUtil;
import com.jiadongrui.trans.util.RSAUtil;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @program: bitcoin
 * @description: 用户
 * @author: jiadongrui
 * @create: 2021-04-22 21:48
 **/
public class Owner {

    private PublicKey publicKey;

    private PrivateKey privateKey;

    public Owner() {
        try {
            // 生成密钥对
            KeyPair keyPair = RSAUtil.getKeyPair();
            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
            this.privateKey = RSAUtil.getPrivateKey(privateKey);
            this.publicKey = RSAUtil.getPublicKey(publicKey);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("RSAUtil调用get异常");
        }
    }

    /**
     * 将交易转移给下一个拥有者
     * @param currTransaction
     * @param nextOwner
     * @return
     */
    public Transaction transfer(Transaction currTransaction, String nextOwner){
        Transaction newTransaction = new Transaction();
        //查询公钥记录本，获取新Owner的公钥
        PublicKey nextOwnerPublicKey = OwnerPublicKeyBook.queryOwnerPublicKey(nextOwner);
        //设置新交易的所有者公钥
        newTransaction.setNextOwnerPublicKey(nextOwnerPublicKey);
        //设置新交易的上一任交易
        newTransaction.setPreTransaction(currTransaction);
        //设置新交易中的hash值
        HashCombine hashCombine = new HashCombine(currTransaction,nextOwnerPublicKey);
        String transHash = CalUtil.calHashCode(hashCombine);
        newTransaction.setTransactionHash(transHash);
        //设置新交易中的交易签名
        try {
            String sign = RSAUtil.sign(transHash, this.privateKey);
            newTransaction.setCurrOwnerSign(sign);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("RSAUtil调用sign异常");
        }
        return newTransaction;
    }

    /**
     * 验证older是否为当前交易的拥有者
     * @param currTransaction
     * @return
     */
    public boolean varify(Transaction currTransaction,String oldOwner){
        //查询公钥记录本，获取旧Owner的公钥
        PublicKey oldOwnerPublicKey = OwnerPublicKeyBook.queryOwnerPublicKey(oldOwner);
        try {
            boolean verify = RSAUtil.verify(currTransaction.getTransactionHash(), oldOwnerPublicKey,
                    currTransaction.getCurrOwnerSign());
            return verify;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("RSAUtil调用varify异常");
        }
        return false;
    }

}
