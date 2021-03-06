
public class Kjanos_Base_Class implements Kjanos_Interface{
    
    private String text;
    private char[] CArray;
    private char[][] CMatrix;
    
    public Kjanos_Base_Class(String Input){
        this.CArray = Input.toCharArray();
    }
    
    public void set_Tscher(){
        int base = 0, c = 0;
        for(int i = 0; i < this.CArray.length; i++){
            if(Character.isLetter(this.CArray[i]) || this.CArray[i] == ' ' || this.CArray[i] == '\''){
                base++;
            }
        }
        char[][] output = new char [5][base];
        base = 0;
        for(int i = 0; i < this.CArray.length; i++){
            if(Character.isLetter(this.CArray[i]) || this.CArray[i] == ' ' || this.CArray[i] == '\''){
                base++;
                c = 0;
            }
            output[c][base-1] = this.CArray[i];
            c++;
        }
        this.CMatrix = output;
    }

    public void set_NJSR(){
        String output = "";
        char[] base = this.CMatrix[0];
        StringBuilder builder = new StringBuilder();
        
        for(int n = 0; n < this.CMatrix[0].length; n++){
            builder.setLength(0);
            builder.append(base[n]);
            for(int m = 1; m < this.CMatrix.length; m++){
                switch(this.CMatrix[m][n]){
                    case '\u0330':                              //tilde below
                        builder.insert(0, "n");
                        break;
                    case '\u0325':                              //ring below
                        builder.insert(0, "j");
                        break;
                    case '\u0303':                              //tilde above
                        if(base[n] != 'n' && base[n] != 'N'){
                            builder.append("n");  
                        } else {
                            for (int i = 0; i < builder.length(); i++){
                                if(builder.charAt(i) == base[n] && this.CMatrix[m][n] != ' '){
                                    builder.insert(i+1, this.CMatrix[m][n]);
                                    break;
                                }
                            }
                        }
                        break;
                    case '\u030A':                              //ring above
                        builder.append("j");
                        break;
                    case '\u0328':                              //ogonek below
                        builder.append("s");
                        break;
                    case '\u1DCE':                              //ogonek above
                        builder.insert(0, "s");
                        break;
                    case '\u02DE':                              //rhotic hook
                        builder.append("r");
                        break;
                    case '\u0304':                              //macron
                        for (int i = 0; i < builder.length(); i++){
                            if(builder.charAt(i) == base[n]){
                                builder.insert(i, base[n]);
                                break;
                            }
                        }
                        break;
                    default:
                        for (int i = 0; i < builder.length(); i++){
                            if(builder.charAt(i) == base[n] && this.CMatrix[m][n] != ' '){
                                builder.insert(i+1, this.CMatrix[m][n]);
                                break;
                            }
                        }  
                }
            }
            output = output + builder;     //column change
        }
        this.text = output;
    }

