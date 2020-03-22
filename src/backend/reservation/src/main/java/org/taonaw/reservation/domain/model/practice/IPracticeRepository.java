package org.taonaw.reservation.domain.model.practice;

import lombok.NonNull;

import java.util.Optional;

public interface IPracticeRepository {
    Optional<Practice> findBy(@NonNull PracticeType practiceType);
}
