package org.csu.mypetstore.domain;

import java.util.HashMap;

public class ResponseTemplate {

    private Object data;
    private String returnMessage;
    private boolean judgement;

    public ResponseTemplate() {

    }

    public boolean isJudgement() {
        return judgement;
    }

    public void setJudgement(boolean judgement) {
        this.judgement = judgement;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
