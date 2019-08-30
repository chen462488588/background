package com.sonoscape.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;

//@WebFilter(filterName = "PageFilter", urlPatterns = {"/*"})
public class PageFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("filter............");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("login")) {
            chain.doFilter(request, response);
        } else {
            String accessToken = req.getParameter("accessToken");
            Key key = new SecretKeySpec("mysecret".getBytes(), SignatureAlgorithm.HS512.getJcaName());

            try {
                Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken);
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("false");
            }
            chain.doFilter(request, response);
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
