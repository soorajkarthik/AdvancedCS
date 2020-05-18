import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GuitarVisualizer {

    static boolean hasClosed = false;

    public static void main(String[] args) {

        final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        final long startTime = System.currentTimeMillis();
        GuitarString[] strings = new GuitarString[KEYBOARD.length()];
        ArrayList<Float> samples = new ArrayList<>();
        Coordinate coordinate = new Coordinate(0, 0.5);
        StdDraw.setPenRadius(0.0025);

        for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(400 * Math.pow(1.05956, (i - 24)));
        }

        //adds thread to run when program is closed
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {

                //breaks loop that's playing audio to stop more samples from being added to the float ArrayList
                stopLoop();

                Scanner scan = new Scanner(System.in);
                System.out.print("Save as: ");
                String fileName = scan.next();

                try {

                    //essentially shifts all decimal places 16 slots to the right in base 2
                    //casts to int so bitshift is possible
                    //shifts bits 8 to the right to get the "first part" of the initial float value
                    //then adds the "second part"
                    byte[] byteArray = new byte[samples.size() * 2];
                    int index = 0;
                    for (float f : samples) {
                        int bits = Math.round(f * 32767);
                        byteArray[index++] = (byte) (bits >> 8);
                        byteArray[index++] = (byte) (bits);
                    }

                    //ByteArrayInputStream passes 1 value at a time from the byte array used to initialize it
                    InputStream input = new ByteArrayInputStream(byteArray);


                    //sample rate is the same sample rate as used in GuitarString class
                    //even though samples are floats, sample size in bits is 16 since we're only storing 2 bytes of the float
                    //single channel since we're only storing 1 float value per sample
                    //I have no clue what bigEndian means but all I get is static noise if it's false, so I have sent bigEndian to true
                    AudioFormat format = new AudioFormat(44100f, 16, 1, true, true);

                    //creates stream that outputs each byte value with the correct format
                    AudioInputStream stream = new AudioInputStream(input, format, byteArray.length);

                    //file that is written to
                    File file = new File(fileName + ".wav");

                    //writes WAV file using the stream of formatted values
                    AudioSystem.write(stream, AudioFileFormat.Type.WAVE, file);

                    System.out.println("Audio has been saved to " + fileName + ".wav");


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        while (!hasClosed) {

            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();
                if (KEYBOARD.contains("" + key)) {
                    strings[KEYBOARD.indexOf("" + key)].pluck();
                }

            }

            double sample = 0;

            for (GuitarString g : strings) {
                sample += g.sample();
                g.tic();

            }

            samples.add((float) sample);
            StdAudio.play(sample);

            if (strings[0].time() % 3 == 0) {

                Coordinate temp = new Coordinate((double) (strings[0].time() % 512) / 512, sample + 0.5);
                if (temp.x > coordinate.x) {
                    StdDraw.line(coordinate.x, coordinate.y, temp.x, temp.y);
                    coordinate = temp;
                } else {
                    StdDraw.line(coordinate.x, coordinate.y, 1, 0.5);
                    StdDraw.show(5);
                    StdDraw.clear();
                    coordinate = new Coordinate(0, 0.5);
                }
            }
        }
    }

    //has been made static so the thread can access it
    public static void stopLoop() {
        hasClosed = true;
    }
}





