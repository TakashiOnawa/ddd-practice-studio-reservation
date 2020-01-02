package org.taonaw.reservation.domain.model.studios;

public interface IStudioRepository {
    Studio findBy(StudioId studioId);
}
