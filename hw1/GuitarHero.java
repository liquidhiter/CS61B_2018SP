import synthesizer.GuitarString;

public class GuitarHero {

    private final static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        int numOfKeys = keyboard.length();
        GuitarString[] soundData = new GuitarString[numOfKeys];

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int idxOfKey = keyboard.indexOf(key);
                if (idxOfKey > -1) {
                    double freq = 440.0 * Math.pow(2.0, (idxOfKey - 24.0) / 12.0);
                    GuitarString newSound = new synthesizer.GuitarString(freq);
                    newSound.pluck();
                    soundData[idxOfKey] = newSound;
                }
            }

            /* Compute the super-position of sample */
            double sample = 0.0;
            for (GuitarString sound : soundData) {
                if (sound != null) {
                    sample += sound.sample();
                }
            }

            /* Play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString sound : soundData) {
                if (sound != null) {
                    sound.tic();
                }
            }
        }
    }

}
