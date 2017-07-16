package AllFiles;

import java.io.*;
import javax.swing.JOptionPane;

public class FileItem<T>
{
    private String fileName;
    private File file;
    
    public FileItem(String fileName)
    { 
        this.fileName = fileName;
    }
    
    public void saveToFile(T list)
    {
        this.file = new File(fileName);
        
        //Try to save list to file.
        try
        {
            if(!file.exists())
                file.createNewFile();
            
            FileOutputStream FOS = new FileOutputStream(file);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);

            OOS.writeObject(list);  //Put the entire list in the ObjectOutputStream,
                                    //which then saves the list to file thanks to FileOutputStream.
           
            OOS.close();
            FOS.close();
        }
        catch(Exception e)
        {
            //The list could, for whatever reason, not be saved.
            JOptionPane.showMessageDialog(null, "Det gick inte att spara filen!", "Felmeddelande", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public T loadFromFile()
    {
        T list = null;

        this.file = new File(fileName);
        
        //Try to load the list from the file.
        try
        {
            if(file.exists())
            {
                FileInputStream FIS = new FileInputStream(file);
                ObjectInputStream OIS = new ObjectInputStream(FIS);

                list = (T)OIS.readObject();  //Put the list in the file to the ArrayList<Media> called "list".

                OIS.close();
                FIS.close();
            }
        }
        catch(Exception e)
        {
            //The file could, for whatever reason, not be read.
            JOptionPane.showMessageDialog(null, "Det gick inte att ladda filen!", "Felmeddelande", JOptionPane.ERROR_MESSAGE);
        }
        
        return list;
    }
}
