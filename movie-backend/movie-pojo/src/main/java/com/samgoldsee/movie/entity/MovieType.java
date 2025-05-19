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
public class MovieType implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 电影类型id
    private Integer id;

    // 电影类型名
    private String name;
}
