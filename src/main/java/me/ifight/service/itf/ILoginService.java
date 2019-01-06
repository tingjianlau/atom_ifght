package me.ifight.service.itf;

import me.ifight.model.LoginDetail;

public interface ILoginService {

    LoginDetail getLoginDetail(String username);

    String generateToken(ITokenDetail tokenDetail);

}
