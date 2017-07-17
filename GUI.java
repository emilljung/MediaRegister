package AllFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public final class GUI extends JFrame
{   
    class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand()) {
                case "Add":
                    add();
                    break;
                case "Remove":
                    removeElem();
                    break;
                case "Remove all":
                    removeAllElem();
                    break;
                case "Change info":
                    prepareChangeInfo();
                    break;
                case "Ok":
                    changeInfo();
                    break;
                case "Movie":
                    changeToMovie();
                    break;
                case "Music":
                    changeToMusic();
                    break;
                case "Save":
                    save();
                    break;
                case "Load":
                    load();
                    break;
                case "Exit":
                    quitProgram();
                    break;
                case "Sort: Title":
                    sortTitle();
                    break;
                case "Sort: Album":
                    sortMusicSpec();
                    break;
                case "Sort: Actor":
                    sortMovieSpec();
                    break;
            }
        }
    }
    
    MediaRegister register = new MediaRegister();   //Is used to add, remove, change etc objects.
    ButtonListener listener;                        //Add the listener to for example JButtons so they too can do stuff.
    JPanel enableDisable;                           //This is used to dis-/en-able the top of the right side.
    JTextField staticF1, staticF2, staticF3, changeF1, changeF2, changeF3;
    JList list;                     //Present Music or Movie list here.
    JButton sortB, addB;
    JRadioButton musicRB, movieRB;  //You either handle Music of Movie objects.
    
    public GUI()
    {
        this.listener = new ButtonListener();
        
        //Set JFrame 
        //---------------------------------------------------------------
        setTitle("Register");
        setSize(1000, 600); //Window has 1000 pixles wide, 600 pixles high
        setLayout(new GridLayout(1,2)); //1 row, 2 columns
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //---------------------------------------------------------------
        
        addMenu();
        addPanels();

        setVisible(true);
    }
    
    void addMenu()
    {
        //Add JMenuItems in a JMenu, which you later add in JMenuBar.
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem item;
        
        menu = new JMenu("File");
        item = new JMenuItem("Save");
        item.addActionListener(this.listener);
        menu.add(item);
        
        item = new JMenuItem("Load");
        item.addActionListener(this.listener);
        menu.add(item);
        
        item = new JMenuItem("Exit");
        item.addActionListener(this.listener);
        menu.add(item);
        menuBar.add(menu);
        
        setJMenuBar(menuBar);
    }
    
    void addPanels()
    {
        JPanel sidePanel, topPanel, centerPanel, bottomPanel;
        JButton jButton;

        //ButtonGroup belongs to the left panel.
        //Only one JRadioButton can be activated since they are grouped.
        //---------------------------------------------------------------
        ButtonGroup buttonGroup = new ButtonGroup();
        this.musicRB = new JRadioButton("Music");
        this.musicRB.addActionListener(this.listener);
        this.musicRB.setSelected(true); //Music is true by default.
        
        this.movieRB = new JRadioButton("Movie");
        this.movieRB.addActionListener(this.listener);
        
        buttonGroup.add(this.musicRB);
        buttonGroup.add(this.movieRB);
        //---------------------------------------------------------------
        
        
        //Left side is being constructed
        //-----------------------------------------------------
        sidePanel = new JPanel(new BorderLayout()); //This is the entire left side.
        topPanel = new JPanel(new FlowLayout());    //Top panel of the left side.
        topPanel.add(this.musicRB);                 //Add 2 JRadioButtons to the top of the left side.
        topPanel.add(this.movieRB);
        sidePanel.add(topPanel, BorderLayout.NORTH); //Add the top panel to sideOanel.

        //6 rows, 1 colums, 0 pixles between each column, 10 pixles between each row.
        centerPanel = new JPanel(new GridLayout(6, 1, 0, 20)); //The panel with the JTextFields.
        this.staticF1 = new JTextField();
        this.staticF2 = new JTextField();
        this.staticF3 = new JTextField();
        this.changeF1 = new JTextField();
        this.changeF2 = new JTextField();
        this.changeF3 = new JTextField();
        
        //These 3 will not change border title at all
        this.staticF1.setBorder(BorderFactory.createTitledBorder("Title"));
        this.staticF2.setBorder(BorderFactory.createTitledBorder("Genre"));
        this.staticF3.setBorder(BorderFactory.createTitledBorder("Year"));
        
        //These 3 are will change title and/or be in-/visible depending on which JRadioButton is active.
        this.changeF1.setBorder(BorderFactory.createTitledBorder("Band")); //Movie -> PlayTime
        this.changeF2.setBorder(BorderFactory.createTitledBorder("Album")); //Movie -> Actor
        this.changeF3.setBorder(BorderFactory.createTitledBorder("Director")); //Visible = true/false
        this.changeF3.setVisible(false); //Is invisible since Music is true by default.
        
        //Add all 6 JTextField to centerPanel (still on the left side).
        centerPanel.add(this.staticF1);
        centerPanel.add(this.staticF2);
        centerPanel.add(this.staticF3);
        centerPanel.add(this.changeF1);
        centerPanel.add(this.changeF2);
        centerPanel.add(this.changeF3);
        
        //A JButton is added (funny, right?) to the bottom of the left side.
        this.addB = new JButton("Add");
        this.addB.addActionListener(this.listener);
        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(this.addB);
        
        //Add both bottomPanel and centerPanel to sidePanel.
        sidePanel.add(bottomPanel, BorderLayout.SOUTH);
        sidePanel.add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(sidePanel);    //The left side is added to the JFrame.
        //-----------------------------------------------------
        
        //Right side is being contructed
        //-----------------------------------------------------
        sidePanel = new JPanel(new BorderLayout()); //This is now the entire right side.
        topPanel = new JPanel(new FlowLayout());    //This is now the top of the right side.
       
        //Add JButtons to the top of the right side.
        jButton = new JButton("Remove");
        jButton.addActionListener(this.listener);
        topPanel.add(jButton);
        jButton = new JButton("Remove all");
        jButton.addActionListener(this.listener);
        topPanel.add(jButton);
        jButton = new JButton("Change info");
        jButton.addActionListener(this.listener);
        topPanel.add(jButton);
        jButton = new JButton("Sort: Title");
        jButton.addActionListener(this.listener);
        topPanel.add(jButton);
        this.sortB = new JButton("Sort: Album");
        this.sortB.addActionListener(this.listener);
        topPanel.add(this.sortB);
        
        this.enableDisable = topPanel; //Is used while changing info

        //JList is created.
        this.list = new JList();
        this.list.setBorder(BorderFactory.createTitledBorder("Music"));
        
        sidePanel.add(topPanel, BorderLayout.NORTH);    //Add the top of the right side to sidePanel.
        sidePanel.add(this.list, BorderLayout.CENTER);  //Add the list to the rest of the right side.
        JScrollPane scrollPane = new JScrollPane(this.list); //Add scrollPane to JList.
        sidePanel.add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(sidePanel);                //Add the entire right side to JFrame.
        //-----------------------------------------------------
    }
    
    void add()
    {
        //Check if any of the JTextFields are empty (changeF3 is being checked if movieRB is true).
        if(!this.staticF1.getText().isEmpty() && !this.staticF2.getText().isEmpty() && !this.staticF3.getText().isEmpty() &&
           !this.changeF1.getText().isEmpty() && !this.changeF2.getText().isEmpty())
        {
            if(this.musicRB.isSelected())
            {
                //Try to convert the string in staticF3 to int/a number.
                try
                {
                    //A Music object is created if nothing goes wrong.
                    this.register.addMusic(new Music(this.staticF1.getText(),
                                                     this.staticF2.getText(),
                                                     Integer.parseInt(this.staticF3.getText()), //Convert string to int.
                                                     this.changeF1.getText(),
                                                     this.changeF2.getText()));
                }
                catch(Exception e)
                {
                    //staticF3 cannot be converted to int/a number.
                    JOptionPane.showMessageDialog(this, "Year must be an int!", "Error message", JOptionPane.ERROR_MESSAGE);
                }
                
                //Update the list presented on the screen.
                this.list.setListData(this.register.getMusicList().toArray()); //toString() is being called automatically.
                                                                               //Study Media, Music and Movie for more details.
            }
            else
            {
                //Check if changeF3 is empty.
                if(!this.changeF3.getText().isEmpty())
                {
                    //Try to convert the string in staticF3 and changeF1 to int.
                    try
                    {
                        //A Movie object is created if nothing goes wrong.
                        this.register.addMovie(new Movie(this.staticF1.getText(),
                                                         this.staticF2.getText(),
                                                         Integer.parseInt(this.staticF3.getText()), //Convert string to int.
                                                         Integer.parseInt(this.changeF1.getText()), //Convert string to int.
                                                         this.changeF2.getText(),
                                                         this.changeF3.getText()));
                    }
                    catch(Exception e)
                    {
                        //staticF3 and/or changeF1 cannot be converted to int.
                        JOptionPane.showMessageDialog(this, "Year and/or PlayTime must be an int!", "Error message", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                //Update the list.
                this.list.setListData(this.register.getMovieList().toArray());
            }
        }
    }
    
    void removeElem()
    {
        //Try to remove an element from the list.
        try
        {
            if(this.musicRB.isSelected())
            {
                this.register.removeMusicElem(this.list.getSelectedIndex());    //Remove the selected Music object.
                this.list.setListData(this.register.getMusicList().toArray());  //Update the list on the screen.
            }
            else
            {
                this.register.removeMovieElem(this.list.getSelectedIndex());    //Remove the selected Movie object.
                this.list.setListData(this.register.getMovieList().toArray());  //Update the list on the screen.
            }
        }
        catch(Exception e)
        {
            //No element is selected.
            JOptionPane.showMessageDialog(this, "No elements have been selected!", "Error message", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void removeAllElem()
    {
        if(this.musicRB.isSelected())
        {
            //Remove all Music objects
            this.register.removeAllMusic();
            this.list.setListData(this.register.getMusicList().toArray());  //Update the list on the screen.
        }
        else
        {
            //Remove all Movie objects
            this.register.removeAllMovie();
            this.list.setListData(this.register.getMovieList().toArray());  //Update the list on the screen.
        }
    }
    
    void changeToMovie()
    {
        
        this.list.setListData(this.register.getMovieList().toArray());  //The list is now showing the movie list.
        this.list.setBorder(BorderFactory.createTitledBorder("Movie")); //Border title of the list is now "Movie".
        this.sortB.setText("Sort: Actor");
        
        //Reset all JTextField, change the border title of changeF1 & changeF2 and show changeF3.
        this.staticF1.setText(null);
        this.staticF2.setText(null);
        this.staticF3.setText(null);
        this.changeF1.setBorder(BorderFactory.createTitledBorder("PlayTime"));
        this.changeF1.setText(null);
        this.changeF2.setBorder(BorderFactory.createTitledBorder("Actor"));
        this.changeF2.setText(null);
        this.changeF3.setVisible(true);
        this.changeF3.setText(null);
    }
    
    void changeToMusic()
    {
        this.list.setListData(this.register.getMusicList().toArray());  //The list is now showing the movie list.
        this.list.setBorder(BorderFactory.createTitledBorder("Music")); //The border title of the list is now "Music".
        this.sortB.setText("Sort: Album");
        
        //Reset all JTextField, change the border title of changeF1 & changeF2 and make changeF3 invisible.
        this.staticF1.setText(null);
        this.staticF2.setText(null);
        this.staticF3.setText(null);
        this.changeF1.setBorder(BorderFactory.createTitledBorder("Band"));
        this.changeF1.setText(null);
        this.changeF2.setBorder(BorderFactory.createTitledBorder("Album"));
        this.changeF2.setText(null);
        this.changeF3.setVisible(false);
        this.changeF3.setText(null);
    }
    
    void prepareChangeInfo()
    {
        //The point of this function is to change a bunch of stuff in the JFrame so you can
        //only quit and write the new content in the JTextFields. 
        //This function is called before changeInfo() is called.
        
        //Try to get all the values from a selected object and put them in the JTextFields.
        try
        {
            if(!this.list.isSelectionEmpty()) //There's nothing to change if the list is empty.
                    this.addB.setText("Ok");
            
            if(this.musicRB.isSelected())
            {
                //Get the values of the Music object and put them in the JTextFields.
                //About staticF3... the value is converted from int to string.
                this.staticF1.setText(this.register.getMusicList().get(this.list.getSelectedIndex()).getTitle());
                this.staticF2.setText(this.register.getMusicList().get(this.list.getSelectedIndex()).getGenre());
                this.staticF3.setText(Integer.toString(this.register.getMusicList().get(this.list.getSelectedIndex()).getYear()));
                this.changeF1.setText(((Music)this.register.getMusicList().get(this.list.getSelectedIndex())).getBand());
                this.changeF2.setText(((Music)this.register.getMusicList().get(this.list.getSelectedIndex())).getAlbum());
            }
            else
            {
                //Get the values of the Movie object and put them in the JTextFields.
                //The values which are being put in staticF3 & changeF1 are converted from int to string.
                this.staticF1.setText(this.register.getMovieList().get(this.list.getSelectedIndex()).getTitle());
                this.staticF2.setText(this.register.getMovieList().get(this.list.getSelectedIndex()).getGenre());
                this.staticF3.setText(Integer.toString(this.register.getMovieList().get(this.list.getSelectedIndex()).getYear()));
                this.changeF1.setText(Integer.toString(((Movie)this.register.getMovieList().get(this.list.getSelectedIndex())).getPlayTime()));
                this.changeF2.setText(((Movie)this.register.getMovieList().get(this.list.getSelectedIndex())).getActor());
                this.changeF3.setText(((Movie)this.register.getMovieList().get(this.list.getSelectedIndex())).getDirector());
            }
            
            //Disable list and the JRadioButtons.
            this.list.setEnabled(false);
            this.movieRB.setEnabled(false);
            this.musicRB.setEnabled(false);
                
            //Disables the buttons above the list.
            for(int i = 0; i < 5; i++)
                this.enableDisable.getComponent(i).setEnabled(false);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "No elements have been selected!", "Error message", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void changeInfo()
    {
        //Change all stuff back to normal.
        this.addB.setText("Add");
        this.list.setEnabled(true);
        this.movieRB.setEnabled(true);
        this.musicRB.setEnabled(true);
        
        for(int i = 0; i < 5; i++)
            this.enableDisable.getComponent(i).setEnabled(true);
        
        //Check if any of the JTextFields are empty.
        if(!this.staticF1.getText().isEmpty() && !this.staticF2.getText().isEmpty() && !this.staticF3.getText().isEmpty() &&
           !this.changeF1.getText().isEmpty() && !this.changeF2.getText().isEmpty())
        {
            if(this.musicRB.isSelected())
            {
                //Try to convert the text in staticF3 to int.
                try
                {
                    this.register.changeMusicInfo(this.list.getSelectedIndex(), this.staticF1.getText(), this.staticF2.getText(), 
                                            Integer.parseInt(this.staticF3.getText()), this.changeF1.getText(), this.changeF2.getText());
                    
                    this.list.setListData(this.register.getMusicList().toArray());
                }
                catch(Exception e)
                {
                    //The text in staticF3 could not be converted.
                    JOptionPane.showMessageDialog(this, "Year must be an int!", "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                //Check if changeF3 is empty
                if(!this.changeF3.getText().isEmpty())
                {
                    //Try to convert the text in staticF3 and changeF1 to int.
                    try
                    {
                        this.register.changeMovieInfo(this.list.getSelectedIndex(), this.staticF1.getText(), this.staticF2.getText(), 
                                                Integer.parseInt(this.staticF3.getText()), Integer.parseInt(this.changeF1.getText()), 
                                                this.changeF2.getText(), this.changeF3.getText());

                        this.list.setListData(this.register.getMovieList().toArray());
                    }
                    catch(Exception e)
                    {
                        //The text in staticF3 and/or changeF1 could not be converted.
                        JOptionPane.showMessageDialog(this, "Year and/or PlayTime must be an int!", "Error message", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    
    void sortTitle()
    {
        boolean nr;
        
        //Sort list after title.
        if(this.musicRB.isSelected())
        {
            nr = true;  //Music is in use.
            this.register.sortAfterTitle(nr);
            this.list.setListData(this.register.getMusicList().toArray());
        }
        else
        {
            nr = false; //Movie is in use.
            this.register.sortAfterTitle(nr);
            this.list.setListData(this.register.getMovieList().toArray());
        }
    }
    
    void sortMusicSpec()
    {
        //Sort after album in the Music list.
        this.register.sortAfterAlbum();
        this.list.setListData(this.register.getMusicList().toArray());
    }
    
    void sortMovieSpec()
    {
        //Sort after actor in the Movie list.
        this.register.sortAfterActor();
        this.list.setListData(this.register.getMovieList().toArray());
    }
    
    void save()
    {
        //If the text of addB is not "Add" then the window is not in "change mode".
        if(this.addB.getText().equals("Add"))
        {
            if(this.musicRB.isSelected())
            {
                //Save all Music objects.
                this.register.saveMusic();
            }
            else
            {
                //Save all Movie objects.
                this.register.saveMovie();
            }
        }
    }
    
    void load()
    {
        //If the text of addB is not "Add" then the window is not in "change mode".
        if(this.addB.getText().equals("Add")) 
        {
            if(this.musicRB.isSelected())
            {
                //Load Music
                if(this.register.loadMusic())
                    this.list.setListData(this.register.getMusicList().toArray());
            }
            else
            {
                //Load Movie
                if(this.register.loadMovie())
                    this.list.setListData(this.register.getMovieList().toArray());
            }
        }
    }
    
    void quitProgram()
    {
        System.exit(0); //Quit program
    }
}
