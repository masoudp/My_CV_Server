package com.mpakbaz.mycvserver.common;

import com.mpakbaz.mycvserver.domain.enums.LOCALE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MLText implements Serializable {
    private static final long serialVersionUID = -6126670773296298961L;

    private LOCALE language;
    private String text;
}
