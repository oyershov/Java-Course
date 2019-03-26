import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    /**
     * Метод loadFromFile завантажує дані з файлу
     *
     * @param fileName назва файлу
     * @return повертає список типу String
     */
    static List<String> loadFromFile(String fileName) {
        List<String> strList = new ArrayList<>();
        BufferedReader br = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            br = new BufferedReader(new InputStreamReader(fis));
            String str;
            int line = 0;
            while ((str = br.readLine()) != null) {
                if (line >= 1) {
                    strList.add(str.replaceAll(";", ","));
                } else {
                    line++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return strList;
    }

    /**
     * Метод writeToFile записує дані у файл
     *
     * @param str      рядок даних
     * @param fileName назва файлу
     */
    public static void writeToFile(String str, String fileName) {
        BufferedWriter bw = null;
        File f = new File(fileName);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(f.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(str);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод parse перевіряє дані на відповідність до класу FlatForSale
     *
     * @param str рядок даних
     * @return повертає об'єкт класу FlatForSale
     * @throws Exception
     */
    public static FlatForSale ffsParse(String str) throws Exception {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.builder().addColumn("address").addColumn("floor").addColumn("square").
                addColumn("numOfRooms").addColumn("price").build();
        FlatForSale ffs = null;
        try {
            ffs = mapper.readerFor(FlatForSale.class).with(schema).readValue(str);
        } catch (Exception e) {
            throw new Exception("Invalid data: " + str);
        }
        if (StringUtils.isBlank(ffs.address) || ffs.floor <= 0 || ffs.square <= 0 ||
        		ffs.numOfRooms <= 0 || ffs.price <= 0) {
            throw new Exception("Invalid data: " + str);
        }
        return ffs;
    }

    public static void main(String args[]) throws JsonProcessingException {
        String csvFile = "data.csv";
        String logFile = "journal.log";
        String jsonFile = "new_data.json";
        LogInterface cLog = new ConsoleLog();
        LogInterface fLog = new FileLog(logFile);
        List<LogInterface> errors = Arrays.asList(cLog, fLog);
        ParseLog pLog = new ParseLog(errors);
        List<String> strList = loadFromFile(csvFile);
        List<FlatForSale> ffsList = new ArrayList<>();
        for (String str : strList) {
        	FlatForSale ffs = pLog.ffsParse(str);
            if (ffs != null) {
                ffsList.add(ffs);
            }
        }
        System.out.println("Flat for sale:");
        for (FlatForSale ffs : ffsList) {
            System.out.println(ffs);
        }
        List<FFS_Child> childList = new ArrayList<>();
        ffsList.forEach(ffs -> {
            FFS_Child child = new FFS_Child();
            child.address = ffs.address;
            child.floor = ffs.floor;
            child.square = ffs.square;
            child.numOfRooms = ffs.numOfRooms;
            child.price = ffs.price;
            childList.add(child);
        });
        childList.sort(Comparator.naturalOrder());
        System.out.println("\nFlat for sale sorted by price:");
        for (FFS_Child child : childList) {
            System.out.println(child);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(childList);
        writeToFile(json, jsonFile);
    }

}
