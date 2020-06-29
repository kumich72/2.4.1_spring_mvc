package web.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        List<Role> roles = (List<Role>) authentication.getAuthorities();
        String j_username = httpServletRequest.getParameter("j_username");
        String j_password = httpServletRequest.getParameter("j_password");

        HttpSession session = httpServletRequest.getSession();

        session.setAttribute("j_username", j_username);
        session.setAttribute("j_password", j_password);

        boolean isHaveAdmin = roles.stream().anyMatch(e -> e.getName().equals("ADMIN"));
        if(isHaveAdmin){
            httpServletResponse.sendRedirect("/users");
        }else{
            boolean isHaveUser = roles.stream().anyMatch(e -> e.getName().equals("USER"));
            if(isHaveUser) {
                User user = (User) session.getAttribute("user");
                session.setAttribute("user", user);

                httpServletResponse.sendRedirect("/user");
            }
        }
    }
}