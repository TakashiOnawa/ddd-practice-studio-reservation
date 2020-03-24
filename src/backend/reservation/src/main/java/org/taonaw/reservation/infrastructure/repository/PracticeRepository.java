package org.taonaw.reservation.infrastructure.repository;

import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.taonaw.reservation.domain.model.practice.IPracticeRepository;
import org.taonaw.reservation.domain.model.practice.Practice;
import org.taonaw.reservation.domain.model.practice.PracticeType;

import java.util.Optional;

@Repository
public class PracticeRepository implements IPracticeRepository {
    @Override
    public Optional<Practice> findBy(@NonNull PracticeType practiceType) {
        return Optional.empty();
    }
}
