package _base.base.localDateTime.entity;

import java.time.LocalDateTime;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-31
 * @Desc:
 */
public class Entity {
    private Long id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String remark;


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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
