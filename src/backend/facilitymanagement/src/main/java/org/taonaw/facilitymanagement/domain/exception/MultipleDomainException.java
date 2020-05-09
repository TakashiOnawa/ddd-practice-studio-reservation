package org.taonaw.facilitymanagement.domain.exception;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class MultipleDomainException extends DomainException {
    private final Map<Class<? extends Reason>, Reason> reasons = new HashMap<>();

    protected MultipleDomainException(
            @NonNull String message,
            @NonNull Map<Class<? extends Reason>, Reason> reasons,
            boolean addReasonsMessage) {
        super(createMessage(message, reasons, addReasonsMessage));
        this.reasons.putAll(reasons);
    }

    private static String createMessage(
            String message,
            Map<Class<? extends Reason>, Reason> reasons,
            boolean addReasonsMessage) {
        if (!addReasonsMessage || reasons.size() == 0) {
            return message;
        }

        var sb = new StringBuilder(message);
        sb.append("Details:");
        sb.append(System.lineSeparator());
        for (var reason : reasons.values()) {
            sb.append(System.lineSeparator());
            sb.append(reason.getMessage());
        }
        return sb.toString();
    }

    public <T extends Reason> Optional<T> getReason(Class<T> type) {
        var reason = reasons.get(type);
        if (reason == null) {
            return Optional.empty();
        }
        return Optional.of(type.cast(reason));
    }

    public <T extends Reason> boolean isReasonExists(Class<T> type) {
        return reasons.containsKey(type);
    }

    protected static abstract class Reason {
        public abstract String getMessage();
    }

    protected static abstract class Builder {
        private final Map<Class<? extends Reason>, Reason> reasons = new HashMap<>();

        public MultipleDomainException build() {
            return createDomainException(reasons);
        }

        public void throwIfErrorExists() {
            if (reasons.size() > 0) {
                throw createDomainException(reasons);
            }
        }

        protected void putReason(@NonNull Reason reason) {
            reasons.put(reason.getClass(), reason);
        }

        protected abstract MultipleDomainException createDomainException(@NonNull Map<Class<? extends Reason>, Reason> reasons);
    }
}
