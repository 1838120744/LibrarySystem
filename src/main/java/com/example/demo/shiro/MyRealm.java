package com.example.demo.shiro;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import javax.persistence.Id;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.example.demo.dao.AdminDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Administrator;
import com.example.demo.entity.User;



/**
 * 自定义Realm
 *
 */
public class MyRealm extends AuthorizingRealm{
	
	@Resource
	private UserDao  userDao;
	@Resource
    private AdminDao adminDao;
	
	/**
	 *  #授权--验证url
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
		String role=(String) SecurityUtils.getSubject().getSession().getAttribute("role");
				//(String) SecurityUtils.getSubject().getPrincipal();
		
		//User user=userDao.findByName(name);
		//有了用户可以拿到，角色，  有角色，就有对应的菜单 list集合。  --- permissions
	    Logger log = LoggerFactory.getLogger(MyRealm.class);

		log.info(role);
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		//Set<String> roles=new HashSet<String>();
		//roles.add("管理员");
		
		//  遍历角色所有menu  
		info.addStringPermission("管理员");//添加权限   permissions
		//info.setRoles(roles);
		return info;
	}
	
	/**
	 * 权限认证--登录
	 */

	@Override //认证器，主体的认证过程通过Authenticator进行；
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
		LoginToken loginToken =(LoginToken)token;
        String loginType = loginToken.getLoginType();
        if("user".equals(loginType)) {
        	System.out.println("shiro_user");
        	Integer id=null;
        	try {
    			 id=Integer.valueOf((String) token.getPrincipal());//用户名  UsernamePasswordTokenr的第一个参数  name
			} catch (Exception e) {
				return null;				
			}
			User user=userDao.findById(id).orElse(null);
			if(user!=null){
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getName(),user.getPsw(),"xxx");
				SecurityUtils.getSubject().getSession().setAttribute("role",loginType);
				return authcInfo;
			}else{
				return null;				
			}
        }
        else if("admin".equals(loginType)) {
        	System.out.println("shiro_admin");
        	String name=(String)token.getPrincipal();//用户名  UsernamePasswordTokenr的第一个参数  name
			Administrator administrator=adminDao.findByName(name);
			if(administrator!=null){
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(administrator.getName(),administrator.getPsw(),"xxx");
				SecurityUtils.getSubject().getSession().setAttribute("role",loginType);
				return authcInfo;
			}else{
				return null;				
			}
        }
        else return null;
	}
}
