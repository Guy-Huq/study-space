package com.duyhung.studyspace.room.dto;

import com.duyhung.studyspace.room.RoomStatus;
import com.duyhung.studyspace.room.RoomType;

import java.math.BigDecimal;

public record RoomResponse(
        Long id,
        String name,
        Integer capacity,
        RoomType type,
        BigDecimal pricePerHour,
        RoomStatus status
) {}