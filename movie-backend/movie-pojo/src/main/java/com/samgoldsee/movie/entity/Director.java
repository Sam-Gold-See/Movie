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
public class Director implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 导演id
    private Integer id;

    // 导演名
    private String name;

    // 头像资源链接
    private String avatar;

    // 导演简介
    private String description;
}
