package cn.chenxun.common.shiro;

import cn.chenxun.common.consts.GlobalConst;
import cn.chenxun.common.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;

@Slf4j
public class ShiroMatcher extends HashedCredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Object salt = hasSalt(info);
        Object accountCredentials = this.getCredentials(info);
        String password = JwtUtil.getClaim(String.valueOf(token.getCredentials()), GlobalConst.PASSWD);
        String md5Passwd = new SimpleHash("MD5", password, salt, 4).toHex();
        return this.equals(md5Passwd, accountCredentials.toString());
    }

    protected Object hasSalt(AuthenticationInfo info){
        Object salt = null;
        if(info instanceof SaltedAuthenticationInfo){
            salt = ((SaltedAuthenticationInfo) info).getCredentialsSalt();
        }
        return salt;
    }

}