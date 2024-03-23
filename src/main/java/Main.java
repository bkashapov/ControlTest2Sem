import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.nio.file.Files;
public class Main {
    public static void main(String[] args) {
        List<TVprogram> allPrograms = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File("C:/Users/bulat/Downloads/Telegram Desktop/schedule.txt"));
            List<String> list = new ArrayList<String>();
            while (s.hasNext()){
                list.add(s.next());
            }
            s.close();

            List<String> testList = Files.readAllLines(new File("C:/Users/bulat/Downloads/Telegram Desktop/schedule.txt").toPath(), Charset.defaultCharset());


            Map<Channel, List<TVprogram>> map = new HashMap<>();
            Channel channel = null;
            List<TVprogram> programList = new ArrayList<>();
            byte hour = 0;
            byte minutes = 0;
            for(String str: testList) {
                if (str.charAt(0) == '#') {
                    if(channel != null) {
                        map.put(channel,programList);
                    }
                    programList = new ArrayList<>();
                    switch(str) {
                        case "#Первый" :
                            channel = Channel.Первый;
                            break;
                        case "#Россия 1" :
                            channel = Channel.Россия1;
                            break;
                        case "#Матч!" :
                            channel = Channel.Матч;
                            break;
                        case "#НТВ" :
                            channel = Channel.НТВ;
                            break;
                        case "#Пятый канал" :
                            channel = Channel.ПятыйКанал;
                            break;
                        case "#Культура" :
                            channel = Channel.Культура;
                            break;
                        case "#Карусель" :
                            channel = Channel.Карусель;
                            break;
                        case "#Звезда" :
                            channel = Channel.Звезда;
                            break;
                        case "#ТНВ" :
                            channel = Channel.ТНВ;
                            break;
                    }
                    continue;
                }
                try {
                    Integer.parseInt(String.valueOf((str.charAt(0))));
                    String a = str.substring(0, 2);
                    hour = Byte.parseByte(a);
                    minutes = Byte.parseByte(str.substring(3));
                } catch (NumberFormatException e) {
                    TVprogram program = new TVprogram();
                    program.time = new BroadcastsTime(hour, minutes);
                    program.title = str;
                    program.channel = channel;
                    programList.add(program);
                    allPrograms.add(program);

                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("ошибка");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void point6(List<TVprogram> allPrograms) {
        allPrograms.sort(new Comparator<TVprogram>() {
            @Override
            public int compare(TVprogram o1, TVprogram o2) {
                return o1.time.compareTo(o2.time);
            }
        });
        for(TVprogram p: allPrograms) {
            System.out.println(p);
        }
    }
    public List<TVprogram> point7(Map<Channel, List<TVprogram>> map, BroadcastsTime time) {
        List<TVprogram> res = new ArrayList<>();
        for(Channel channel: map.keySet()) {
            List<TVprogram> programs = map.get(channel);
            for(int i = 0; i < programs.size() - 1; i++) {
                if(time.between(programs.get(i).time, programs.get(i + 1).time)) {
                    res.add(programs.get(i));
                }
            }
            if (time.befor(programs.get(programs.size() - 1).time)) {
                res.add(programs.get(programs.size() - 1));
            }
        }
        return res;
    }
    public List<TVprogram> point8(List<TVprogram> allPrograms, String title) {
        List<TVprogram> res = new ArrayList<>();
        for(TVprogram p : allPrograms) {
            if(p.title == title) {
                res.add(p);
            }
        }
        return res;
    }
    public List<TVprogram> point9(Map<Channel, List<TVprogram>> map, Channel channel, BroadcastsTime time) {
        List<TVprogram> res = new ArrayList<>();
        List<TVprogram> programs = map.get(channel);
        for(int i = 0; i < programs.size() - 1; i++) {
            if(time.between(programs.get(i).time, programs.get(i + 1).time)) {
                res.add(programs.get(i));
            }
        }
        if (time.befor(programs.get(programs.size() - 1).time)) {
            res.add(programs.get(programs.size() - 1));
        }
        return res;
    }
    public List<TVprogram> point10(Map<Channel, List<TVprogram>> map, Channel channel,BroadcastsTime time1, BroadcastsTime time2) {
        List<TVprogram> res = new ArrayList<>();
        List<TVprogram> programs = map.get(channel);
        for(int i = 0; i < programs.size(); i++) {
            if(programs.get(i).time.between(time1, time2)) {
                res.add(programs.get(i));
            }
        }
        return res;
    }
}
