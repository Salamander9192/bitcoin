package com.jiadongrui.trans.server;

import com.jiadongrui.trans.domain.Block;
import com.jiadongrui.trans.util.CalUtil;
import com.jiadongrui.trans.util.RSAUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @program: bitcoin
 * @description: 时间戳服务器
 * @author: jiadongrui
 * @create: 2021-05-06 21:07
 **/
public class TimestampServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampServer.class);

    private String timeStampServerName;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    public TimestampServer(String timeStampServerName) {
        try {
            this.timeStampServerName = timeStampServerName;
            // 生成密钥对
            KeyPair keyPair = RSAUtil.getKeyPair();
            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
            this.privateKey = RSAUtil.getPrivateKey(privateKey);
            this.publicKey = RSAUtil.getPublicKey(publicKey);
            LOGGER.info("create tss: [{}]",this);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("RSAUtil error");
        }
    }

    /**
     * 时间戳服务器，通过工作量证明制作区块
     * @param block
     * @param difficulty
     * @return
     */
    public String doHashSignWithWorkProve(Block block,int difficulty){
        //TODO
        return "";
    }




}
