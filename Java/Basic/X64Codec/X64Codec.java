package co.nworks.codec.misc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.jcodec.api.awt.SequenceEncoder;

public class X64Codec {
	
	
	public static void main(String args[]) {
        List<String> mFiles = new ArrayList<String>();
        File mDirectory;
        String folderPath = "E:\\screenshots";
        mDirectory = new File(folderPath);
        // Get the files in the directory
        System.out.println("|*|*| Creation of video started. |*|*|");
        File[] files = mDirectory.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                mFiles.add(f.getAbsolutePath());
            }
        }

        File sddir = new File("E:\\video"+"/");
        if (!sddir.exists()) {
            sddir.mkdirs();
        }
        File file = new File("E:\\video"+"/" + "vid_"+System.currentTimeMillis()+".mp4");
        SequenceEncoder encoder;
        try {
            encoder = new SequenceEncoder(file);
            long starting = System.nanoTime();
            for (int i = 0; i < mFiles.size(); i++) {
                System.out.println("Inside Image "+ "i = "+i+"/"+mFiles.size());
                long readImageStart = System.nanoTime();
                BufferedImage img = ImageIO.read(new File(mFiles.get(i)));
                long readImageEnd = System.nanoTime();
                System.out.println("Reading Image from disk took "+TimeUnit.NANOSECONDS.toMillis(readImageEnd - readImageStart)+" ms");
                
                for(int j=0;j<12;j++){
                    System.out.println("Inside Image-Frame "+ "j = "+j);
                    long start = System.nanoTime();
                    encoder.encodeImage(img);
                    long end = System.nanoTime();
                    System.out.println("Encoding Frame took "+TimeUnit.NANOSECONDS.toMillis(end - start)+" ms");
                } 
            }
            long ending = System.nanoTime();
            System.out.println("Encoding took total of "+TimeUnit.NANOSECONDS.toMillis(ending - starting)+" ms");
            encoder.finish();
            //deleteAllScreenshots();
            System.out.println("|*|*| Video created successfully. |*|*|");
        } catch (Exception e) {
            System.out.println("|*|*| Error Creating Video - "+e.getMessage());
            e.printStackTrace();
        }
    }
}
