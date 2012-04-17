package edu.kubsu.fpm.managed.classes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 07.03.12
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public class ImgConverter {

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT){
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT){

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }
    public static byte[] changeProportion(File fOriginal, byte[] byteOriginal, int origWidth, int origHeigth, int WDTH, int HGT) throws IOException {
        BufferedImage biOriginal = null;
        if(byteOriginal!=null){
        biOriginal = ImageIO.read(new ByteArrayInputStream(byteOriginal));
        }
        if(fOriginal!=null){
        biOriginal = ImageIO.read(fOriginal);

        }
        int type = biOriginal.getType() == 0? BufferedImage.TYPE_INT_ARGB : biOriginal.getType();
        
        Dimension dim = getSmallerDim(origWidth,origHeigth,WDTH,HGT);
        BufferedImage biResult = resizeImageWithHint(biOriginal, type, (int)dim.getWidth(), (int)dim.getHeight());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(biResult, "png", baos);
        baos.flush();
        byte[] byteResult = baos.toByteArray();
        baos.close();
        return byteResult;

    }

    private static Dimension getSmallerDim(double origWidth, double origHeigth, double wdth, double hgt) {
        double proportion = Math.min((origWidth/wdth),(origHeigth/hgt));
        Dimension newDimension = new Dimension();
        newDimension.setSize(Math.round(origWidth/proportion),Math.round(origHeigth/proportion));
        return newDimension;
    }
    public static byte[] readFromFile(String filename) {

        BufferedInputStream bufferedInput = null;
        byte[] buffer = new byte[1024];

        try {

            //Construct the BufferedInputStream object
            bufferedInput = new BufferedInputStream(new FileInputStream(filename));

            int bytesRead = 0;

            //Keep reading from the file while there is any content
            //when the end of the stream has been reached, -1 is returned
            while ((bytesRead = bufferedInput.read(buffer)) != -1) {

                //Process the chunk of bytes read
                //in this case we just construct a String and print it out
                String chunk = new String(buffer, 0, bytesRead);
//                System.out.print(chunk);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the BufferedInputStream
            try {
                if (bufferedInput != null)
                    bufferedInput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return buffer;
    }
}
