package com.homeweave.jweave.messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homeweave.jweave.exceptions.SerializationException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class Message<T> {
    private final Operation operation;
    private final Map<String, String> headers;
    private final T data;

    public Message(Operation operation, Map<String, String> headers, T data) {
        this.operation = operation;
        this.headers = headers;
        this.data = data;
    }

    public void serializeTo(BufferedWriter writer) throws IOException, SerializationException {
        writer.write("OP " + this.operation.serialize() + "\n");
        for (Map.Entry<String, String> entry: this.headers.entrySet()) {
            writer.write(entry.getKey().toUpperCase() + " " + entry.getValue() + "\n");
        }
        if (data != null) {
            writer.write("MSG " + toJson(this.data) + "\n");
        }
        writer.write("\n");
        writer.flush();
    }

    private String toJson(T data) throws SerializationException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Unable to serialize.", e);
        } catch (IOException e) {
            throw new SerializationException("Unable to serialize.", e);
        }
    }

    public Operation getOperation() {
        return this.operation;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public T getData() {
        return this.data;
    }
}
