package net.rsprot.protocol.game.incoming.misc.client

import net.rsprot.protocol.message.IncomingMessage

/**
 * Map build complete is sent when the client finishes building map after
 * a map reload. This packet is primarily used by the server for `p_loaddelay;`
 * procs, which delay current active script until the client has finished loading
 * the map, with a 10-game-cycle timeout.
 */
public data object MapBuildComplete : IncomingMessage
