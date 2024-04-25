package com.cfg.idgen.entity;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共ID信息表
 * @TableName FICS_COMMON_ID
 */
@Data
public class BasCommonIdDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 最大ID
     */
    private Long maxId;

    /**
     * 段大小
     */
    private Integer step;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 版本号
     */
    private Long version;

    /**
     * id类型
     */
    private String idType;

    /**
     * 段标识：日期字符串
     */
    private String segFlag;

    /**
     * 创建时间
     */
    private Date crteTime;

    /**
     * 更新时间
     */
    private Date updtTime;


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BasCommonIdDO other = (BasCommonIdDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getMaxId() == null ? other.getMaxId() == null : this.getMaxId().equals(other.getMaxId()))
                && (this.getStep() == null ? other.getStep() == null : this.getStep().equals(other.getStep()))
                && (this.getBizType() == null ? other.getBizType() == null : this.getBizType().equals(other.getBizType()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
                && (this.getIdType() == null ? other.getIdType() == null : this.getIdType().equals(other.getIdType()))
                && (this.getSegFlag() == null ? other.getSegFlag() == null : this.getSegFlag().equals(other.getSegFlag()))
                && (this.getCrteTime() == null ? other.getCrteTime() == null : this.getCrteTime().equals(other.getCrteTime()))
                && (this.getUpdtTime() == null ? other.getUpdtTime() == null : this.getUpdtTime().equals(other.getUpdtTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMaxId() == null) ? 0 : getMaxId().hashCode());
        result = prime * result + ((getStep() == null) ? 0 : getStep().hashCode());
        result = prime * result + ((getBizType() == null) ? 0 : getBizType().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getIdType() == null) ? 0 : getIdType().hashCode());
        result = prime * result + ((getSegFlag() == null) ? 0 : getSegFlag().hashCode());
        result = prime * result + ((getCrteTime() == null) ? 0 : getCrteTime().hashCode());
        result = prime * result + ((getUpdtTime() == null) ? 0 : getUpdtTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", maxId=").append(maxId);
        sb.append(", step=").append(step);
        sb.append(", bizType=").append(bizType);
        sb.append(", version=").append(version);
        sb.append(", idType=").append(idType);
        sb.append(", segFlag=").append(segFlag);
        sb.append(", crteTime=").append(crteTime);
        sb.append(", updtTime=").append(updtTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}