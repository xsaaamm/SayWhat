package tarsos_pitch;
import java.io.IOException;

/**
 * Decouples the audio input stream 
 * @author Joren Six
 */
public interface TarsosDSPAudioInputStream {

	/**
	 * Skip a number of bytes before reading the remaining bytes.
	 * @param bytesToSkip The number of bytes to skip.
	 * @return The number of bytes skipped.
	 * @throws IOException If the underlying  if an input or output error occurs
	 * #see read 
	 */
	long skip(long bytesToSkip) throws IOException;

	 /**
     * Reads up to a specified maximum number of bytes of data from the audio
     * stream, putting them into the given byte array.
     * <p>This method will always read an integral number of frames.
     * If <code>len</code> does not specify an integral number
     * of frames, a maximum of <code>len - (len % frameSize)
     * </code> bytes will be read.
     *
     * @param b the buffer into which the data is read
     * @param off the offset, from the beginning of array <code>b</code>, at which
     * the data will be written
     * @param len the maximum number of bytes to read
     * @return the total number of bytes read into the buffer, or -1 if there
     * is no more data because the end of the stream has been reached
     * @throws IOException if an input or output error occurs
     * @see #skip
     */
	int read(byte[] b, int off, int len) throws IOException ;

	 /**
     * Closes this audio input stream and releases any system resources associated
     * with the stream.
     * @throws IOException if an input or output error occurs
     */
    public void close() throws IOException;
    
	/**
	 * 
	 * @return The format of the underlying audio
	 */
	TarsosDSPAudioFormat getFormat();

	long getFrameLength();
}
