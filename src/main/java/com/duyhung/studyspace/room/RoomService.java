package com.duyhung.studyspace.room;

import com.duyhung.studyspace.common.exception.DuplicateResourceException;
import com.duyhung.studyspace.common.exception.ResourceNotFoundException;
import com.duyhung.studyspace.room.dto.RoomRequest;
import com.duyhung.studyspace.room.dto.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public List<RoomResponse> findAll() {
        return roomRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RoomResponse findById(Long id) {
        return toResponse(getRoomOrThrow(id));
    }

    @Transactional
    public RoomResponse create(RoomRequest request) {
        if (roomRepository.existsByName(request.name())) {
            throw new DuplicateResourceException("Tên phòng đã tồn tại: " + request.name());
        }
        Room room = new Room();
        apply(room, request);
        return toResponse(roomRepository.save(room));
    }

    @Transactional
    public RoomResponse update(Long id, RoomRequest request) {
        Room room = getRoomOrThrow(id);
        apply(room, request);
        return toResponse(roomRepository.save(room));
    }

    @Transactional
    public void delete(Long id) {
        roomRepository.delete(getRoomOrThrow(id));
    }

    private Room getRoomOrThrow(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy phòng id = " + id));
    }

    private void apply(Room room, RoomRequest r) {
        room.setName(r.name());
        room.setCapacity(r.capacity());
        room.setType(r.type());
        room.setPricePerHour(r.pricePerHour());
    }

    private RoomResponse toResponse(Room r) {
        return new RoomResponse(r.getId(), r.getName(), r.getCapacity(),
                r.getType(), r.getPricePerHour(), r.getStatus());
    }
}