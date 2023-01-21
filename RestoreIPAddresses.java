class Solution {

    private boolean isValidClass(String s) {
        if(s.length() == 1) {
            return true;
        }
        else if(s.charAt(0) != '0' && s.length() < 4 && Integer.parseInt(s) < 256) {
            return true;
        } else {
            return false;
        }
    }

    private void getPossibleIpAddresses(int startIndex, int[] cutPoints, int cutsAvailable, String s, List<String> possibleIpAddresses) {
        if(startIndex == s.length()) {
            return;
        }
        if(s.substring(startIndex).length() > (cutsAvailable + 1) * 4) {
            return;
        }
        if(s.substring(startIndex).length() < cutsAvailable + 1) {
            return;
        }
        if(cutsAvailable == 3 && !isValidClass(s.substring(0, startIndex + 1))) {
            return;
        }
        if(cutsAvailable == 2 && !isValidClass(s.substring(cutPoints[0] + 1, startIndex + 1))) {
            return;
        }
        if(cutsAvailable == 1 && !isValidClass(s.substring(cutPoints[1] + 1, startIndex + 1))) {
            return;
        }
        cutPoints[3-cutsAvailable] = startIndex;
        cutsAvailable--;
        if(cutsAvailable == 0) {
            if(isValidClass(s.substring(cutPoints[2] + 1))) {
                StringBuilder possibleIpAddress = new StringBuilder();
                possibleIpAddress.append(s.substring(0, cutPoints[0] + 1));
                possibleIpAddress.append(".");
                possibleIpAddress.append(s.substring(cutPoints[0] + 1, cutPoints[1] + 1));
                possibleIpAddress.append(".");
                possibleIpAddress.append(s.substring(cutPoints[1] + 1, cutPoints[2] + 1));
                possibleIpAddress.append(".");
                possibleIpAddress.append(s.substring(cutPoints[2] + 1));
                possibleIpAddresses.add(new String(possibleIpAddress));
            }
        } else {
            getPossibleIpAddresses(startIndex + 1, cutPoints, cutsAvailable, s, possibleIpAddresses);
        }
        cutsAvailable++;
        cutPoints[3-cutsAvailable] = -1;
        getPossibleIpAddresses(startIndex + 1, cutPoints, cutsAvailable, s, possibleIpAddresses);
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> possibleIpAddresses = new ArrayList<String>();
        if(s.length() <= 12) {
            int[] cutPoints = new int[3];
            getPossibleIpAddresses(0, cutPoints, 3, s, possibleIpAddresses);
        }
        return possibleIpAddresses;
    }

}
