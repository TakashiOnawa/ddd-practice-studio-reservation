package org.taonaw.reservation.domain.model.practicetypes;

import java.util.List;

public interface IPracticeTypeRepository {
    List<PracticeType> findAll();
    PracticeType findby(PracticeTypes practiceType);
}
