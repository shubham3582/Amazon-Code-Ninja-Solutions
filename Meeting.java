/*
Author Shubham Srivastava
Problem Amazon Code Ninja - Meeting Schedules
*/
import java.util.*;
 
class MeetingManager
{
    private int meetingCount;
    private int duration;
    private Map meetings;
    private Map available;
    private boolean isProcessed;
 
    private static int toMinutes(int hh, int mm)
    {
        return (60*hh + mm) ;
    }
 
    private static String toHHMM (int minutes)
    {
        String hh = Integer.toString ( minutes / 60 );
        String mm = Integer.toString ( minutes % 60 );
        if (hh.equals("24"))
        {
            hh = "00";
        }
        if (hh.length() == 1)
        {
            hh = "0" + hh;
        }
        if (mm.length() == 1)
        {
            mm = "0" + mm;
        }
        String ret = hh + " " + mm;
        return (ret);
    }
 
    private static void printMap(Map<Integer, Integer> m)
    {
        List<Integer> sortedKeys = new ArrayList<Integer> (m.keySet());
        Collections.sort(sortedKeys);
 
        int startTime, endTime;
        for (int i = 0 ; i < sortedKeys.size() ; i++ )
        {
            startTime = sortedKeys.get(i);
            endTime   = m.get(startTime);
            System.out.println( toHHMM(startTime) +
                               " " +
                               toHHMM(endTime) );
        }
    }
 
    public void printMeetings()
    {
        printMap (this.meetings);
    }
 
    public void printAvailable()
    {
        if (!this.isProcessed)
        {
            this.mergeMeetings();
            this.setAvailableSlots();
            this.isProcessed = true;
        }
        printMap (this.available);
    }
 
    public void addMeeting(String meetingTime)
    {
        String[] x = meetingTime.split("\\s+");
 
        int s_hh   = Integer.parseInt(x[0]);
        int s_mm   = Integer.parseInt(x[1]);
        int f_hh   = Integer.parseInt(x[2]);
        int f_mm   = Integer.parseInt(x[3]);
 
        int start  = toMinutes(s_hh, s_mm);
        int end    = toMinutes(f_hh, f_mm);
 
        if (end == 0)
        {
            end = 1440;
        }
 
        if (this.meetings.containsKey(start))
        {
            int prev_end_time = (int) this.meetings.get(start);
            if (prev_end_time < end)
            {
                this.meetings.put(start, end);
            }
        }
        else
        {
            this.meetings.put(start, end);
        }
    }
 
    private void mergeMeetings()
    {
        List<Integer> sortedStartTimes = new ArrayList<Integer> (this.meetings.keySet());
        Collections.sort(sortedStartTimes);
 
        int start, end, prev_start, prev_end;
        for (int i = 0 ; i < sortedStartTimes.size() ; i++)
        {
            start = (int) sortedStartTimes.get(i);
            end   = (int) this.meetings.get(start);
            for (int j = 0 ; j < i ; j++)
            {
                prev_start = (int) sortedStartTimes.get(j);
                if (! this.meetings.containsKey(prev_start))
                {
                    continue;
                }
                prev_end   = (int) this.meetings.get(prev_start);
                if (start <= prev_end)
                {
                    this.meetings.remove(start);
                    if (end > prev_end)
                    {
                        this.meetings.put(prev_start, end);
                    }
                }
            }
        }
    }
 
    private void setAvailableSlots()
    {
        List<Integer> sortedStartTimes = new ArrayList<Integer> (this.meetings.keySet());
        Collections.sort(sortedStartTimes);
        int av_start = 0;
        for (int i = 0 ; i < sortedStartTimes.size() ; i++)
        {
            int m_start = sortedStartTimes.get(i);
            if ((m_start - av_start) >= this.duration)
            {
                this.available.put(av_start, m_start);
            }
            av_start = (int) this.meetings.get(m_start);
        }
        if ((1440 - av_start) >= this.duration)
        {
            this.available.put(av_start, 1440);
        }
    }
 
    public MeetingManager(int m, int k)
    {
        this.meetingCount = m;
        this.duration = k;
        this.meetings = new HashMap<Integer, Integer>();
        this.available = new HashMap<Integer, Integer>();
        this.isProcessed = false;
    }
}
 
public class Solution
{
 
    static MeetingManager getInput()
    {
        Scanner sc = new Scanner(System.in);
        String[] mk = sc.nextLine().split("\\s+");
        MeetingManager mm = new MeetingManager(Integer.parseInt(mk[0]), Integer.parseInt(mk[1]));
        String[] times = new String[Integer.parseInt(mk[0])];
        for (int i = 0; sc.hasNextLine(); i++)
        {
            mm.addMeeting (sc.nextLine());
        }
        return mm;
    }
 
    public static void main (String[] args)
    {
        MeetingManager mm = getInput();
        //mm.printMeetings();
        mm.printAvailable();
 
     }
    
}
