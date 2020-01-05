package org.taonaw.common.domain.specification;

public interface ISpecification<T> {
    boolean isSatisfiedBy(T t);
}
