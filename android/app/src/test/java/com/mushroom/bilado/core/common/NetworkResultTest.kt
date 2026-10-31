package com.mushroom.bilado.core.common

import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkResultTest {
    @Test
    fun success_maps_to_content() {
        val result = NetworkResult.Success(data = "invoice-123")

        assertEquals(UiState.Success("invoice-123"), result.toUiState())
    }

    @Test
    fun failure_maps_to_error() {
        val result = NetworkResult.Failure(error = NetworkError.Timeout)

        assertEquals(UiState.Error(NetworkError.Timeout), result.toUiState())
    }

    @Test
    fun success_map_transforms_payload_without_changing_operation_state() {
        val result = NetworkResult.Success(data = 42)

        assertEquals(NetworkResult.Success("42"), result.map(Int::toString))
    }
}
