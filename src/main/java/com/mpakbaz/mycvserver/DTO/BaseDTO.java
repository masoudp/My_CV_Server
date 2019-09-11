package com.mpakbaz.mycvserver.DTO;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseDTO implements Serializable {

    protected long id;

    protected long created;

    public BaseDTO(int id) {
        this.id = id;
    }

    public BaseDTO(int id, Long created) {
        this.id = id;
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof BaseDTO))
            return false;
        BaseDTO baseDTO = (BaseDTO) o;
        return id == baseDTO.id;
    }
}
