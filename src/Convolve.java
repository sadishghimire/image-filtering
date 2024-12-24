public class Convolve {

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

//        for (int x = 0; x <F.length ; x++)
//            for (int y = 0; y <F[0].length ; y++){
//                F[x][y]=F[x][y]+f[x][y];
//            }

        //assigning minimum value to zero and max value to 255 using interpolation
        for (int x = 0; x <F.length ; x++)
            for (int y = 0; y <F[0].length ; y++){
                F[x][y]=(int) ((double)(F[x][y]-min)/(max-min)*255);
            }
        for (int x = 0; x <10 ; x++)
            for (int y = 0; y <10 ; y++){
                System.out.println( F[x][y]);
            }
        img.display(F, "Convoluted image");

    }
    public static void main(String[] args){

        Convolve c =new  Convolve();
        Convolution();
    }
}
