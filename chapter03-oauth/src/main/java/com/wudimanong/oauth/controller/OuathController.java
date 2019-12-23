package com.wudimanong.oauth.controller;

import com.wudimanong.oauth.entity.ResponseData;
import com.wudimanong.oauth.entity.enums.ResponseCode;
import com.wudimanong.oauth.feign.OauthResourceClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author joe
 */
@Controller
@SessionAttributes({"authorizationRequest"})
public class OuathController {

    @Autowired
    private OauthResourceClient oauthResourceClient;

    /**
     * 登出回调
     *
     * @param request
     * @param response
     */
    @RequestMapping("/backReferer")
    public void sendBack(HttpServletRequest request, HttpServletResponse response) {

        try {
            //sending back to client app
            String referer = request.getHeader("referer");
            if (referer != null) {
                int index = referer.indexOf("?");
                if (index != -1) {
                    referer = referer.substring(0, index);
                }
                response.sendRedirect(referer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主页，未从客户端跳转直接登陆会显示
     *
     * @param model
     * @return
     */
    @RequestMapping("/")
    public ModelAndView indexPage(HttpServletRequest request, Map<String, Object> model) {
        // 获取用户名
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

        String userName = ((UserDetails) securityContext
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        model.put("userName", userName);
        // 获取全部客户端应用
        ResponseData responseData = oauthResourceClient.getAllClient();
        if (ResponseCode.SUCCESS.getCode().equals(responseData.getCode()) && responseData.getData() != null) {
            model.put("client", responseData.getData());
        } else {
            model.put("client", new ArrayList<>());
        }
        return new ModelAndView("index", model);
    }
}
