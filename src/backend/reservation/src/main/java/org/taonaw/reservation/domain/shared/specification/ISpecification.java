package org.taonaw.reservation.domain.shared.specification;

public interface ISpecification<T> {
    boolean isSatisfiedBy(T t);
}
