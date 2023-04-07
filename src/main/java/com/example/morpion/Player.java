package com.example.morpion;

public class Player {
    public  String name ;
    public int score ;
    public String pawn ;
    public boolean start = false ;

    public Player(){

    }

    public Player(String name, int score, boolean start) {
        this.name = name;
        this.score = score;
        this.start = start;
    }



    public String getName() {
        return name;
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPawn() {
        return pawn;
    }

    public void setPawn(String pawn) {
        this.pawn = pawn;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void setName(String name) {
        this.name = name;
    }

}