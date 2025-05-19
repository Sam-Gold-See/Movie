package com.samgoldsee.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Actor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 演员id
    private Integer id;

    // 演员名
    private String name;

    // 头像资源链接
    private String avatar;

    // 演员简介
    private String description;
}
