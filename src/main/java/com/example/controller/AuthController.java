package com.example.controller;

import com.example.WebApiResponseObject;
import com.example.domain.User;
import com.example.request.LoginRequest;
import com.example.security.*;
import com.example.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ログイン・ログアウトを行うコントローラです.
 *
 * @author haruka.yamaneki
 */
@RestController
@RequestMapping("/auth")
//CrossOrigin対応(異なるサーバーからの呼び出しを許可)
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtBlacklistService jwtBlacklistService;

    /**
     * ログインを行います.
     * @param request
     * @param result
     * @param model
     * @param response
     * @return webApiResponseObject
     */
    @NonAuthorize // 認可しない
    @PostMapping("/login")
    public ResponseEntity<WebApiResponseObject> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        WebApiResponseObject webApiResponseObject = new WebApiResponseObject();
        Map<String, Object> responseMap = new HashMap<>();

        User user = authService.login(request.getEmail(), request.getPassword());

        if (user == null) {
            webApiResponseObject.setStatus("error");
            webApiResponseObject.setMessage("メールアドレス、またはパスワードが間違っています。");
            webApiResponseObject.setErrorCode("E-01");
            System.out.println("WebApiResponseMessage:" + webApiResponseObject);
            return new ResponseEntity<>(webApiResponseObject, HttpStatus.UNAUTHORIZED);
        }
        // 成功情報をレスポンス
        webApiResponseObject.setStatus("success");
        webApiResponseObject.setMessage("OK.");
        webApiResponseObject.setErrorCode("E-00");
        responseMap.put("user", user);
        webApiResponseObject.setResponseMap(responseMap);

        // 認証トークンを発行してレスポンスに詰めます
        createAndResponseAccessToken(user, response);

        return new ResponseEntity<>(webApiResponseObject, HttpStatus.OK);
    }

    /**
     * ログアウトをします.
     *
     * <pre>
     * curl -X POST -H "Content-Type: application/json" "http://localhost:8080/ecsite-api/user/logout"
     * </pre>
     *
     * @return WebAPIのレスポンス情報
     */
    @Authorize // 認可する
    @PostMapping("/signout")
    public WebApiResponseObject logout(HttpServletRequest request) {
        // Authorizationの値を取得
        String authorization = request.getHeader("Authorization");

        // JWTトークンを取得
        String accessToken = authorization.replace("Bearer ", "");

        // トークンの有効期限を取得
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
        Date expirationDate = claims.getExpiration();

        // トークンをブラックリストに追加
        jwtBlacklistService.addToBlacklist(accessToken, expirationDate);

        // 成功情報をレスポンス
        WebApiResponseObject webApiResponseObject = new WebApiResponseObject();
        webApiResponseObject.setStatus("success");
        webApiResponseObject.setMessage("OK.");
        webApiResponseObject.setErrorCode("E-00");
        System.out.println(webApiResponseObject);
        return webApiResponseObject;
    }

    /**
     * 認証トークンを発行してレスポンスに詰めます.
     *
     * @param user ログイン成功したユーザ情報
     *
     * @param response レスポンス情報
     */
    private void createAndResponseAccessToken(User user, HttpServletResponse response) {
        // 認証トークン=JWT（JSON Web Token）を発行
        JsonWebTokenUtil jsonWebTokenUtil = new JsonWebTokenUtil();
        String jsonWebToken = jsonWebTokenUtil.generateToken(user.getId().toString(), user.getName());
        System.out.println("jsonWebToken:" + jsonWebToken);

        // CrossOrigin対応しているWebAPIでカスタムレスポンスヘッダを指定する場合、
        // TypeScript側で取得するには以下の1行が必要
        response.addHeader("Access-Control-Expose-Headers", "access-token, user_id, username");
        // 認証トークン=JWT（JSON Web Token）を発行しレスポンスデータに含ませる
        response.addHeader("access-token", jsonWebToken);
        response.addHeader("user_id", user.getId().toString());
        response.addHeader("username", user.getName());
    }
}

