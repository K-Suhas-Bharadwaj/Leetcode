class Job implements Comparable<Job> {
    
    int startTime;
    int endTime;
    int profit;
    
    public Job(int startTime, int endTime, int profit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }
    
    public int compareTo(Job job) {
        return this.endTime - job.endTime;
    }
    
}

class Solution {
    
    public int getLastConflictingJob(Job[] jobs, int n) {
        
        int low = 0;
        int high = n;
        
        while(low <= high) {
            int mid = (low + high)/2;
            if(jobs[mid].endTime <= jobs[n].startTime)
                if(jobs[mid+1].endTime <= jobs[n].startTime)
                    low = mid + 1;
                else
                    return mid;
            else
                high = mid - 1;
        }
        
        return -1;
        
    }
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        
        int n = startTime.length;
        
        Job[] jobs = new Job[n];
        for(int i=0;i<startTime.length;i++) 
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        Arrays.sort(jobs);
        
        int[] maxProfit = new int[n];
        maxProfit[0] = jobs[0].profit;
        for(int i=1;i<n;i++) {
            int profitByCurrentJob = jobs[i].profit;
            int lastConflictingJob = getLastConflictingJob(jobs, i);
            if(lastConflictingJob != -1) profitByCurrentJob = profitByCurrentJob + maxProfit[lastConflictingJob];
            maxProfit[i] = Math.max(profitByCurrentJob,maxProfit[i-1]);
        }
        
        return maxProfit[n-1];
        
    }
    
}
