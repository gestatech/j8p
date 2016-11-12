package io.r2.j8p.t1_language;

/**
 * Use static and default methods of an interface including inheritance rules for a default method
 *
 * @author robymus <r@r2.io>
 */
public class Interfaces {

    public interface INum {
        int getNum();
        default String getAsString() {
            return String.valueOf(getNum());
        }

        static INum getInstance(int num) {
            return new INum() {
                @Override
                public int getNum() {
                    return num;
                }
            };
        }
    }

    public interface ILong {
        long getLong();
        default String getAsString() {
            return String.valueOf(getLong());
        }
    }

    public class NumProv implements INum, ILong {

        private int num;

        public NumProv(int n) {
            num = n;
            // Note: static methods are not inherited from interface
        }

        @Override
        public int getNum() {
            return num;
        }

        @Override
        public long getLong() {
            return getNum();
        }

        // we have a name conflict in INum and ILong, so we must implement getAsString in class
        @Override
        public String getAsString() {
            return String.valueOf(num);
        }
    }


}
