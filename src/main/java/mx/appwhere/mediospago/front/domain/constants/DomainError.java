package mx.appwhere.mediospago.front.domain.constants;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Alejandro Martin
 * @version 1.0 - 2017/09/22
 */
public enum DomainError {
    //TODO CORREGIR ESTA CLASE

    /**
     * Generic Error
     */
    ERROR(700),
    /**
     * Resource can not be found
     */
    RESOURCE_NOT_FOUND(701),
    /**
     * Connection error
     */
    CONNECTION_ERROR(702),
    /**
     * Esb error
     */
    ESB_ERROR(703),
    /**
     * As error
     */
    AS_ERROR(704),

    BACKEND_ERROR(500),

    /**
     * This field is to mark the end of the enum.
     * NOTE: Do no use this error
     */
    SUPER_ERROR(99999);

    private Integer code;

    DomainError(Integer code) {
        this.code = code;
    }

    // setters & getters

    public Integer code() {
        return code;
    }

    /**
     * Return an {@link Optional  optional} of {@link DomainError} to corresponding {@code code}.
     *
     * @param code domain error code
     * @return domain error if it exits
     */
    public static Optional<DomainError> getDomainError(Integer code) {
        return Arrays.stream(DomainError.values())
                .filter(domainError -> domainError.code.equals(code)).findFirst();
    }
}
