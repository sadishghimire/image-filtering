/*The Image class
 * --> Read/Save/display Images
 * --> Extract pixel arrays, etc.
 * --> Based on BufferedArray
 ----------------------------
 Nischal Regmi
 Associate Professor, EEC
 */
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Scanner;
import java.math.RoundingMode.*;


public class Image {
    BufferedImage image;
    //*********************************************************************
    //************** Constructors *****************************************
    public Image(String filename) {
        try {
            this.image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public Image(BufferedImage img) {
        this.image = img;
    }

    public Image(int[][] A) {//gray-scale image from array
        image = new BufferedImage(A.length, A[0].length,
                BufferedImage.TYPE_BYTE_GRAY);
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                Color newColor = new Color(A[x][y], A[x][y], A[x][y]);
                image.setRGB(x, y, newColor.getRGB());
            }


    }

    //*******************************************************************
    //************** Basic IO and other functions ***********************

    BufferedImage getImage() {

        return this.image;
    }

    void saveToFile(String filename, String extension) {
        try {
            ImageIO.write(image, "jpg", new File(filename + "." + extension));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void saveToFile(int[][] f, String filename, String extension) {
        Image im = new Image(f);
        im.saveToFile(filename, extension);
    }

    void display(String title) {
        ImageIcon icon = new ImageIcon(this.image);
        JFrame frame = new JFrame(title);
        frame.setLayout(new FlowLayout());
        frame.setSize(this.image.getWidth(), this.image.getHeight());
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void display(int[][] f, String title) {
        //clip intensities to the range [0,255]
        for (int x = 0; x < f.length; x++)
            for (int y = 0; y < f[0].length; y++) {
                if (f[x][y] > 255)
                    f[x][y] = 255;
                if (f[x][y] < 0)
                    f[x][y] = 0;
            }

        Image img = new Image(f);

        img.display(title);
        //Image.saveToFile(f, "D:\\gray", "jpg");

    }

//    public static void display(double[][] f,String title){
//        //clip intensities to the range [0,255]
//        int[][] F = new int[f.length][f[0].length];
//        for(int x=0;x<f.length;x++)
//            for(int y=0;y<f[0].length;y++) {
//                F[x][y] = (int) Math.round(f[x][y]);
//                if(F[x][y]>255)
//                    F[x][y]=255;
//                if(F[x][y]<0)
//                    F[x][y]=0;
//            }
//        Image img = new Image(F);
//        img.display(title);
//
//    }


    public static void threshold() {

        //Image img = new Image("C:\\Users\\DELL\\Desktop\\IMG_20240213_094728.jpg");
        Image img = new Image("C:\\Users\\DELL\\Desktop\\fingerprint.jpg");
        int[][] f = img.getPixelArray();
        Image.display(f, "Original Image");
        for (int x = 0; x < f.length; x++)
            for (int y = 0; y < f[0].length; y++) {
                if (f[x][y] > 80)
                    f[x][y] = 255;
                else
                    f[x][y] = 0;
            }
        Image.display(f, "Threshold=80 image");
        Image.saveToFile(f, "D:\\threshold", "jpg");

    }
    public  static void log(){
        Image img = new Image("C:\\Users\\DELL\\Desktop\\fingerprint.jpg");
        int[][] f = img.getPixelArray();
        int totalintensity = 0;
        int meanintensityy = 0;
        int totalpixel = f.length * f[0].length;
        for (int x = 0; x < f.length; x++)
            for (int y = 0; y < f[0].length; y++) {
                totalintensity = totalintensity + f[x][y];
            }
        meanintensityy = totalintensity / totalpixel;
        System.out.println("meanintensity of image is" + meanintensityy);



        for (int x = 0; x < f.length; x++)
            for (int y = 0; y < f[0].length; y++) {

                if (f[x][y] > meanintensityy) {
                    f[x][y] = 255;
                }
                if (f[x][y] < 0) {
                    f[x][y] = 0;
                }

            }
        Image.display(f, "Mean intensity Image");
        Image.saveToFile(f, "D:\\meanintensity", "jpg");


    }

       public  static void histogram(){
     Image img = new Image("C:\\Users\\DELL\\Desktop\\IMG_20240213_094728.jpg");
        int[][] f = img.getPixelArray();
    int size=f.length*f[0].length;
    System.out.println("The size of image is"+ size);
    int[] h=new int[256];
        int nk,x=0,y=0;
        h[f[x][y]]=0;

    for( x=0;x<f.length;x++)
        for( y=0;y<f[0].length;y++) {
            System.out.println("f["+x+"]"+"["+y+"]="+f[x][y]);
        }
//            if(f[x][y]>255)
//                f[x][y]=255;
//            else if(f[x][y]<0)
//                f[x][y]=0;
//            else{
//                f[x][y]=f[x][y];
//            }
//}
    double[] p=new double[256];
    for(int i=0;i<255;i++){
        p[i]=(double) h[i]/256;
    }
    for(x=0;x<255;x++){
        System.out.println("x="+x+"n="+h[x]+"p="+p[x]);
    }
           double[] sk=new double[256];
            sk[0]=0;
           for(int i=0;i<255;i++){
               sk[i]=sk[i]+p[i];
           }
           for(int i=0;i<255;i++){
               sk[i]=Math.round(sk[i]*255);
               System.out.println("sk="+sk[i]);
           }
//           for(int m=0;m<255;m++){
//               for(int n=0;n<255;n++){
//                   f[m][n]=
//               }
//           }
       }

    public static void meanintensity() {
        Image img = new Image("C:\\Users\\DELL\\Desktop\\fingerprint.jpg");
        int[][] f = img.getPixelArray();
        int totalintensity = 0;
        int meanintensityy = 0;
        int totalpixel = f.length * f[0].length;
        for (int x = 0; x < f.length; x++)
            for (int y = 0; y < f[0].length; y++) {
                totalintensity = totalintensity + f[x][y];
            }
        meanintensityy = totalintensity / totalpixel;
        System.out.println("meanintensity of image is" + meanintensityy);



        for (int x = 0; x < f.length; x++)
            for (int y = 0; y < f[0].length; y++) {

                if (f[x][y] > meanintensityy) {
                    f[x][y] = 255;
                }
                if (f[x][y] < 0) {
                    f[x][y] = 0;
                }

            }
        Image.display(f, "Mean intensity Image");
        Image.saveToFile(f, "D:\\meanintensity", "jpg");

    }
public static void Convolution() {
    //Image img = new Image("C:\\Users\\DELL\\Desktop\\IMG_20240213_094728.jpg");
    Image img = new Image("C:\\Users\\DELL\\Desktop\\IMG_4994.jpg");
    int[][] f = img.getPixelArray();
    int max=0,min=0;


    int [ ] [ ] w = {{0,1,0},{1,-4,1}, {0 ,1 ,0}};
    int a = (w.length - 1) / 2;
    int b = (w[0].length - 1) / 2;
    int[][] f_padded = new int[2 * a + f.length][2 * b + f[0].length];
    //zero padding code
    for (int x = 0; x < f.length; x++)
        for (int y = 0; y < f[0].length; y++)
            f_padded[a + x][b + y] = f[x][y];

int[][] F=new int[f.length][f[0].length];

    //convolution code
    for (int x = 0; x < f.length; x++)
        for (int y = 0; y < f[0].length; y++) {
            for (int s = -a; s <= a; s++)
                for (int t = -b; t <= b; t++) {
                    int v = w[s + a][t + b];
                    F[x][y] = F[x][y] + v * f_padded[(a + x) - s][(b + y) - t];
                }
        }
    //find max and min value of intensity
    for (int x = 0; x < f.length; x++)
        for (int y = 0; y < f[0].length; y++){
            if(F[x][y]>max){
                max=F[x][y];
            }

        }
    System.out.println("max="+ max);
    //finding minimum
    for (int x = 0; x < f.length; x++)
        for (int y = 0; y < f[0].length; y++){
            if(F[x][y]<min){
                min=F[x][y];
            }

        }
    System.out.println("min="+ min);

    for (int x = 0; x <F.length ; x++)
        for (int y = 0; y <F[0].length ; y++){
            F[x][y]=F[x][y]+f[x][y];
        }

    //assigning minimum value to zero and max value to 255 and interpolation
    for (int x = 0; x <F.length ; x++)
        for (int y = 0; y <F[0].length ; y++){

               F[x][y]=(int) ((double)(F[x][y]-min)/(max-min)*255);

        }
    for (int x = 0; x <10 ; x++)
        for (int y = 0; y <10 ; y++){
            System.out.println( F[x][y]);
        }




    Image.display(F, "Convoluted image");

}
    public static void correlation() {
        //Image img = new Image("C:\\Users\\DELL\\Desktop\\IMG_20240213_094728.jpg");
        Image img = new Image("C:\\Users\\DELL\\Desktop\\IMG_4994.jpg");
        int[][] f = img.getPixelArray();
        int [ ] [ ] w = {{0,1,0},{1,-4,1}, {0 ,1 ,0}};
        int a = (w.length - 1) / 2;
        int b = (w[0].length - 1) / 2;
        int[][] f_padded = new int[2 * a + f.length][2 * b + f[0].length];
        //zero padding code
        for (int x = 0; x < f.length; x++)
            for (int y = 0; y < f[0].length; y++)
                f_padded[a + x][b + y] = f[x][y];

        int[][] F=new int[f.length][f[0].length];
        //convolution code
        for (int x = 0; x < f.length; x++)
            for (int y = 0; y < f[0].length; y++) {
                for (int s = -a; s <= a; s++)
                    for (int t = -b; t <= b; t++) {
                        int v = w[s + a][t + b];
                        F[x][y] = F[x][y] + v * f_padded[(a + x) + s][(b + y) + t];
                    }
            }
        Image.display(F, "Correlated image");
    }

    int[][] getPixelArray() {
        int[][] A = new int[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++)
            for (int y = 0; y < image.getHeight(); y++) {
                Color c = new Color(image.getRGB(x, y));
                A[x][y] = (int) (c.getRed() + c.getGreen() + c.getBlue()) / 3;
            }
        return A;
    }

    public static void main(String[] args){


        //threshold();
        //meanintensity();
        //correlation();
        Convolution();
       // histogram();

    }
}
