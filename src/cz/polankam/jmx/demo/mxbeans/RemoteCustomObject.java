package cz.polankam.jmx.demo.mxbeans;

import java.beans.ConstructorProperties;

public class RemoteCustomObject {
    private final String state;
    private final int code;

    @ConstructorProperties({"state", "code"})
    public RemoteCustomObject(String state, int code) {
        this.state = state;
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public int getCode() {
        return code;
    }
}
