package tarsos;

/**
 * An interface to handle detected pitch.
 * 
 * @author Joren Six
 */
public interface PitchDetectionHandler {
	/**
	 * Handle a detected pitch.
	 * @param pitchDetectionResult 
	 * @param audioEvent
	 * 
	 */
	void handlePitch(PitchDetectionResult pitchDetectionResult,AudioEvent audioEvent);
}
