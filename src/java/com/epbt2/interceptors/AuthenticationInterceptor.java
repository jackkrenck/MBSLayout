/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epbt2.interceptors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.epbt2.common.model.User;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author ghani
 */
public class AuthenticationInterceptor implements Interceptor {
    //private static final long serialVersionUID = -5011962009065225959L;

    @Override
    public void destroy() {
        //release resources here
    }

    @Override
    public void init() {
        // create resources here
    }

    @Override
    public String intercept(ActionInvocation actionInvocation)
            throws Exception {

        Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();

        User user = (User) sessionAttributes.get("USER");

        if (user == null) {
            return Action.LOGIN;
        } else {
            Action action = (Action) actionInvocation.getAction();
            if (action instanceof UserAware) {
                ((UserAware) action).setUser(user);
            }
            return actionInvocation.invoke();
        }
    }

}
