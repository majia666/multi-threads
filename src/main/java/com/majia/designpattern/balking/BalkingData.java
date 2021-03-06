package com.majia.designpattern.balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class BalkingData {

    private final String fileName;

    private String content;

    private boolean changed;

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent) {
        this.content = newContent;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed) {
            System.out.println(Thread.currentThread().getName() + " balking.");
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " call do save,content= " + this.content);
       try (Writer writer = new FileWriter(fileName, true)){
           writer.write(content);
           writer.write("\n");
           writer.flush();
       }

    }
}
