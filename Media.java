package AllFiles;

import java.io.Serializable;

//Media is abstract. This means that you have to create either a
//Movie or a Music since they are subclasses to Media. 

//In FileItem, Serializables is used so you can save the entire ArrayList
//instead of, for example, one string at a time.

//The purpose of Comparable<Media> is to find out if, for example,
//the string "Häst" should be before or after "Nisse" when a list
//is being sorted.
abstract class Media implements Serializable, Comparable<Media>
{
    private String title, genre;
    private int year;
    
    //Change this value in one obj and thy shall 
    //also see the change in the other objects
    private static int sort = 0;
    
    protected Media(String title, String genre, int year)
    {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }
    
    //These 2 functions are defined in Movie & Music.
    abstract String toStringSpec();
    abstract int compareToSpec(Media m);
    
    public int compareTo(Media m)
    {
        int result = 0;
        
        if(Media.sort == 0) //Compare titles
            result = this.title.compareTo(m.getTitle()); //A->Z
        else    //Compare albums if the Media is Music, actors if Movie.
            result = compareToSpec(m);
        
        return result;
    }
    
    public String toString()
    {
        StringBuilder b = new StringBuilder();
        
        b.append("Title: ");
        b.append(this.title);
        b.append("   |   Genre: ");
        b.append(this.genre);
        b.append("   |   Year: ");
        b.append(this.year);
        b.append(this.toStringSpec());
        
        return b.toString();    //Returns a string with all the info of the Media.
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getTitle()
    {
        return this.title;
    }
    
    public void setGenre(String genre)
    {
        this.genre = genre;
    }
    public String getGenre()
    {
        return this.genre;
    }
    
    public void setYear(int year)
    {
        this.year = year;
    }
    
    public int getYear()
    {
        return this.year;
    }
    
    public static void changeSort(int nr)
    {
        if(nr > -1 && nr < 2)
            Media.sort = nr;
    }
}
