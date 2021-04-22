package com.jiadongrui.trans.util;

import com.jiadongrui.trans.domain.HashCombine;

/**
 * @program: bitcoin
 * @description: 计算工具类
 * @author: jiadongrui
 * @create: 2021-04-22 21:51
 **/
public final class CalUtil {

    public static String calHashCode(HashCombine hashCombine){
        return String.valueOf(hashCombine.hashCode());
    }



}
