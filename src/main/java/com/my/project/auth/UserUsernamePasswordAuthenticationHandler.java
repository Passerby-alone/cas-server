package com.my.project.auth;

import com.ezhiyang.cloud.commons.mybatis.query.Example;
import com.my.project.dao.UsersMapper;
import com.my.project.entity.UsersDO;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;

/**
 * @author jinguo_peng
 * @description TODO
 * @date 2020/3/8 下午8:03
 */
public class UserUsernamePasswordAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

    private Logger logger = LoggerFactory.getLogger(UserUsernamePasswordAuthenticationHandler.class);

    @Resource
    private UsersMapper usersMapper;

    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential usernamePasswordCredential) throws GeneralSecurityException, PreventedException {

        final String username = getPrincipalNameTransformer().transform(usernamePasswordCredential.getUsername());
        final String password = usernamePasswordCredential.getPassword();

        UsersDO users = null;
        try {
            Example example = new Example(UsersDO.class).where(new Example.WhereClause() {
                @Override
                public void build(Example example) {
                    example.or().andEqualTo(UsersDO.Column.userName, username)
                                .andEqualTo(UsersDO.Column.password, password);
                }
            });
            users = usersMapper.selectOneByExample(example);
            if (null == users) {
                throw new FailedLoginException("用户名不存在: " + username);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new FailedLoginException(e.getMessage());
        }
        SimplePrincipal principal = new SimplePrincipal(users.getUserName());
        return createHandlerResult(usernamePasswordCredential, principal, null);
    }
}
