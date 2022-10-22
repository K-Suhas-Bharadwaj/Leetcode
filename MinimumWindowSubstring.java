class Solution {
    int tFrequencyMap[];
    int windowFrequencyMap[];
    public boolean isWindowingString() {
        for(int i=65;i<=122;i++) if(tFrequencyMap[i] > 0 && tFrequencyMap[i] != windowFrequencyMap[i]) return false;
        return true;
    }
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";
        tFrequencyMap = new int[123];
        for(int i=0;i<123;i++) tFrequencyMap[i] = 0;
        for(int i=0;i<t.length();i++) tFrequencyMap[t.charAt(i)]++;
        for(int windowSize=t.length();windowSize<=s.length();windowSize++) {
            for(int i=0;i<s.length()-windowSize;i++) {
                if(i == 0) {
                    windowFrequencyMap = new int[123];
                    for(int index=0;index<123;index++) windowFrequencyMap[index] = 0;
                    for(int index=0;index<windowSize;index++) windowFrequencyMap[s.charAt(index)]++;
                } else {
                    tFrequencyMap[s.charAt(i - 1)]--;
                    tFrequencyMap[s.charAt(i + windowSize - 1)]++; 
                }
                if(isWindowingString()) return s.substring(i, i + windowSize);
            }
        }
        return "";
    }
}
