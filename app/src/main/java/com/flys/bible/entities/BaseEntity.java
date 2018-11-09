package com.flys.bible.entities;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by User on 19/09/2018.
 */

public class BaseEntity implements Serializable {

    @DatabaseField(generatedId = true)
    private Long id;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
