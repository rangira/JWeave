package com.homeweave.jweave.messaging;


import com.homeweave.jweave.exceptions.SerializationException;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

class DummyMessage {
    private int v;
    private String str;
    List<DummyMessage2> dummyMsg;

    public DummyMessage(int v, String str, List<DummyMessage2> dummyMsg) {
        this.v = v;
        this.str = str;
        this.dummyMsg = dummyMsg;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setDummyMsg(List<DummyMessage2> dummyMsg) {
        this.dummyMsg = dummyMsg;
    }

    public int getV() {
        return v;
    }

    public String getStr() {
        return str;
    }

    public List<DummyMessage2> getDummyMsg() {
        return dummyMsg;
    }
}

class DummyMessage2 {
    private String str;

    public DummyMessage2(String str) {
        this.str = str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}


public class MessageTest {

    @Test
    public void testMessageSerialization() throws IOException, SerializationException {
        StringWriter sw = new StringWriter();
        BufferedWriter br = new BufferedWriter(sw);
        DummyMessage dummyMessage = new DummyMessage(1, "test", Arrays.asList(new DummyMessage2("test")));
        Message<DummyMessage> msg = new Message<DummyMessage>(Operation.DEQUEUE, new HashMap<String, String>(){{
            put("key1", "val1");
            put("key2", "val2");
        }}, dummyMessage);
        msg.serializeTo(br);
        br.flush();
        String expected =
                "OP dequeue\n" +
                "KEY1 val1\n" +
                "KEY2 val2\n" +
                "MSG {\"v\":1,\"str\":\"test\",\"dummyMsg\":[{\"str\":\"test\"}]}\n" +
                "\n";
        assertEquals(sw.getBuffer().toString(), expected);
    }
}
