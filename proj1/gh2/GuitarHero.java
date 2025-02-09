package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final GuitarString[] strings = new GuitarString[keyboard.length()];
    private static final int lasting_time = 50000;
    private static final int[] time = new int[keyboard.length()];

    private static void play(GuitarString string) {
        string.pluck();
        for (int i = 0; i < 50000; i++) {
            StdAudio.play(string.sample());
            string.pluck();
        }
    }

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        for (int i = 0; i < keyboard.length(); i++) {
            double frequency = 440 * Math.pow(2, (double)(i - 24)/12);
            strings[i] = new GuitarString(frequency);
            time[i] = -1;
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index < 0)
                    continue;
                strings[index].pluck();
                time[index] = 0;
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < keyboard.length(); i++) {
                if (time[i] >= lasting_time) {
                    time[i] = -1;
                }
                else if (time[i] >= 0) {
                    sample += strings[i].sample();
                    time[i] += 1;
                }
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString string: strings)
                string.tic();
        }
    }
}

