package me.ifight.service.impl;

import me.ifight.service.itf.ITokenDetail;

public class TokenDetailImpl implements ITokenDetail {
    private final String userName;

    public TokenDetailImpl(String userName){
        this.userName = userName;
    }

    @Override
    public String getUsername(){
        return this.userName;
    }
}
