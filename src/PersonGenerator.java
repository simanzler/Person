import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    private static final Scanner console = new Scanner(System.in);
    public static void main(String[] args)
    {
        String fileName = SafeInput.getNonZeroLenString(console, "Enter the text file name");

        ArrayList<Person> PersonList = new ArrayList<>();

        boolean done = false;
        while(!done)
        {
            Person person = new Person("","","","",0);
            person.setID(SafeInput.getNonZeroLenString(console, "Enter the ID"));
            person.setfName(SafeInput.getNonZeroLenString(console, "Enter the first name"));
            person.setlName(SafeInput.getNonZeroLenString(console, "Enter the last name"));
            person.setTitle(SafeInput.getNonZeroLenString(console, "Enter the title"));
            person.setYOB(SafeInput.getInt(console, "Enter the year of birth"));

            PersonList.add(person);

            done = SafeInput.getYNConfirm(console, "Are you done adding to the database?");
        }

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".txt");

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for(Person rec : PersonList)
            {
                writer.write(rec.toCSV(), 0, rec.toCSV().length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
