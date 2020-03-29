package org.taonaw.facilitymanagement.domain.shared.specification;

public interface ISpecification<T> {
    boolean isSatisfiedBy(T t);
}
