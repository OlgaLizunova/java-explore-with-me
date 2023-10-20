package ru.practicum.main.utils;

import java.util.Optional;

public enum RequestState {
        PENDING,
        CONFIRMED,
        REJECTED,
        CANCELED;

        public static Optional<RequestState> from(String stringState) {
                for (RequestState state : values()) {
                        if (state.name().equalsIgnoreCase(stringState)) {
                                return Optional.of(state);
                        }
                }
                return Optional.empty();
        }
}
