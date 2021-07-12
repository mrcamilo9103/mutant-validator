package co.com.mutantdna.model.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class Constants {
    public static final String RGX_ALLOWED_DNA_CHARS = "[ATCGatcg]+\\.?";
    public static final String YES = "Y";
    public static final String NO = "N";
}
