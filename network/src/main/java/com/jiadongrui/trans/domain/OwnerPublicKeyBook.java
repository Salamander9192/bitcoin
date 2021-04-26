package com.jiadongrui.trans.domain;

import com.jiadongrui.trans.Owner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class OwnerPublicKeyBook {

    private static final Logger logger = LoggerFactory.getLogger(OwnerPublicKeyBook.class);

    private static Map<String,PublicKey> book = new HashMap<>();

    public static void register(Owner owner){
        book.put(owner.getOwnerName(),owner.getPublicKey());
        logger.info("Owner :[{}] has been registered",owner.getOwnerName());
    }

    public static void register(String ownerName,PublicKey publicKey){
        book.put(ownerName,publicKey);
    }

    public static PublicKey queryOwnerPublicKey(String ownerName){
        return book.get(ownerName);
    }

}
