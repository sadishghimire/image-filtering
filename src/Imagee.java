import java.lang.Math;


public class Imagee {

 int[][]  Convolve(int[][] f,int[][] w)
{
    int[][] F=new int[f.length][f[0].length];

    int a = (w.length - 1) / 2;
    int b = (w[0].length - 1) / 2;
    int[][] f_padded = new int[2 * a + f.length][2 * b + f[0].length];
    //zero padding code
    for (int x = 0; x < f.length; x++)
        for (int y = 0; y < f[0].length; y++)
            f_padded[a + x][b + y] = f[x][y];
    //convolution code
    for (int x = 0; x < f.length; x++)
        for (int y = 0; y < f[0].length; y++) {
            for (int s = -a; s <= a; s++)
                for (int t = -b; t <= b; t++) {
                    int v = w[s+a][t+b];
                    F[x][y] = F[x][y] + v * f_padded[(a + x) - s][(b + y) - t];
                }
        }
return F;
}
    public static int[][] rescale(int[][] F){
        int max=0,min=0;
        //max
        for (int x = 0; x < F.length; x++)
            for (int y = 0; y < F[0].length; y++){
                if(F[x][y]>max){
                    max=F[x][y];
                }
            }
        System.out.println("max="+ max);
        //finding minimum
        for (int x = 0; x < F.length; x++)
            for (int y = 0; y < F[0].length; y++){
                if(F[x][y]<min){
                    min=F[x][y];
                }
            }
        for (int x = 0; x <F.length ; x++)
            for (int y = 0; y <F[0].length ; y++){
                F[x][y]=(int) ((double)(F[x][y]-min)/(max-min)*255);
            }
        for (int x = 0; x <10 ; x++)
            for (int y = 0; y <10 ; y++){
                System.out.println( F[x][y]);
            }
        return F;
    }
int[][] enhanced(int[][] f,int[][] F){
    int[][] enhancedpixels=new int[f.length][f[0].length];

    for (int x = 0; x <f.length ; x++)
            for (int y = 0; y <f[0].length ; y++){
                enhancedpixels[x][y]=F[x][y]+f[x][y];
            }
    return enhancedpixels;
}
int[][] logtransform(int[][] f){
     int[][] loggedpixels=new int[f.length][f[0].length];
    for (int x = 0; x <f.length ; x++)
        for (int y = 0; y <f[0].length ; y++){
            loggedpixels[x][y]=(int)(150* Math.log(f[x][y]+1));
        }
    return loggedpixels;
}

//public static int[][] medianFilter(int[][] f){
//
//    int[][] F=new int[f.length][f[0].length];
//    int[][] mdfilter=new int[3][3];
//    int a = (mdfilter.length - 1) / 2;
//    int b = (mdfilter[0].length - 1) / 2;
//    int[][] f_padded = new int[2 * a + f.length][2 * b + f[0].length];
//    //zero padding code
//    for (int x = 0; x < f.length; x++)
//        for (int y = 0; y < f[0].length; y++)
//            f_padded[a + x][b + y] = f[x][y];
//
//    //medainfilter operation
//    for (int x = 0; x < f.length; x++)
//        for (int y = 0; y < f[0].length; y++) {
//            for (int s = -a; s <= a; s++)
//                for (int t = -b; t <= b; t++) {
//                    int v = w[s+a][t+b];
//                    F[x][y] = F[x][y] + v * f_padded[(a + x) - s][(b + y) - t];
//                }
//        }
//    return F;
//}



    public static void main(String[] args){
        int [ ] [ ] w = {{0,1,0},{1,-4,1}, {0 ,1 ,0}};
        Image img = new Image("C:\\Users\\DELL\\Desktop\\IMG_4994.jpg");
        int[][] f = img.getPixelArray();
        Image.display(f,"original image");
        Imagee imagee=new Imagee();
//        int[][] F=imagee.Convolve(f,w);
//        Image.display(F,"convoluted image");
//        int[][] rescaledpixel=rescale(F);
//
//        Image.display(rescaledpixel,"rescaled image");
//        int[][] enhancedpixel=imagee.enhanced(f,rescaledpixel);
//        Image.display(enhancedpixel,"enhanced Pixel");
        int[][] loggedpixels= ;
        Image.display(loggedpixels,"logged Pixel");




    }
}