    public void set_Bengmellanbli(){
        this.text = replaceI(this.text, "", "T\u0301", "", "t\u0301", "D", "d");                          //T bli D
        this.text = replaceI(this.text, "", "P\u0301", "", "p\u0301", "B", "b");                          //P bli B
        this.text = replaceI(this.text, "", "S\u0301", "", "s\u0301", "Z", "z");                          //S bli Z
        this.text = replaceI(this.text, "", "F\u0301", "", "f\u0301", "V", "v");                          //F bli V
        this.text = replaceI(this.text, "", "K\u0301", "", "k\u0301", "K", "k");                          //K bli G
        this.text = replaceI(this.text, "", "C\u0301", "", "c\u0301", "Dz", "dz");                        //C bli DZ
        this.text = replaceI(this.text, "??\u0300", "S\u030C\u0301", "??\u0300", "s\u030C\u0301", "??", "??");//?? bli ??
        this.text = replaceI(this.text, "??\u0300", "C\u030C\u0301", "??\u0300", "c\u030C\u0301", "??", "??");//?? bli ??
        this.text = replaceI(this.text, "", "T\u0309\u0301", "", "t\u0309\u0301", "Dh", "dh");            //TH bli DH
        this.text = replaceI(this.text, "", "D\u0300", "", "d\u0300", "T", "t");                          //T bli D
        this.text = replaceI(this.text, "", "B\u0300", "", "b\u0300", "P", "p");                          //B bli P
        this.text = replaceI(this.text, "", "Z\u0300", "", "z\u0300", "S", "s");                          //Z bli S
        this.text = replaceI(this.text, "", "V\u0300", "", "v\u0300", "F", "f");                          //V bli F
        this.text = replaceI(this.text, "", "G\u0300", "", "g\u0300", "K", "k");                          //G bli K
        this.text = replaceI(this.text, "??\u0300", "Z\u030C\u0300", "??\u0300", "z\u030C\u0300", "??", "??");//?? bli ??
        this.text = replaceI(this.text, "", "D\u0309\u0300", "", "d\u0309\u0300", "Th", "th");            //TH bli DH
        this.text = replaceI(this.text, "??", "A\u0308", "??", "a\u0308", "A", "a");             //Aa with diaeresis
        this.text = replaceI(this.text, "??", "E\u0308", "??", "e\u0308", "E", "e");             //Ee with diaeresis
        this.text = replaceI(this.text, "??", "I\u0308", "??", "i\u0308", "I", "i");             //Ii with diaeresis
        this.text = replaceI(this.text, "??", "O\u0308", "??", "o\u0308", "??", "??");             //Oo with diaeresis
        this.text = replaceI(this.text, "??", "U\u0308", "??", "u\u0308", "Y", "y");             //Uu with diaeresis
        this.text = replaceI(this.text, "??", "", "??", "", "An", "an");                         //Aa with tilde
        this.text = replaceI(this.text, "???", "", "???", "", "En", "en");                         //Ee with tilde
        this.text = replaceI(this.text, "??", "", "??", "", "In", "in");                         //Ii with tilde
        this.text = replaceI(this.text, "??", "", "??", "", "On", "on");                         //Oo with tilde
        this.text = replaceI(this.text, "??", "", "??", "", "Un", "un");                         //Uu with tilde
        this.text = replaceI(this.text, "??", "", "??", "", "Aj", "aj");                         //Aa with ring
        this.text = replaceI(this.text, "??", "", "??", "", "Aa", "aa");                         //Aa with macron
        this.text = replaceI(this.text, "??", "", "??", "", "Ee", "ee");                         //Ee with macron
        this.text = replaceI(this.text, "??", "", "??", "", "Ii", "ii");                         //Ii with macron
        this.text = replaceI(this.text, "??", "", "??", "", "Oo", "oo");                         //Oo with macron
        this.text = replaceI(this.text, "??", "", "??", "", "Uu", "uu");                         //Uu with macron
        this.text = replaceI(this.text, "", "R\u030C", "", "r\u030C", "R", "r");               //Rr with caron
        this.text = replaceI(this.text, "??", "", "??", "", "L", "l");                           //Ll with bar
        this.text = replaceI(this.text, "??", "U\u0306", "??", "u\u0306", "W", "w");             //Uu with breve
        this.text = replaceI(this.text, "??", "I\u0306", "??", "i\u0306", "J", "j");             //Ii with breve
        this.text = replaceI(this.text, "", "F\u0309", "", "f\u0309", "F", "f");               //Dd with hook
        this.text = replaceI(this.text, "C", "", "c", "", "Ts", "ts");                         //Cc
        this.text = replaceI(this.text, "X", "", "x", "", "Ks", "ks");                         //Xx
        this.text = replaceI(this.text, "", "H\u0309", "", "h\u0309", "X", "x");               //Hh with hook
        this.text = replaceI(this.text, "??", "S\u030C", "??", "s\u030C", "Sh", "sh");           //Ss with caron
        this.text = replaceI(this.text, "??", "C\u030C", "??", "c\u030C", "Ch", "ch");           //Cc with caron
        this.text = replaceI(this.text, "??", "Z\u030C", "??", "z\u030C", "Dj", "dj");           //Zz with caron
        this.text = replaceI(this.text, "", "R\u0309", "", "r\u0309", "Rh", "rh");             //Rr with hook
        this.text = replaceI(this.text, "??", "N\u0303", "??", "n\u0303", "Gn", "gn");           //Nn with tilde
        this.text = replaceI(this.text, "", "N\u0309", "", "n\u0309", "Ng", "ng");             //Nn with hook
        this.text = replaceI(this.text, "", "D\u0309", "", "d\u0309", "Dh", "dh");             //Dd with hook
        this.text = replaceI(this.text, "", "T\u0309", "", "t\u0309", "Th", "th");             //Dd with hook
        this.text = replaceI(this.text, "??", "c\u0327", "??", "c\u0327", "H", "h");             //Remove chandrabindu
        this.text = replaceI(this.text, "", "\u0307", "", "", "", "");                         //Remove aspiration
        this.text = replaceI(this.text, "", "\u0323", "", "", "", "");                         //Remove ejectiveness
        this.text = replaceI(this.text, "", "\u0310", "", "", "", "");                         //Remove chandrabindu
        this.text = replaceI(this.text, "", "\u0300", "", "", "", "");                         //Remove grave
        this.text = replaceI(this.text, "", "\u0301", "", "", "", "");                         //Remove acute
    }

    public String get_Kjanos(){
        return this.text;
    }
    
    public static String replaceI (String str, String case1, String case2, String case3, String case4, String rep1, String rep2){
        if(!case1.equals(""))str = str.replaceAll(case1, rep1);      
        if(!case2.equals(""))str = str.replaceAll(case2, rep1);
        if(!case3.equals(""))str = str.replaceAll(case3, rep2);        
        if(!case4.equals(""))str = str.replaceAll(case4, rep2);
        return str;
    }
    
}
