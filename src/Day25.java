public class Day25 {

    public static void main(String[] args) {
        part1();
    }

    static void part1() {
        long cardPubKey =8184785;
        long doorPubKey =5293040;

        int i = getLoopSize(cardPubKey, 7);
        int j= getLoopSize(doorPubKey, 7);

        System.out.println("loop size for card is " + i);
        System.out.println("loop size for door is " + j);
        long val = 1;
        for (int k = 0; k < i; k++) {
            val = val*doorPubKey;
            val = val%20201227;
        }

        System.out.println("ans is " + val);
    }

    private static int getLoopSize(long publicKey, long subjectNum) {
        long val = 1;
        int i = 0;
        while (true) {
            val = val*subjectNum;
            val = val%20201227;

            i++;
            if(val == publicKey) break;
        }
        return i;
    }
}
