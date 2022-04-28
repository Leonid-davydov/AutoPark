import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MechanicService implements Fixer{
    private static String[] details = new String[] {"Filter", "Sleeve", "Shaft", "Axis", "Candle", "Oil", "GRM", "ShRUS"};
    private static String PATH = "./orders.csv";
    private static final CSVParser parserColon = new CSVParserBuilder()
            .withSeparator(',')
            .withIgnoreQuotations(true)
            .build();

    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {

            Map<String, Integer> breakdowns = new HashMap<>();
            int id = vehicle.getId();
            String line = "" + id;
            String detail;
            Integer number;

            for (int i = 0; i < 3; i++){
                detail = details[getRandomInteger(0,7)];
                number = getRandomInteger(0,2);

                if (number != 0) {
                    breakdowns.put(detail, number);
                }
            }

            for(Map.Entry<String, Integer> entry : breakdowns.entrySet()){
                line += "," + entry.getKey() + "," + entry.getValue();
            }

            if (!breakdowns.isEmpty())
                write(line);

        return breakdowns;
    }

    @Override
    public void repair(Vehicle vehicle) {
        List<String[]> lines = null;

        try (CSVReader reader = new CSVReader(new FileReader(PATH))) {

            lines = reader.readAll();

            for (int i = 1; i < lines.size(); i++) {
                if (Integer.parseInt(lines.get(i)[0].trim()) == vehicle.getId())
                    lines.remove(i);
            }

            CSVWriter writer = new CSVWriter(new FileWriter(PATH),
                    ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    ' ',
                    "\n");

            writer.writeAll(lines);
            writer.close();

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        List<String[]> lines;

        try (CSVReader reader = new CSVReader(new FileReader(PATH))) {

            lines = reader.readAll();

            for (int i = 1; i < lines.size(); i++) {
                if (Integer.parseInt(lines.get(i)[0].trim()) == vehicle.getId())
                    return true;
            }


        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static int getRandomInteger(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    private void write(String str) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(PATH, true),
                ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                ' ',
                "\n"))
        {
            String[] line = {str};
            writer.writeNext(line, false);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int findMost() {
        int id = 0;
        int maxBreakdowns = 0;
        int sum;
        List<String[]> lines;

        try (CSVReader reader = new CSVReader(new FileReader(PATH))) {

            lines = reader.readAll();

            for (int i = 1; i < lines.size(); i++) {
                sum = 0;

                for (int j = 2; j < lines.get(i).length; j +=2){
                    sum += Integer.parseInt(lines.get(i)[j].trim());
                }

                if (sum > maxBreakdowns) {
                    maxBreakdowns = sum;
                    id = Integer.parseInt(lines.get(i)[0].trim());
                }
            }

            System.out.println("Max number of Breakdowns is " + maxBreakdowns);

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return id;
    }
}
