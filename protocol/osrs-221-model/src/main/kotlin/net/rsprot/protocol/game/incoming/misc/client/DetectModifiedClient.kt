package net.rsprot.protocol.game.incoming.misc.client

import net.rsprot.protocol.message.IncomingMessage

/**
 * Detect modified client is sent by the client right before a map load
 * if the client has been given a frame. For simple deobs, this is generally
 * not the case.
 * In OSRS, the code is consistently sent as '1,057,001,181'.
 */
public class DetectModifiedClient(
    public val code: Int,
) : IncomingMessage {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DetectModifiedClient

        return code == other.code
    }

    override fun hashCode(): Int {
        return code
    }

    override fun toString(): String {
        return "DetectModifiedClient(code=$code)"
    }
}
