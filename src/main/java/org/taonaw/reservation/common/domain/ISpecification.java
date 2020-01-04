package org.taonaw.reservation.common.domain;

public interface ISpecification<T> {
    boolean isSatisfiedBy(T t);
}
