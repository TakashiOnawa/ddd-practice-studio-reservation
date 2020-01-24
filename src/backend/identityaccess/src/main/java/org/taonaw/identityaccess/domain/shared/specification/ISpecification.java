package org.taonaw.identityaccess.domain.shared.specification;

public interface ISpecification<T> {
    boolean isSatisfiedBy(T t);
}
