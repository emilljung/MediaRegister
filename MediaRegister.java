package AllFiles;

import java.util.ArrayList;
import java.util.Collections;

class MediaRegister
{ 
    ArrayList<Media> musicList, movieList;
    
    public MediaRegister()
    {
        this.musicList = new ArrayList<Media>();
        this.movieList = new ArrayList<Media>();
    }
    
    void addMusic(Media m)
    {
        this.musicList.add((Music)m);
    }
    
    void addMovie(Media m)
    {
        this.movieList.add((Movie)m);
    }
    
    void removeAllMusic()
    {
        this.musicList.removeAll(this.musicList);
    }
    
    void removeMusicElem(int elem)
    {
        this.musicList.remove(elem);
    }
    
    void removeAllMovie()
    {
        this.movieList.removeAll(this.movieList);
    }
    
    void removeMovieElem(int elem)
    {
        this.movieList.remove(elem);
    }
    
    void changeMusicInfo(int elem, String title, String genre, int year, String band, String album)
    {
        this.musicList.get(elem).setTitle(title);
        this.musicList.get(elem).setGenre(genre);
        this.musicList.get(elem).setYear(year);
        ((Music)this.musicList.get(elem)).setBand(band);
        ((Music)this.musicList.get(elem)).setAlbum(album);
    }
    
    void changeMovieInfo(int elem, String title, String genre, int year, int playTime, String actor, String director)
    {
        this.movieList.get(elem).setTitle(title);
        this.movieList.get(elem).setGenre(genre);
        this.movieList.get(elem).setYear(year);
        ((Movie)this.movieList.get(elem)).setPlayTime(playTime);
        ((Movie)this.movieList.get(elem)).setActor(actor);
        ((Movie)this.movieList.get(elem)).setDirector(director);
    }
    
    void sortAfterTitle(boolean nr)
    {
        Media.changeSort(0);
        
        if(nr == true)
            bubbleSort(this.musicList);
        else
            bubbleSort(this.movieList);
    }
    
    void sortAfterAlbum()
    {
        Media.changeSort(1);
        bubbleSort(this.musicList);
    }
    
    void sortAfterActor()
    {
        Media.changeSort(1);
        bubbleSort(this.movieList);
    }
    
    void bubbleSort(ArrayList<Media> m)
    {
        for(int i = 0; i < m.size(); i++)
            for(int x = 1; x < m.size() - i; x++)
                if(m.get(x-1).compareTo(m.get(x)) > 0) //s.get(x-1) > s.get(x)
                    Collections.swap(m, x-1, x);
    }
    
    ArrayList<Media> getMovieList()
    {
        return this.movieList;
    }
    
    ArrayList<Media> getMusicList()
    {
        return this.musicList;
    }
    
    void saveMusic()
    {       
        FileItem<ArrayList> file = new FileItem<>("Music");
        file.saveToFile(this.musicList);
    }
    
    void saveMovie()
    {
        FileItem<ArrayList> file = new FileItem<>("Movie");
        file.saveToFile(this.movieList);
    }
    
    boolean loadMusic()
    {
        boolean ans = false;
            
        FileItem<ArrayList> file = new FileItem<>("Music");
        this.musicList = file.loadFromFile();
        
        if(this.musicList != null)
            ans = true;
        
        return ans;
    }
    
    boolean loadMovie()
    {
        boolean ans = false;
        
        FileItem<ArrayList> file = new FileItem<>("Movie");
        this.movieList = file.loadFromFile();
        
        if(this.movieList != null)
            ans = true;
        
        return ans;
    }
}
