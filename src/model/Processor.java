package model;

/**
 * Interface dictating the interface of the processor that will read in commands.
 * @author Roman Bureacov
 * @version 2025-10
 */
public interface Processor {
    /**
     * Sends a command to the processor.
     * @param pOpcode the op code
     * @param pArg1 the first argument
     * @param pArg2 the second argument
     * @return return object of the op code, if any, otherwise null
     */
    Object command(Integer pOpcode, Integer pArg1, Integer pArg2);

    /**
     * Sends a command to the processor.
     * @param pInstruction the opcode as an array of integers
     * @return return object of the op code, if any, otherwise null
     */
    default Object command(Integer[] pInstruction) {
        return this.command(pInstruction[0], pInstruction[1], pInstruction[2]);
    }
}
