package lk.iit.nextora.module.event.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.iit.nextora.common.constants.ApiConstants;
import lk.iit.nextora.common.dto.ApiResponse;
import lk.iit.nextora.common.dto.PagedResponse;
import lk.iit.nextora.module.event.dto.request.CreateEventRequest;
import lk.iit.nextora.module.event.dto.request.UpdateEventRequest;
import lk.iit.nextora.module.event.dto.response.EventResponse;
import lk.iit.nextora.module.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controller for Event operations
 * Handles endpoints for viewing, creating, and managing events
 */
@RestController
@RequestMapping(ApiConstants.EVENTS)
@RequiredArgsConstructor
@Tag(name = "Events", description = "Event management endpoints")
public class EventController {

    private final EventService eventService;

    // ==================== Public Endpoints ====================

    @GetMapping
    @Operation(summary = "Get all published events", description = "View all published events")
    @PreAuthorize("hasAuthority('EVENT:READ')")
    public ApiResponse<PagedResponse<EventResponse>> getPublishedEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "startAt") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        PagedResponse<EventResponse> response = eventService.getPublishedEvents(pageable);
        return ApiResponse.success("Events retrieved successfully", response);
    }

    @GetMapping("/{eventId}")
    @Operation(summary = "Get event by ID", description = "View a specific event")
    @PreAuthorize("hasAuthority('EVENT:READ')")
    public ApiResponse<EventResponse> getEventById(@PathVariable Long eventId) {
        EventResponse response = eventService.getEventById(eventId);
        return ApiResponse.success("Event retrieved successfully", response);
    }

    @GetMapping(ApiConstants.EVENT_SEARCH)
    @Operation(summary = "Search events", description = "Search events by keyword")
    @PreAuthorize("hasAuthority('EVENT:READ')")
    public ApiResponse<PagedResponse<EventResponse>> searchEvents(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<EventResponse> response = eventService.searchEvents(keyword, pageable);
        return ApiResponse.success("Search completed successfully", response);
    }

    @GetMapping(ApiConstants.EVENT_UPCOMING)
    @Operation(summary = "Get upcoming events", description = "Get all upcoming events")
    @PreAuthorize("hasAuthority('EVENT:READ')")
    public ApiResponse<PagedResponse<EventResponse>> getUpcomingEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<EventResponse> response = eventService.getUpcomingEvents(pageable);
        return ApiResponse.success("Upcoming events retrieved successfully", response);
    }

    @GetMapping(ApiConstants.EVENT_ONGOING)
    @Operation(summary = "Get ongoing events", description = "Get all ongoing events")
    @PreAuthorize("hasAuthority('EVENT:READ')")
    public ApiResponse<PagedResponse<EventResponse>> getOngoingEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<EventResponse> response = eventService.getOngoingEvents(pageable);
        return ApiResponse.success("Ongoing events retrieved successfully", response);
    }

    @GetMapping(ApiConstants.EVENT_PAST)
    @Operation(summary = "Get past events", description = "Get all past events")
    @PreAuthorize("hasAuthority('EVENT:READ')")
    public ApiResponse<PagedResponse<EventResponse>> getPastEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<EventResponse> response = eventService.getPastEvents(pageable);
        return ApiResponse.success("Past events retrieved successfully", response);
    }

    @GetMapping("/search/date")
    @Operation(summary = "Search by date range", description = "Search events by date range")
    @PreAuthorize("hasAuthority('EVENT:READ')")
    public ApiResponse<PagedResponse<EventResponse>> searchByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<EventResponse> response = eventService.searchByDateRange(startDate, endDate, pageable);
        return ApiResponse.success("Search completed successfully", response);
    }

    // ==================== Creator Endpoints ====================

    @PostMapping
    @Operation(summary = "Create event", description = "Create a new event")
    @PreAuthorize("hasAuthority('EVENT:CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<EventResponse> createEvent(@Valid @RequestBody CreateEventRequest request) {
        EventResponse response = eventService.createEvent(request);
        return ApiResponse.success("Event created successfully", response);
    }

    @PutMapping("/{eventId}")
    @Operation(summary = "Update event", description = "Update own event (only DRAFT status)")
    @PreAuthorize("hasAuthority('EVENT:UPDATE')")
    public ApiResponse<EventResponse> updateEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody UpdateEventRequest request) {
        EventResponse response = eventService.updateEvent(eventId, request);
        return ApiResponse.success("Event updated successfully", response);
    }

    @PostMapping("/{eventId}" + ApiConstants.EVENT_PUBLISH)
    @Operation(summary = "Publish event", description = "Publish own event (DRAFT -> PUBLISHED)")
    @PreAuthorize("hasAuthority('EVENT:UPDATE')")
    public ApiResponse<EventResponse> publishEvent(@PathVariable Long eventId) {
        EventResponse response = eventService.publishEvent(eventId);
        return ApiResponse.success("Event published successfully", response);
    }

    @PostMapping("/{eventId}" + ApiConstants.EVENT_CANCEL)
    @Operation(summary = "Cancel event", description = "Cancel own event")
    @PreAuthorize("hasAuthority('EVENT:UPDATE')")
    public ApiResponse<EventResponse> cancelEvent(@PathVariable Long eventId) {
        EventResponse response = eventService.cancelEvent(eventId);
        return ApiResponse.success("Event cancelled successfully", response);
    }

    @GetMapping(ApiConstants.EVENT_MY)
    @Operation(summary = "Get my events", description = "Get events created by current user")
    @PreAuthorize("hasAuthority('EVENT:READ')")
    public ApiResponse<PagedResponse<EventResponse>> getMyEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<EventResponse> response = eventService.getMyEvents(pageable);
        return ApiResponse.success("My events retrieved successfully", response);
    }
}
