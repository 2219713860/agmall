package com.zhangxu.agmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名;用户名
     */
    private String username;

    /**
     * 密码;密码
     */
    private String password;

    /**
     * 昵称;昵称
     */
    private String nickname;

    /**
     * 真实姓名;真实姓名
     */
    private String realname;

    /**
     * 头像;头像
     */
    @Column(name = "user_img")
    private String userImg;

    /**
     * 手机号;手机号
     */
    @Column(name = "user_mobile")
    private String userMobile;

    /**
     * 邮箱地址;邮箱地址
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 性别;M(男) or F(女)
     */
    @Column(name = "user_sex")
    private String userSex;

    /**
     * 生日;生日
     */
    @Column(name = "user_birth")
    private Date userBirth;

    /**
     * 注册时间;创建时间
     */
    @Column(name = "user_regtime")
    private Date userRegtime;

    /**
     * 更新时间;更新时间
     */
    @Column(name = "user_modtime")
    private Date userModtime;

    /**
     * 是否是商户的管理员
     */
    @Column(name = "is_admin")
    private Integer isAdmin;
}