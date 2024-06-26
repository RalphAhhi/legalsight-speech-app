package com.legalsight.speech.dto;

import java.util.List;

public record ResultSetResponse<R>(List<R> data, int page, int perPage, long totalElements) {
}