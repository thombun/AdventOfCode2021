package advent.advent16;

import advent.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Advent16 {

    public static final String FILE = "ad16sample.txt";
    public List<String> input = new ArrayList<>();

    Map<Integer, Integer> values = new HashMap<>();

    public void calc() throws IOException {
        InputStream inputStream = Loader.loadFile(FILE);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }

            int count = 0;
            for (String s : input) {
                int path = part1(s, count);
                count++;
                System.out.println("----------- " + path);
            }

            for (Integer integer : values.keySet()) {
                System.out.println("score: " + integer + " " + values.get(integer));
            }
            System.out.println("end");
        }
    }

    private int part1(String input, int i) {
        //hex to bin
        String s = hexToBin(input);
//        System.out.println(s);
        return packet(s, i).length();
    }

    public String packet(String s, int level) {
        if (s.length() < 11 && !s.contains("1")) {
            return "";
        }

        //check packet version
        int iVersion = getVersion(s);
        countValue(level, iVersion);

        int iId = getId(s);

        String packet = s.substring(6);

        if (iId == 4) {
            int left = getLiteral(packet, 5);
            packet = packet.substring(left);
        } else {
            String lengthTypeId = packet.substring(0, 1);

            if (lengthTypeId.equals("0")) {
                //15
                int length = Integer.parseInt(packet.substring(1, 15 + 1), 2);
                packet = packet.substring(15 + 1);
                String rest = packet;
                int left = 0;
                while (length > 0) {
                    int id = getId(rest);
                    if (id == 4) {
//                        countValue(level, getVersion(rest));
//                        left = getLiteral(rest.substring(6), 5);
//                        rest = rest.substring(6 + left);
//                        length -= 6 + left;
//                        packet = packet.substring(6 + left);
                        int tmpl = packet.length();
                        packet = packet(packet, level);
                        length -= tmpl - packet.length();
                        if (!rest.contains("1")) {
                            break;
                        }
                    } else {
                        if (rest.length() >= length) {
                            packet(rest.substring(0, length), level);
                            break;
                        }
                    }
                }
            } else {
                //11
                String lengthHeader = packet.substring(1, 11 + 1);
                int count = Integer.parseInt(lengthHeader, 2);
                packet = packet.substring(lengthHeader.length() + 1);

                String tmpPacket = packet;
//                while (tmpPacket.charAt(tmpPacket.length() - 1) == '0') {
//                    tmpPacket = tmpPacket.substring(0, tmpPacket.length() - 1);
//                }
//                int length = (tmpPacket.length()) / count;
                for (int i = 1; i <= count; i++) {
                    packet = packet(packet, level);
                }
                System.out.println();
            }
        }
        return packet;
    }

    private void countValue(int level, int iVersion) {
        System.out.println("id:" + iVersion + " thingy");
        if (values.containsKey(level)) {
            values.put(level, values.get(level) + iVersion);
        } else {
            values.put(level, iVersion);
        }
    }

    public int getId(String s) {
        String id = s.substring(3, 6);
        return Integer.parseInt(id, 2);
    }

    public int getVersion(String s) {
        String version = s.substring(0, 3);
        return Integer.parseInt(version, 2);
    }

    public int getLiteral(String packet, int length) {
        String num = "";
        int count = 0;
        for (int i = 0; i < packet.length() / length; i++) {
            String group = packet.substring(i * length, (i + 1) * length);
            String iNum = group.substring(1);//cut first bit
            num += iNum;
            count++;
            if (group.startsWith("0")) {
                break;
            }
        }
        Long decimal = Long.parseLong(num, 2);
        System.out.println("decimal: " + decimal);
        return count * 5;
    }

    private String hexToBin(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }
}
