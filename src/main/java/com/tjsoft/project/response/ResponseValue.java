package com.tjsoft.project.response;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author sefo
 * @version V1.0
 * @Package com.fjm.project.util
 * @date 2021/7/27 11:11
 * @Copyright ©
 */
@Log4j2
@Data
@AllArgsConstructor
public class ResponseValue {

    /**
     * 返回编码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * Response输出Json格式
     *
     * @param response
     * @param data     返回数据
     */
    public static void responseJson(ServletResponse response, Object data) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(data));
            out.flush();
        } catch (Exception e) {
            log.error("Response输出Json异常：" + e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 返回信息
     *
     * @param code 返回编码
     * @param msg  返回消息
     * @param data 返回数据
     * @return
     */
    public static ResponseValue response(Integer code, String msg, Object data) {
        return new ResponseValue(code, msg, data);
    }

    /**
     * 返回成功
     *
     * @param data 返回数据
     * @return
     */
    public static ResponseValue success(Object data) {
        return ResponseValue.response(200, "成功", data);
    }

    /**
     * 返回失败
     *
     * @param data 返回数据
     * @return
     */
    public static ResponseValue fail(Object data) {
        return ResponseValue.response(500, "失败", data);
    }
}
