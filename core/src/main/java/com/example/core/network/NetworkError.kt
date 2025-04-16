package com.example.core.network

public sealed class NetworkError(override val message: String) : Throwable(message = message) {

    public class ServerError(requestName: String, message: String) :
        NetworkError("Error communicating with the server for requestName: $requestName, message: $message")

    public class NoData(requestName: String) :
        NetworkError("Empty response body for requestName: $requestName")

    public class BadCode(requestName: String, code: Int) :
        NetworkError("Server response with code: $code for requestName: $requestName")

    public class NoInternet : NetworkError("No internet connection")

    public companion object {
        public const val FAILED_INTERNET_CONNECTION_CODE: Int = -1
        public const val SUCCESS_CODE: Int = 200
        public const val BAD_REQUEST_CODE: Int = 400
        public const val NOT_FOUND_CODE: Int = 404
    }
}