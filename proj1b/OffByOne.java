public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int char1 = (int) x;
        int char2 = (int) y;
        int diff = Math.abs(char1 - char2);
        return diff == 1;
    }
}
