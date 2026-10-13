package com.duyhung.studyspace.room.dto;

import com.duyhung.studyspace.room.RoomType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RoomRequest(
        @NotBlank(message = "Tên phòng không được để trống")
        String name,

        @NotNull(message = "Sức chứa là bắt buộc")
        @Min(value = 1, message = "Sức chứa phải lớn hơn 0")
        Integer capacity,

        @NotNull(message = "Loại phòng là bắt buộc")
        RoomType type,

        @NotNull(message = "Giá là bắt buộc")
        @DecimalMin(value = "0.0", message = "Giá không được âm")
        BigDecimal pricePerHour
) {}