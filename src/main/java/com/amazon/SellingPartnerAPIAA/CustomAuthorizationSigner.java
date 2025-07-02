package com.amazon.SellingPartnerAPIAA;

import okhttp3.Request;

/**
 * Custom Authorization Signer
 */
public class CustomAuthorizationSigner implements AuthorizationSigner {



    private final String accessToken;

    public CustomAuthorizationSigner(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Signs a Request with an LWA Access Token
     *
     * @param originalRequest Request to sign (treated as immutable)
     * @return Copy of originalRequest with LWA signature
     */
    public Request sign(Request originalRequest) {

        return originalRequest
                .newBuilder()
                .addHeader(SIGNED_ACCESS_TOKEN_HEADER_NAME, accessToken)
                .build();
    }
}
