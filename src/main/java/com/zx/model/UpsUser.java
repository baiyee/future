package com.zx.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户列
 *
 * @author theshy
 * @date 2022年12月08日 00:48
 */
@Data
@Accessors(chain = true)
public class UpsUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;
}
