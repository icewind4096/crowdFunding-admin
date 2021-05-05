package com.windvalley.crowd.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author icewind4096
 * @since 2021-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TAdmin对象", description="")
public class TAdmin implements Serializable {

    private static final long serialVersionUID=1L;

    private String loginAcct;

    @TableField("user_openId")
    private String userOpenid;

    private String userPhone;

    private String userPswd;

    private String userName;

    private String email;

    private String createTime;


}
