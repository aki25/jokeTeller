package com.example;

import java.util.Random;

public class JokingJava {
    private String[] jokesCollection = {"Random joke 1","Random joke 2","Random joke 3"};
    private Random random = new Random();

    public String humourMe(){
        return jokesCollection[random.nextInt(jokesCollection.length)];
    }

}
