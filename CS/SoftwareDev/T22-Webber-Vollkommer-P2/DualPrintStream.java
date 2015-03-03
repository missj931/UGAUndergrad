//import neccessary packages
import java.io.*;
import java.util.*;
//extends the printstream class
public class DualPrintStream extends PrintStream {
        private final PrintStream second;
	
	//construcor
        public DualPrintStream(OutputStream main, PrintStream second) {
            super(main);
            this.second = second;
        }

        /**
         * Closes the main stream. 
         * The second stream is just flushed but <b>not</b> closed.
         * @see java.io.PrintStream#close()
         */
	//override's parent
        @Override
        public void close() {
            // just for documentation
            super.close();
        }

	//overrides parent
        @Override
        public void flush() {
            super.flush();
            second.flush();
        }

	//overrides parent
        @Override
        public void write(byte[] buf, int off, int len) {
            super.write(buf, off, len);
            second.write(buf, off, len);
        }
	
	//overrides parent
        @Override
        public void write(int b) {
            super.write(b);
            second.write(b);
        }

	//overrides parent
        @Override
        public void write(byte[] b) throws IOException {
            super.write(b);
            second.write(b);
        }
    }
