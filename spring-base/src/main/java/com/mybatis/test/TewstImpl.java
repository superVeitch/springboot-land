package com.mybatis.test;

import lombok.NonNull;

public class TewstImpl implements Tewst {
    @Override
    public boolean test(@NonNull long p){
        System.out.println(p);
        return true;
    }
}
