package io.r2.j8p.t1_language;

/**
 * Develop code that uses String objects in the switch statement, binary literals, and numeric literals,
 * including underscores in literals
 */
public class Basics {

    private int dec = 1234;
    private int hex = 0xdeadface;
    private int oct = 0644;
    private int bin = 0b1101010;
    private int long_dec = 1_2_3_4_5;

    // 0b_1 is invalid
    private int long_bin = 0b1101_1001_1001;

    public int getVal(String varName) {
        switch (varName) {
            case "dec": return dec;
            case "hex": return hex;
            case "oct": return oct;
            case "bin": return bin;
            case "long_dec": return long_dec;
            case "long_bin": return long_bin;
            default:
                throw new IllegalArgumentException(varName);
        }
    }
}
