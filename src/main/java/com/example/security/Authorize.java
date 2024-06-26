package com.example.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 認可を行うコントローラのメソッドにつけるアノテーション.<br>
 *
 * クラスとメソッドにつけることができます。<br>
 *
 * 参考サイト：https://b1san-blog.com/post/spring/spring-auth/
 *
 * @author igamasayuki
 *
 */
//@Authorize
@Target({ElementType.TYPE, ElementType.METHOD}) // クラスとメソッドにつけることができる
@Retention(RetentionPolicy.RUNTIME)// 実行時に読み込まれる
public @interface Authorize {
}