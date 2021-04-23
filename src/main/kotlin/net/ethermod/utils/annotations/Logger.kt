package net.ethermod.utils.annotations

import net.ethermod.Ether
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Logger {
    private val LOGGER: Logger = LogManager.getLogger(Ether.MODID)

    fun log(msg: Any){
        LOGGER.log(Level.INFO, msg)
    }
}