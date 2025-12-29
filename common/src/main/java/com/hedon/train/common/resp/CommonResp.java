package com.hedon.train.common.resp;

public class CommonResp<T> {
    private boolean success = true;
    private String message;
    private T content;

    public CommonResp() {
        this.success = true;
        this.message = "";
        this.content = null;
    }

    public CommonResp(T content) {
        this.success = true;
        this.message = "";
        this.content = content;
    }

    public CommonResp(boolean success, String message, T content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }

    public static <T> CommonResp<T> success(T content) {
        return new CommonResp<>(content);
    }

    public static CommonResp<Void> error(String msg) {
        return new CommonResp<Void>(false, msg, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
