package com.example.demo.mapper;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Mapper<T> {

    default <Q> List<Q> toList(List<T> ts, Function<T, Q> mapper) {
        return ts.stream().map(mapper).collect(Collectors.toList());
    }

    default <Q> Set<Q> toSet(List<T> ts, Function<T, Q> mapper) {
        return ts.stream().map(mapper).collect(Collectors.toSet());
    }
}

