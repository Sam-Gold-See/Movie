package com.samgoldsee.movie.result;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

    private long total;

    private List<T> records;
}
