package iori.hdoctor.net.response;

import java.util.ArrayList;

import iori.hdoctor.net.entity.TestRecord;

/**
 * Created by Administrator on 2015/8/7.
 */
public class PatientTestRecordResponse {

    private ArrayList<TestRecord> testrecordlist;

    public ArrayList<TestRecord> getTestrecordlist() {
        return testrecordlist;
    }

    public void setTestrecordlist(ArrayList<TestRecord> testrecordlist) {
        this.testrecordlist = testrecordlist;
    }
}
