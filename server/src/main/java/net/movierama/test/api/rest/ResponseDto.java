package net.movierama.test.api.rest;

public class ResponseDto {

    private long code;

    private String entity;

    public ResponseDto() {
    }

    public ResponseDto(long code, String entity) {
        this.code = code;
        this.entity = entity;
    }

    public long getCode() {
        return code;
    }

    public String getEntity() {
        return entity;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
