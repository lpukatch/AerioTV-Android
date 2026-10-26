package com.aeriotv.android.core.sync

import org.junit.Assert.assertEquals
import org.junit.Test

class DriveSyncResultsTest {
    @Test fun successfulPullDoesNotHideFailedPush() {
        assertEquals(
            1,
            countSyncFailures(
                pushed = mapOf(SyncCategory.WatchProgress to false),
                pulled = mapOf(SyncCategory.WatchProgress to true),
            ),
        )
    }

    @Test fun countsBothFailuresForTheSameCategory() {
        assertEquals(
            2,
            countSyncFailures(
                pushed = mapOf(SyncCategory.Favorites to false),
                pulled = mapOf(SyncCategory.Favorites to false),
            ),
        )
    }

    @Test fun successfulPushDoesNotHideFailedPull() {
        assertEquals(
            1,
            countSyncFailures(
                pushed = mapOf(SyncCategory.WatchProgress to true),
                pulled = mapOf(SyncCategory.WatchProgress to false),
            ),
        )
    }

    @Test fun initialPullCanRunWithoutPushResults() {
        assertEquals(
            1,
            countSyncFailures(
                pushed = emptyMap(),
                pulled = mapOf(SyncCategory.Playlists to true, SyncCategory.Preferences to false),
            ),
        )
    }

    @Test fun successfulOperationsHaveNoFailures() {
        assertEquals(
            0,
            countSyncFailures(
                pushed = mapOf(SyncCategory.Reminders to true),
                pulled = mapOf(SyncCategory.Reminders to true),
            ),
        )
        assertEquals(0, countSyncFailures(emptyMap(), emptyMap()))
    }
}
