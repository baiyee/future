package com.zx.model;

import lombok.Data;

/**
 * 用户列
 *
 * @author theshy
 * @date 2022年12月08日 00:48
 */
@Data
public class UpsUser {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;
}
