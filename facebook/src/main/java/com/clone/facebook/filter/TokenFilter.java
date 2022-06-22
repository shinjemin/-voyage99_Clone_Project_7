package com.clone.facebook.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.clone.facebook.dto.UserTokenRespDto;
import com.clone.facebook.models.TokenDecode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component // 클래스에서 빈을 직접 등록 가능
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        UserTokenRespDto tokenRespDto = null;
        String url = httpRequest.getRequestURI();

        // 로그인, 회원가입은 jwt 토큰 인증이 필요 없음
        if(url.startsWith("/comment") || url.startsWith("/") || url.startsWith("/api")) {
            String authorization = httpRequest.getHeader("Authorization");

            // authorization이 null이거나 Bearer로 시작하지 않을 경우
            if(authorization == null || !authorization.startsWith("Bearer ")) {
                // 에러 처리
                tokenRespDto  = new UserTokenRespDto(false, "토큰이 유효하지 않습니다.");
            }

            if(tokenRespDto == null) {
                // 인증 성공 한 부분
                String token = authorization.substring(7); // substring 을 통해 Bearer 토큰의 앞부분을 잘라줌

                DecodedJWT jwt = null;

                try { // JWT SHA256사용, 시크릿 키 : 7ZWt7ZW0OTkgN+yhsCDtjpjsnbTsiqTrtoEg7YG066Gg7L2U65SpIO2Vre2VtDk5IDfsobAg7Y6Y 7J207Iqk67aBIO2BtOuhoOy9lOuUqQ== <- 시크릿 키는 커스텀이지만 한 번 정하면 다른 곳에서도 동일 해야함
                    Algorithm algorithm = Algorithm.HMAC256("7ZWt7ZW0OTkgN"); //use more secure key
                    JWTVerifier verifier = JWT.require(algorithm)
                            .withIssuer("gkdgo99") // 발급자
                            .build(); //Reusable verifier instance

                    jwt = verifier.verify(token);

                    TokenDecode tokenDecode = new TokenDecode(jwt);
                    httpRequest.setAttribute("decode", tokenDecode);
                } catch (JWTVerificationException exception){
                    tokenRespDto  = new UserTokenRespDto(false, exception.getMessage());
                }
            }
        }

        if(tokenRespDto != null) {
            httpResponse.setContentType("application/json");
            httpResponse.setCharacterEncoding("utf-8");
            httpResponse.setStatus(HttpServletResponse.SC_OK);

            OutputStream out = httpResponse.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(out, tokenRespDto);
            out.flush();
            return;
        }


        chain.doFilter(httpRequest, httpResponse);


    }

}
