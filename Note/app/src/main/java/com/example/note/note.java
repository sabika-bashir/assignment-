package com.example.note;

public class note {
    long ID;
    String subject;
    String body;

      note(){}
    note(String subject, String body)
    {
        this.subject = subject;
        this.body = body;
    }
    note(Long ID,String subject, String body)
    {
        this.ID = ID;
        this.subject = subject;
        this.body = body;

    }


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
