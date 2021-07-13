package co.com.mutantdna.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnicalExceptionEnum {
    TECHNICAL_REQUEST_ERROR("001","An error occurred while trying to read the request body"),
    TECHNICAL_SERVER_ERROR("002","Internal server error"),
    TECHNICAL_DB_ERROR("002","An error occurred while save on database"),
    BUSINESS_NOT_IS_A_MUTANT("003", "DNA does not correspond to a mutant"),
    NOT_ALLOWED_DNA_CHARS("004", "Not allowed DNA Chars");

    private final String code;
    private final String title;
}
