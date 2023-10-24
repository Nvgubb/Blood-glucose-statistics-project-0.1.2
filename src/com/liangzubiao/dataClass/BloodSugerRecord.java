package com.liangzubiao.dataClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BloodSugerRecord {
    private float value;
    private String unit;
    private LocalDateTime insertTime;

    public BloodSugerRecord() {
    }

    public BloodSugerRecord(float value, String unit, LocalDateTime insertTime) {
        this.value = value;
        this.unit = unit;
        this.insertTime = insertTime;
    }


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDateTime getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(LocalDateTime insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        String formattedDateTime = insertTime.format(formatter);
        return "记录：{" + "血糖记录：" + value +
                  unit  +
                ", 记录时间：" + formattedDateTime +
                '}';
    }
}
