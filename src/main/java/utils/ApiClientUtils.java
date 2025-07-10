package utils;

import com.amazon.SellingPartnerAPIAA.AuthorizationSigner;
import software.amazon.spapi.ApiClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class ApiClientUtils {

    private static final String DEFAULT_USER_AGENT = "amazon-selling-partner-api-sdk/1.1.2/Java";

    private static final Map<String, String> defaultHeaderMap = new HashMap<>();

    private ApiClientUtils() {
        defaultHeaderMap.put("User-Agent", DEFAULT_USER_AGENT);
    }

    public static void run(AuthorizationSigner signer, String bathPath, Runnable runnable) {
        run(signer, bathPath, () -> {
            runnable.run();
            return null;
        });
    }

    public static <R> R run(AuthorizationSigner signer, String bathPath, Supplier<R> supplier) {
        Objects.requireNonNull(signer, "signer cannot be null");
        Objects.requireNonNull(bathPath, "bathPath cannot be null");
        try {
            ApiClient.setContext(ApiClient.AUTHORIZATION_SIGNER, signer);
            ApiClient.setContext(ApiClient.BASE_PATH, bathPath);
            ApiClient.setContext(ApiClient.DEFAULT_HEADER_MAP, defaultHeaderMap);
            return supplier.get();
        } finally {
            ApiClient.clearContext();
        }
    }

}
