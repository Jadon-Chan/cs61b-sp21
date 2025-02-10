package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final GuitarString[] STRINGS = new GuitarString[KEYBOARD.length()];
    private static final int LASTING_TIME = 50000;
    private static final int[] TIME = new int[KEYBOARD.length()];

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        for (int i = 0; i < KEYBOARD.length(); i++) {
            double frequency = 440 * Math.pow(2, (double) (i - 24) / 12);
            STRINGS[i] = new GuitarString(frequency);
            TIME[i] = -1;
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index < 0) {
                    continue;
                }
                STRINGS[index].pluck();
                TIME[index] = 0;
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < KEYBOARD.length(); i++) {
                if (TIME[i] >= LASTING_TIME) {
                    TIME[i] = -1;
                }
                else if (TIME[i] >= 0) {
                    sample += STRINGS[i].sample();
                    TIME[i] += 1;
                }
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString string: STRINGS) {
                string.tic();
            }
        }
    }
}

