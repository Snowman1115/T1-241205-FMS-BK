package com.fms.fmsback.common.constants;

public interface ResultConstants {

    // 1xx Informational
    int CONTINUE = 100;
    int SWITCHING_PROTOCOLS = 101;
    int PROCESSING = 102;

    // 2xx Success
    int OK = 200;
    int CREATED = 201;
    int ACCEPTED = 202;
    int NON_AUTHORITATIVE_INFORMATION = 203;
    int NO_CONTENT = 204;
    int RESET_CONTENT = 205;
    int PARTIAL_CONTENT = 206;
    int MULTI_STATUS = 207;
    int ALREADY_REPORTED = 208;
    int IM_USED = 226;

    // 3xx Redirection
    int MULTIPLE_CHOICES = 300;
    int MOVED_PERMANENTLY = 301;
    int FOUND = 302;
    int SEE_OTHER = 303;
    int NOT_MODIFIED = 304;
    int USE_PROXY = 305;
    int TEMPORARY_REDIRECT = 307;
    int PERMANENT_REDIRECT = 308;

    // 4xx Client Errors
    int BAD_REQUEST = 400;
    int UNAUTHORIZED = 401;
    int PAYMENT_REQUIRED = 402;
    int FORBIDDEN = 403;
    int NOT_FOUND = 404;
    int METHOD_NOT_ALLOWED = 405;
    int NOT_ACCEPTABLE = 406;
    int PROXY_AUTHENTICATION_REQUIRED = 407;
    int REQUEST_TIMEOUT = 408;
    int CONFLICT = 409;
    int GONE = 410;
    int LENGTH_REQUIRED = 411;
    int PRECONDITION_FAILED = 412;
    int PAYLOAD_TOO_LARGE = 413;
    int URI_TOO_LONG = 414;
    int UNSUPPORTED_MEDIA_TYPE = 415;
    int RANGE_NOT_SATISFIABLE = 416;
    int EXPECTATION_FAILED = 417;
    int I_AM_A_TEAPOT = 418; // April Fools' joke RFC 2324
    int MISDIRECTED_REQUEST = 421;
    int UNPROCESSABLE_ENTITY = 422;
    int LOCKED = 423;
    int FAILED_DEPENDENCY = 424;
    int TOO_EARLY = 425;
    int UPGRADE_REQUIRED = 426;
    int PRECONDITION_REQUIRED = 428;
    int TOO_MANY_REQUESTS = 429;
    int REQUEST_HEADER_FIELDS_TOO_LARGE = 431;
    int UNAVAILABLE_FOR_LEGAL_REASONS = 451;

    // 5xx Server Errors
    int INTERNAL_SERVER_ERROR = 500;
    int NOT_IMPLEMENTED = 501;
    int BAD_GATEWAY = 502;
    int SERVICE_UNAVAILABLE = 503;
    int GATEWAY_TIMEOUT = 504;
    int HTTP_VERSION_NOT_SUPPORTED = 505;
    int VARIANT_ALSO_NEGOTIATES = 506;
    int INSUFFICIENT_STORAGE = 507;
    int LOOP_DETECTED = 508;
    int NOT_EXTENDED = 510;
    int NETWORK_AUTHENTICATION_REQUIRED = 511;


    // Custom Errors
    int NO_LOGIN = 600;
    int NO_AUTH = 700;

}
