package com.jiadongrui.trans;

import com.jiadongrui.trans.domain.OwnerPublicKeyBook;
import com.jiadongrui.trans.domain.Transaction;
import com.jiadongrui.trans.util.RSAUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(Owner.class);

    private String ownerName;

    private PublicKey publicKey;

    private PrivateKey privateKey;


    public Owner(String ownerName) {
        try {
            this.ownerName = ownerName;
            // 生成密钥对
            KeyPair keyPair = RSAUtil.getKeyPair();
            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
            this.privateKey = RSAUtil.getPrivateKey(privateKey);
            this.publicKey = RSAUtil.getPublicKey(publicKey);
            logger.info("create owner: [{}]",this);
            OwnerPublicKeyBook.register(this);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("RSAUtil error");
        }
    }

    /**
     * 将交易转移给下一个拥有者
     * @param currTransaction
     * @param nextOwner
     * @return
     */
    public Transaction transfer(Transaction currTransaction, String nextOwner){
        //查询公钥记录本，获取新Owner的公钥
        PublicKey nextOwnerPublicKey = OwnerPublicKeyBook.queryOwnerPublicKey(nextOwner);
        Transaction newTransaction = new Transaction(currTransaction,nextOwnerPublicKey);
        //设置新交易中的交易签名
        try {
            String sign = RSAUtil.sign(newTransaction.getTransactionHash(), this.privateKey);
            newTransaction.setCurrOwnerSign(sign);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("RSAUtil error");
        }
        logger.info("transaction transferred : from [{}] to [{}]",currTransaction.getCurrOwnerSign(),newTransaction.getNextOwnerPublicKey());
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
            logger.info("owner[{}] varify transN for trans(N-1)=owner(N-1) : result = [{}]",this.getOwnerName(),verify);
            return verify;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("RSAUtil error");
        }
        return false;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerName='" + ownerName + '\'' +
                ", publicKey=" + publicKey +
                ", privateKey=" + privateKey +
                '}';
    }

    public static void main(String[] args) {
        logger.info("test");
    }
}
