package com.pingsoft.mark.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pingsoft.mark.Constant;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wkw
 * @since 2020-07-31
 */
public class LanguageType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creatTime;

    @TableLogic(value = Constant.NOT_DELETE, delval = Constant.DELETE)
    private String discard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }
    public String getDiscard() {
        return discard;
    }

    public void setDiscard(String discard) {
        this.discard = discard;
    }

    @Override
    public String toString() {
        return "LanguageType{" +
            "id=" + id +
            ", name=" + name +
            ", creatTime=" + creatTime +
            ", discard=" + discard +
        "}";
    }
}
