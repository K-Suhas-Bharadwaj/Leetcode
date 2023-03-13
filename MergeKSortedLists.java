class ValueInList {

    int value;
    int listNumber;

    public ValueInList(int value, int listNumber) {
        this.value = value;
        this.listNumber = listNumber;
    }

}

class Solution {

    public ListNode mergeKLists(ListNode[] lists) {

        int n = lists.length;

        if(n == 0) {
            return null;
        } 
        
        else if(n == 1) {
            return lists[0];
        } 
        
        else {

            PriorityQueue<ValueInList> valuesInLists = new PriorityQueue<ValueInList>(
                new Comparator<ValueInList>() {
                    public int compare(ValueInList valueOne, ValueInList valueTwo) {
                        return valueOne.value - valueTwo.value;
                    }
                }
            );

            for(int i = 0; i < n; i++) {
                if(lists[i] != null) {
                    valuesInLists.add(new ValueInList(lists[i].val, i));
                }
            }

            ListNode resultList = new ListNode(-1);
            ListNode resultListCurrentPointer = resultList;

            while(!valuesInLists.isEmpty()) {
                ValueInList minOfAll = valuesInLists.poll();
                int listNumber = minOfAll.listNumber;
                resultListCurrentPointer.next = new ListNode(minOfAll.value);
                resultListCurrentPointer = resultListCurrentPointer.next;
                lists[listNumber] = lists[listNumber].next;
                ListNode nextPointer = lists[listNumber];
                if(nextPointer != null) {
                    valuesInLists.add(new ValueInList(nextPointer.val, listNumber));
                }
            }

            return resultList.next;

        }

    }
    
}
