package io.beatmaps.common.schema

import io.beatmaps.common.api.ECharacteristic
import io.beatmaps.common.api.EDifficulty
import io.beatmaps.common.beatsaber.CorrectType
import io.beatmaps.common.beatsaber.NodePresent
import io.beatmaps.common.schema.SchemaCommon.validateFolder
import io.beatmaps.common.schema.SchemaCommon.violation4
import io.beatmaps.common.schema.SchemaCommon.violation4L
import org.junit.Test
import org.valiktor.constraints.In
import org.valiktor.constraints.NotNull
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class SchemaTest41 {
    @Test
    fun badtypes() {
        val ex = validateFolder("4_1/badtypes")
        assertNotNull(ex)

        assertContentEquals(
            listOf(
                violation4<CorrectType>("njsEvents"),
                violation4<CorrectType>("njsEventsData")
            ),
            ex.constraintViolations
        )
    }

    @Test
    fun basic() {
        val ex = validateFolder("4_1/basic")
        assertNull(ex)
    }

    @Test
    fun schema() {
        val ex = validateFolder("4_1/default")
        assertNull(ex)
    }

    @Test
    fun missing() {
        val ex = validateFolder("4_1/missing")
        assertNull(ex)
    }

    @Test
    fun `null`() {
        val ex = validateFolder("4_1/null")
        assertNotNull(ex)

        assertContentEquals(
            listOf(
                violation4<NotNull>("njsEvents"),
                violation4<NotNull>("njsEventsData")
            ),
            ex.constraintViolations
        )
    }

    @Test
    fun error() {
        val ex = validateFolder("4_1/error")
        assertNotNull(ex)

        assertContentEquals(
            listOf(
                violation4<CorrectType>("njsEvents[0].b(beat)"),
                violation4<CorrectType>("njsEvents[0].i(index)"),
                violation4<NotNull>("njsEvents[1].b(beat)"),
                violation4<NotNull>("njsEvents[1].i(index)"),
                violation4<CorrectType>("njsEvents[3]"),
                violation4<NotNull>("njsEvents[4]"),

                violation4<CorrectType>("njsEventsData[0].d(relativeNoteJumpSpeed)"),
                violation4<CorrectType>("njsEventsData[0].p(usePreviousValue)"),
                violation4<CorrectType>("njsEventsData[0].e(type)"),
                violation4<NotNull>("njsEventsData[1].d(relativeNoteJumpSpeed)"),
                violation4<NotNull>("njsEventsData[1].p(usePreviousValue)"),
                violation4<NotNull>("njsEventsData[1].e(type)"),
                violation4<CorrectType>("njsEventsData[3]"),
                violation4<NotNull>("njsEventsData[4]")
            ),
            ex.constraintViolations
        )
    }
}
