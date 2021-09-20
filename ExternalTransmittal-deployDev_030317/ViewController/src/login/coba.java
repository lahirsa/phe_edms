package login;

public class coba {
    public coba() {
        super();
    }

    public static void main(String[] args) {
        coba coba = new coba();
        String text="";
//        String s ="119101108099111109101049";
        String s ="119101108099111109101";
        int interval = 3;
        int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
        String[] result = new String[arrayLength];

        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        } //Add the last bit
        result[lastIndex] = s.substring(j);
        for (int i=0;i<arrayLength;i++){
                    System.out.println(result[i]);
                    System.out.println((char) Integer.parseInt(result[i]));
                text=text+(char) Integer.parseInt(result[i]);
            }
        System.out.println("hasil = "+text);
    }
}
