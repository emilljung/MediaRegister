package AllFiles;

class Music extends Media
{
    private String band, album;
    
    Music(String title, String genre, int year, String band, String album)
    {
        super(title, genre, year);  //Requires constructor in Media
        this.band = band;
        this.album = album;
    }
    
    @Override
    String toStringSpec()
    {
        StringBuilder b = new StringBuilder();
        
        b.append("   |   Band: ");
        b.append(this.band);
        b.append("   |   Album: ");
        b.append(this.album);
        
        return b.toString();
    }
    
    @Override
    int compareToSpec(Media m)
    {
        return this.album.compareTo(((Music)m).getAlbum()); //Only handles A->Z
    }
    
    void setBand(String band)
    {
        this.band = band;
    }
    
    String getBand()
    {
        return this.band;
    }
    
    void setAlbum(String album)
    {
        this.album = album;
    }
    
    String getAlbum()
    {
        return this.album;
    }
}
