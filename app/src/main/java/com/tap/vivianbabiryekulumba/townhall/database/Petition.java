package com.tap.vivianbabiryekulumba.townhall.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "petition_table")
public class Petition {

    @PrimaryKey(autoGenerate = true)
    private int petition_id;

    @ColumnInfo(name = "petition_title")
    public String petition_title;

    @ColumnInfo(name = "petition_content")
    public String petition_content;

    public Petition(@NonNull String petition_title, String petition_content) {
        this.petition_title = petition_title;
        this.petition_content = petition_content;
    }

    public int getPetition_id() {
        return petition_id;
    }

    public void setPetition_id(int petition_id) {
        this.petition_id = petition_id;
    }

    public String getPetition_title() {
        return petition_title;
    }

    public void setPetition_title(@NonNull String petition_title) {
        this.petition_title = petition_title;
    }

    public String getPetition_content() {
        return petition_content;
    }

    public void setPetition_content(String petition_content) {
        this.petition_content = petition_content;
    }
}
