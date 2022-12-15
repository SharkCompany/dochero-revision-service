package com.dochero.documentrevisionservice.dto.response;

public class DocumentRevisionResponse extends BaseResponse{
    public DocumentRevisionResponse(Object data, String message, boolean error, Integer errorCode) {
        super(data, message, error, errorCode);
    }

    public DocumentRevisionResponse(String message, Integer errorCode) {
        super(message, errorCode);
    }

    public DocumentRevisionResponse(Object data, String message) {
        super(data, message);
    }

    public DocumentRevisionResponse(Object data, String message, Integer errorCode) {
        super(data, message, errorCode);
    }
}
