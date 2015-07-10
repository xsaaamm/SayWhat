package tarsos;


/**
 * <p>
 * AudioProcessors are responsible for actual digital signal processing. The
 * interface is simple: a process method that works on an AudioEvent object. 
 * The AudioEvent contains a buffer with some floats and the same information in
 * raw bytes.
 * </p> 
 * <p>
 * AudioProcessors are meant to be chained e.g. execute an effect and
 * then play the sound. The chain of audio processor can be interrupted by returning
 * false in the process methods.
 * </p>
 * @author Joren Six
 */
public interface AudioProcessor {

	/**
	 * Process the audio event. Do the actual signal processing on an
	 * (optionally) overlapping buffer.
	 * 
	 * @param audioEvent
	 *            The audio event that contains audio data.
	 * @return False if the chain needs to stop here, true otherwise. This can
	 *         be used to implement e.g. a silence detector.
	 */
    boolean process(AudioEvent audioEvent);

    /**
     * Notify the AudioProcessor that no more data is available and processing
     * has finished. Can be used to deallocate resources or cleanup.
     */
    void processingFinished();
}
