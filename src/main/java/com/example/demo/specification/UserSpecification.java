package com.example.demo.specification;

import com.example.demo.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.*;

public class UserSpecification {

    private UserSpecification() {
    }

    public static Specification<User> filter(
            String username, String password, Integer from, Integer to
    ) {

        return (root, criteriaQuery, criteriaBuilder) -> {
            // order
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

            // tim kiem user tieu nhieu tien nhat trong thang -> subquery() calc

            // filter -> tập hợp list điều kiện để truy vấn
            Collection<Predicate> predicates = new ArrayList<>();

            // username = ?1
            if ( StringUtils.isNotBlank(username) ) {
                predicates.add(criteriaBuilder.equal(root.get("username"), username));
            }

            // nếu chuyền tìm gần đúng cái name dc truyền lên
            // lower(a.name) like lower('%:key%') -> key = null ntn
            // tuoi thuoc khoang [from, to] - age deu null
            // agefrom <= a.age && a.age <= ageTo

            // a.age is null or a.age >= from
            if (Objects.nonNull(from)) {
                predicates.add(
                        criteriaBuilder.or(
                                criteriaBuilder.isNull(root.get("age")),
                                criteriaBuilder.greaterThanOrEqualTo(root.get("age"), from)
                        )
                );
            }

            if (Objects.nonNull(to)) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"), to));
            }

            if ( StringUtils.isNotBlank(password) ) {
                predicates.add(criteriaBuilder.equal(root.get("password"), password));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
