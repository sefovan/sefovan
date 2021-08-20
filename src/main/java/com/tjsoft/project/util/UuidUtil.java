package com.tjsoft.project.util;

import java.util.UUID;

/**
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.util
 * @date 2021/8/13 16:40
 * @Copyright ©
 */
public class UuidUtil {

    /**
     * 获取32位uuid
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
