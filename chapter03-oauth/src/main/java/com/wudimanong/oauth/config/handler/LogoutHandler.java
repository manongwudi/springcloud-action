package com.wudimanong.oauth.config.handler;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * @author jiangqiao
 */
public class LogoutHandler extends HttpStatusReturningLogoutSuccessHandler {

    private final HttpStatus httpStatusToReturn;

    public LogoutHandler() {
        this.httpStatusToReturn = HttpStatus.OK;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException,
            ServletException {
        response.setStatus(this.httpStatusToReturn.value());
        String origin = request.getHeader("Origin");
        response.setHeader("Vary", "Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
        response.getWriter().flush();
    }
}
