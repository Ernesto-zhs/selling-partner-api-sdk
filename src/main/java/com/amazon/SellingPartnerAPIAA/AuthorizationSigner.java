package com.amazon.SellingPartnerAPIAA;

import okhttp3.Request;

public interface AuthorizationSigner {

    String SIGNED_ACCESS_TOKEN_HEADER_NAME = "x-amz-access-token";

    Request sign(Request originalRequest) throws LWAException;

}
