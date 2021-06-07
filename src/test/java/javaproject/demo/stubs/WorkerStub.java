package javaproject.demo.stubs;

import javaproject.demo.entities.Worker;

import java.util.Date;

public final class WorkerStub {
    public static final Long ID = 1L;
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String PATRONIC = "patronic";
    public static final String EMAIL = "email";
    public static final String NUMBER = "number";
    public static final String POSITION = "position";
    public static final Date BIRTHDAY = new Date();
    public static final Date STARTEDWORK = new Date();
    public static Worker getRandomWorker() {
        return Worker.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .patronic(PATRONIC)
                .number(NUMBER)
                .birthday(BIRTHDAY)
                .startedWork(STARTEDWORK)
                .job(POSITION)
                .build();
    }
}
