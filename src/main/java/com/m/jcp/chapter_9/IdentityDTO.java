package com.m.jcp.chapter_9;

/**
 * @author zhangtian1
 */
public class IdentityDTO {
    private String id;
    private String type;

    public IdentityDTO(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
