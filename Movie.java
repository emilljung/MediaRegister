package AllFiles;

class Movie extends Media
{
    private int playTime;
    private String actor, director;
    
    Movie(String title, String genre, int year, int playTime, String actor, String director)
    {
        super(title, genre, year);
        this.playTime = playTime;
        this.actor = actor;
        this.director = director;
    }
    
    @Override
    String toStringSpec()
    {
        StringBuilder b = new StringBuilder();
        
        b.append("   |   PlayTime: ");
        b.append(this.playTime);
        b.append("   |   Actor: ");
        b.append(this.actor);
        b.append("   |   Director: ");
        b.append(this.director);
        
        return b.toString();
    }
    
    @Override
    int compareToSpec(Media m)
    {
        return this.actor.compareTo(((Movie)m).getActor()); //A->Z
    }
    
    void setPlayTime(int playTime)
    {
        this.playTime = playTime;
    }
    
    int getPlayTime()
    {
        return this.playTime;
    }
    
    void setActor(String actor)
    {
        this.actor = actor;
    }
    
    String getActor()
    {
        return this.actor;
    }
    
    void setDirector(String director)
    {
        this.director = director;
    }
    
    String getDirector()
    {
        return this.director;
    }
}
