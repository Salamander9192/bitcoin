package com.jiadongrui.trans.domain;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class OwnerPublicKeyBook {

    private static Map<String,PublicKey> book = new HashMap<>();

    public static void register(String ownerName,PublicKey publicKey){
        book.put(ownerName,publicKey);
    }

    public static PublicKey queryOwnerPublicKey(String ownerName){
        return book.get(ownerName);
    }

}
